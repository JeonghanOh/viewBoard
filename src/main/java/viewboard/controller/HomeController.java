package viewboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import viewboard.dto.CommentDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import viewboard.dto.WriteDTO;
import viewboard.entity.BoardEntity;
import viewboard.entity.BoardTypeEntity;
import viewboard.entity.CommentEntity;
import viewboard.repository.CommentRepository;
import viewboard.service.CommentService;
import viewboard.service.SearchService;
import viewboard.service.WriteService;
import viewboard.repository.BoardRepository;
import viewboard.repository.DetailRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @Autowired
    WriteService writeService;
    @Autowired
    CommentService commentService;

    @Autowired
    DetailRepository writeRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    CommentRepository commentRepository;
    @GetMapping("")
    public String home(){
        return "index";
    }
    @GetMapping("/write")
    public String write(){
        return "write";
    }
    @GetMapping("/board")
    public String board() {
        return "board";
    }
    @PostMapping("/write")
    public String write(@ModelAttribute WriteDTO writeDTO){
        writeService.save(writeDTO);
        return "index";
    }
    @GetMapping("/DetailBoard/{boardId}")
    public String DetailBoard(@PathVariable("boardId") int id, Model model){
        List<CommentEntity> commentList = commentRepository.findByCommentList(id);
        model.addAttribute("board" , writeService.getFindid(id));
        model.addAttribute("comment",commentList);
        return "DetailBoard";
    }

    @PostMapping("/DetailBoard")
    public String CommentWrite(@ModelAttribute CommentDto commentDto){
        commentService.Commentinsert(commentDto);
        return "DetailBoard";
    }

    @GetMapping("/MyPage")
    public String MyPage() {
        return "MyPage";
    }
    @RequestMapping("/main")
    public String mainController(Model model){
        int maxType = boardRepository.getMaxBoard_type();
        Set<Integer> set = new HashSet<Integer>();
        while(set.size() < 4){
            int type = (int)(Math.random()*maxType)+1;
            set.add(type);
            System.out.println("type : " + type);
        }

        int i = 0;
        for(int x:set){
            System.out.println("i : " + i + " x: " + x);
            List<BoardEntity> list = writeRepository.findByboardType(x);
            model.addAttribute("boardList"+i, list);
            i++;
        }

        List<BoardEntity> list = writeRepository.getHotGesigeul();
        model.addAttribute("hotGesigeul", list);
        return "main";
    }


    @RequestMapping("/searchResult")
    public String searchController(Model model, @RequestParam String query){
        SearchService ss = new SearchService();
        List<BoardEntity> res = ss.searchResult(query, writeRepository);
        ArrayList<BoardTypeEntity> bteList = new ArrayList<BoardTypeEntity>();

        for(int i=0;i<res.size();i++){
            bteList.add(boardRepository.findByboardType(res.get(i).getBoardType()));
        }

        model.addAttribute("result", res);
        model.addAttribute("bteList", bteList);

        return "searchResult";
    }
}