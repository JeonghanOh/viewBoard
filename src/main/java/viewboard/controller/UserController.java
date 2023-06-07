package viewboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import viewboard.dto.EditDto;
import viewboard.dto.FavoriteDto;
import viewboard.repository.FavoriteRepository;
import viewboard.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/mypage")
    public String myPage(Model model, String email){
        model.addAttribute("mine",userService.myList("wnsqja@naver.com"));
        model.addAttribute("info",userService.userInfo("dkdfl1235@naver.com"));
       model.addAttribute("favorite",userService.favBoard("dkdfl1235@naver.com"));

        return "MyPage";
    }
    @PostMapping("/change")
    public String change(EditDto dto){
        userService.changeName(dto);
        return "redirect:/user/mypage";
    }
}
