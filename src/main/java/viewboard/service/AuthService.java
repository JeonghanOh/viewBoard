package viewboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viewboard.dto.SignUpDto;
import viewboard.entity.UserEntity;
import viewboard.repository.UserRepository;

@Service
public class AuthService {
    @Autowired UserRepository userRepository;
    public void memberInsert(SignUpDto dto){
        String email = dto.getUserEmail();
        String password = dto.getUserPassword();
        String passwordChk = dto.getUserPasswordChk();
        try{
            if(userRepository.existsByuserEmail(email)){
                throw new IllegalStateException("이미 존재하는 이메일입니다.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
            if(!password.equals(passwordChk)) {
                throw new IllegalStateException("비밀번호가 일치하지 않습니다");
            }
        UserEntity user = new UserEntity(dto);
        try{
            System.out.println(dto.getUserPhoneNumber());
            userRepository.save(user);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
