package viewboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import viewboard.dto.*;
import viewboard.service.AuthService;
import viewboard.service.FindService;

import javax.servlet.http.HttpSession;
import java.util.Map;

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
    public String signUp(@Validated @ModelAttribute SignUpDto dto, Errors errors, Model model, RedirectAttributes redirectAttributes){
        if(errors.hasErrors()){
            model.addAttribute("dto",dto);
            Map<String,String> validatorResult = authService.validateHandle(errors);
            for(String key : validatorResult.keySet()){
                model.addAttribute(key,validatorResult.get(key));
            }
            model.addAttribute("errors", errors);
            return "signup";
        }
        authService.memberInsert(dto);
        redirectAttributes.addFlashAttribute("message", "회원 가입이 완료되었습니다.");
        return "main";
    }
    @PostMapping("/loginResult")
    public String login(@ModelAttribute SignInDto dto, HttpSession session){
        ResponseDto<SignInResponseDto> res = authService.signIn(dto);

        if(res.isResult()==true){
            session.setAttribute("login",res.getData().getUser());
            return "redirect:/main";
        }
        else{
            return "login";
        }
    }
    @PostMapping("/cancel")
    public String cancelUser(DeleteDto dto){
        authService.deleteUser(dto);
        return "/secession";
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
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/main";
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
    @GetMapping("/MyPage")
    public String MyPage() {
        return "MyPage";
    }

}
