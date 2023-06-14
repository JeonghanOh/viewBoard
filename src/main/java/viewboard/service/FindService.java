package viewboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import viewboard.dto.FindDto;
import viewboard.dto.ResponseDto;
import viewboard.entity.UserEntity;
import viewboard.repository.UserRepository;

import java.util.Random;

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
        }
        return password;
    }



    public String getRandomPassword( int length ){

        char[] charaters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
        StringBuffer sb = new StringBuffer();

        Random rn = new Random();

        for( int i = 0 ; i < length ; i++ ){

            sb.append( charaters[ rn.nextInt( charaters.length ) ] );

        }

        return sb.toString();

    }
}

