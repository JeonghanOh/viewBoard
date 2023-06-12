package viewboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viewboard.dto.FindDto;
import viewboard.dto.ResponseDto;
import viewboard.entity.UserEntity;
import viewboard.repository.UserRepository;

@Service
public class FindService {
    @Autowired
    UserRepository userRepository;

    public String findId(FindDto dto) {
        String email = "";
        String name = dto.getUserName();
        String phone = dto.getUserPhonenumber();
        UserEntity user;
        try {
            user = userRepository.findUserEmailByUserNameAndUserPhoneNumber(name, phone);
            email = user.getUserEmail();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Database Error");
        }
        return email;
    }

    public String findPassword(FindDto dto) {
        String email = dto.getUserEmail();
        String name = dto.getUserName();
        String phone = dto.getUserPhonenumber();
        String password = "";
        UserEntity user;
        try {
            user = userRepository.findUserPasswordByUserEmailAndUserNameAndUserPhoneNumber(email, name, phone);
            password = user.getUserPassword();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Database Error");
        }
        return password;
    }
}
