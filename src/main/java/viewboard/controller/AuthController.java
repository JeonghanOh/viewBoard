package viewboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public void signUp(@ModelAttribute SignUpDto dto){
        authService.memberInsert(dto);
    }
}
