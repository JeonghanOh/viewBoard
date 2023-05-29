package viewboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import viewboard.entity.BoardEntity;
import viewboard.service.BoardService;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired BoardService boardService;

    @GetMapping("/{boardType}")
    public String getboard(@PathVariable("boardType") int type, Model model){

        model.addAttribute("boardDetail", boardService.getList(type));
        model.addAttribute("hotBoard",boardService.getHotboard(type));
        model.addAttribute("boardType", boardService.getBoardType(type));
        model.addAttribute("boardCount",boardService.counting(type));
        return "board";
    }
}
