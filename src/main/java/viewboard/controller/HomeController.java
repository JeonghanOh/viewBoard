package viewboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
