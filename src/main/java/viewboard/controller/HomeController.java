package viewboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import viewboard.dto.WriteDTO;
import viewboard.service.WriteService;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final WriteService writeService;

    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/write")
    public String write(){
        return "write";
    }
    @PostMapping("/write")
    public String write(@ModelAttribute WriteDTO writeDTO){

        writeService.save(writeDTO);
        return "index";
    }
    @GetMapping("/DetailBoard")
    public String DetailBoard() {
        return "DetailBoard";
    }
    @GetMapping("/MyPage")
    public String MyPage() {
        return "MyPage";
    }
}
