package viewboard.service;

import org.springframework.stereotype.Service;
import viewboard.dto.SignUpDto;

@Service
public class AuthService {
    public void memberInsert(SignUpDto dto){
        String email = dto.getUserEmail();
        String password = dto.getUserPassword();
        String passwordChk = dto.getUserPasswordChk();
        try{
            if(password.equals(passwordChk)){

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
