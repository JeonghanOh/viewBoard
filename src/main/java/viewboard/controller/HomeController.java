package viewboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("")
    public String home(){
        return "index";
    }
    @GetMapping("/write")
    public String write(){
        return "write";
    }
    @GetMapping("/board")
    public String board(){
        return "board";
    }

    @RequestMapping("/main")
    public String mainController(){return "main";}

    @RequestMapping("/searchResult")
    public String searchController(){return "searchResult";}

    @RequestMapping("/login")
    public String loginController(){return "login";}
}
