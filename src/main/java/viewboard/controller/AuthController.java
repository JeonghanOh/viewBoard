package viewboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import viewboard.dto.SignUpDto;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpDto dto){

    }
}
