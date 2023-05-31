package viewboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import viewboard.dto.CommentDto;
import viewboard.dto.WriteDTO;
import viewboard.entity.BoardEntity;
import viewboard.entity.BoardTypeEntity;
import viewboard.entity.CommentEntity;
import viewboard.repository.BoardRepository;
import viewboard.repository.CommentRepository;
import viewboard.repository.DetailRepository;
import viewboard.service.BoardService;
import viewboard.service.CommentService;
import viewboard.service.SearchService;
import viewboard.service.WriteService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/main")
public class BoardController {
    @Autowired BoardService boardService;
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

    @GetMapping("/board/{boardType}")
    public String getboard(@PathVariable("boardType") int type, Model model, @PageableDefault(page=0,size = 10,sort = "boardId",direction = Sort.Direction.DESC) Pageable pageable){
        Page<BoardEntity> list = boardService.getList(type,pageable);
        int nowPag = list.getPageable().getPageNumber()+1;
        int startPag = Math.max(nowPag - 4,1);
        int endPag = Math.min(nowPag + 5,list.getTotalPages());
        model.addAttribute("nowpage",nowPag);
        model.addAttribute("startpage",startPag);
        model.addAttribute("endpage",endPag);
        model.addAttribute("boardDetail", list.getContent());
        model.addAttribute("hotBoard",boardService.getHotboard(type));
        model.addAttribute("board", boardService.getBoardType(type));
        model.addAttribute("boardCount",boardService.counting(type));
        return "board";
    }
    @GetMapping("/board")
    public String board() {
        return "board";
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
        return "redirect:/main";
    }
    @GetMapping("/write")
    public String showWrite(){
        return "write";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute WriteDTO writeDTO){
        writeService.save(writeDTO);
        return "redirect:/main";
    }

    @RequestMapping("")
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
