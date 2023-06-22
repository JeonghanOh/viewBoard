package viewboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import viewboard.dto.*;
import viewboard.entity.UserEntity;
import viewboard.repository.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DetailRepository detailRepository;
    @Autowired
    LikedRepository likedRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    FavoriteRepository favoriteRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public ResponseDto<?> memberInsert(SignUpDto dto) {
        String email = dto.getUserEmail();
        String password = dto.getUserPassword();
        String passwordChk = dto.getUserPasswordChk();
        try {
            if (userRepository.existsByUserEmail(email)) {
                return ResponseDto.setFailed("중복된 이메일 입니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!password.equals(passwordChk)) {
            return ResponseDto.setFailed("비밀번호가 일치하지 않습니다.");
        }
        UserEntity user = new UserEntity(dto);
        String encodedPassword = passwordEncoder.encode(password);
        user.setUserPassword(encodedPassword);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseDto.setSuccess("회원가입 성공", null);
    }

    public ResponseDto<SignInResponseDto> signIn(SignInDto dto) {
        String id = dto.getId();
        String password = dto.getPassword();


        UserEntity userentity = null;
        try {
            userentity = userRepository.findByUserEmail(id);
//			잘못된 이메일
            if (userentity == null) return ResponseDto.setFailed("sign in failed");
//			잘못된 패스워드
            if (!passwordEncoder.matches(password,userentity.getUserPassword())) {
                return ResponseDto.setFailed("sign in failed");
            }

        } catch (Exception error) {
            return ResponseDto.setFailed("Database Error");
        }
        userentity.setUserPassword("");

        int exprTime = 3600000;

        SignInResponseDto signInResponseDto = new SignInResponseDto(exprTime, userentity);
        return ResponseDto.setSuccess("sign in success !", signInResponseDto);
    }

    public Map<String, String> validateHandle(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    public boolean deleteUser(DeleteDto dto) {
        String email = dto.getUserEmail();
        String password = dto.getUserPassword();
        boolean a = false;
        UserEntity user = null;
        user = userRepository.findByUserEmail(email);
        try {
            if (email != null && passwordEncoder.matches(password,user.getUserPassword())){
                deleteAllAboutUser(email);
                a=true;
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public void ChangeNick(String nickname, String email) {
        try {
            userRepository.FixNickname(nickname, email);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
    public void updateNewPassword(String password, FindDto dto){
        String newPassword = passwordEncoder.encode(password);
        String userEmail = dto.getUserEmail();
        try {
            userRepository.newPassword(newPassword, userEmail);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Transactional
    public void deleteAllAboutUser(String email) {
        userRepository.deleteByUserEmail(email);
        detailRepository.deleteByUserEmail(email);
        likedRepository.deleteByLikedDtoUserEmail(email);
        commentRepository.deleteByUserEmail(email);
        favoriteRepository.deleteByFavoriteDtoUserEmail(email);
    }
}
