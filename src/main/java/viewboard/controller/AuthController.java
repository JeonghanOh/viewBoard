package viewboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import viewboard.dto.*;
import viewboard.service.AuthService;
import viewboard.service.FindService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired AuthService authService;
    @Autowired FindService findService;
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
    @PostMapping("/cancel")
    public String cancelUser(DeleteDto dto){
        authService.deleteUser(dto);
        return "secession";
    }
    @GetMapping("/login")
    public String signIn(){
        return "login";
    }
    @GetMapping("/service")
    public String viewfind(){
        return "authservice";
    }

    @PostMapping("/findid")
    public String findEmail(FindDto dto, Model model){
        String email= findService.findId(dto);
        if(email=="" || email == null){
            model.addAttribute("find","해당하는계정이 없습니다.");
            return "authservice";
        }
        model.addAttribute("find",email);
        return "id_finder";
    }
    @PostMapping("/findpw")
    public String findPassword(FindDto dto, Model model){
        String password= findService.findPassword(dto);
        if(password=="" || password == null){
            model.addAttribute("find","해당하는계정이 없습니다.");
            return "authservice";
        }
        model.addAttribute("find",password);
        return "pw_finder";
    }
    @GetMapping("/findid")
    public String viewId(){
        return "id_finder";
    }

    @GetMapping("/findpw")
    public String viewPw(){
        return "pw_finder";
    }
    @GetMapping("/secession")
    public String viewSecession(){
        return "secession";
    }
}
