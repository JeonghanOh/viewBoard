package viewboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import viewboard.dto.ResponseDto;
import viewboard.dto.SignInDto;
import viewboard.dto.SignInResponseDto;
import viewboard.dto.SignUpDto;
import viewboard.service.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired AuthService authService;
    @GetMapping("/signup")
    public String signUpHome(){
        return "signup";
    }
    @PostMapping("/signup")
    public String signUp(@ModelAttribute SignUpDto dto){
        authService.memberInsert(dto);
        return "board";
    }
    @PostMapping("/loginResult")
    public String login(@ModelAttribute SignInDto dto){
        ResponseDto<SignInResponseDto> res = authService.signIn(dto);

        if(res.isResult()==true){
            return "loginSuccess";
        }
        else{
            return "login";
        }
    }
    @GetMapping("/login")
    public String signIn(){
        return "login";
    }
}
