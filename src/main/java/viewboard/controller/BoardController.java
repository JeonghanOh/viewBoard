package viewboard.controller;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import viewboard.dto.CommentDto;
import viewboard.dto.FavoriteDto;
import viewboard.dto.LikedDto;
import viewboard.dto.WriteDTO;
import viewboard.entity.BoardEntity;
import viewboard.entity.BoardTypeEntity;
import viewboard.entity.CommentEntity;
import viewboard.entity.UserEntity;
import viewboard.repository.BoardRepository;
import viewboard.repository.CommentRepository;
import viewboard.repository.DetailRepository;
import viewboard.repository.LikedRepository;
import viewboard.service.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/main")
public class BoardController {
    @Autowired
    BoardService boardService;
    @Autowired
    WriteService writeService;
    @Autowired
    CommentService commentService;
    @Autowired
    RandomService randomService;
    @Autowired
    SearchService searchService;

    @Autowired
    DetailRepository writeRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    LikedRepository likedRepository;

    @GetMapping("/board/{boardType}")
    public String getboard(@PathVariable("boardType") int type, Model model, @PageableDefault(page = 0, size = 10, sort = "boardId", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session) {
        Page<BoardEntity> list = boardService.getList(type, pageable);
        List<BoardTypeEntity> nameList = boardService.HotBoardType();
        List<BoardTypeEntity> list3 = boardService.selectAllBoardType();
        UserEntity user = (UserEntity) session.getAttribute("login");
        if (user != null) {
            boolean is = boardService.isFavorite(type, user);
            model.addAttribute("isfavorite", is);
        }
        int nowPag = list.getPageable().getPageNumber() + 1;
        int startPag = Math.max(nowPag - 4, 1);
        int endPag = Math.min(nowPag + 5, list.getTotalPages());
        System.out.println(list3);
        model.addAttribute("hotType", nameList);
        model.addAttribute("nowpage", nowPag);
        model.addAttribute("startpage", startPag);
        model.addAttribute("endpage", endPag);
        model.addAttribute("boardDetail", list.getContent());
        model.addAttribute("hotBoard", boardService.getHotboard(type));
        model.addAttribute("board", boardService.getBoardType(type));
        model.addAttribute("boardCount", boardService.counting(type));
        model.addAttribute("allBoard", list3);
        return "board";
    }

    @GetMapping("/board")
    public String board() {
        return "board";
    }

    @GetMapping("/detailboard/{boardId}")
    public String DetailBoard(@PathVariable("boardId") int id, Model model) {
        List<CommentEntity> commentList = commentRepository.findByCommentLists(id);
        boardService.increaseView(id);
        BoardEntity board = writeService.getFindid(id);

        model.addAttribute("board", board);
        model.addAttribute("Prev", writeService.prevPage(id, board.getBoardType()));
        model.addAttribute("Next", writeService.nextPage(id, board.getBoardType()));
        model.addAttribute("hotBoard", writeRepository.getHotGesigeul());
        model.addAttribute("type", boardService.getBoardType(board.getBoardType()));
        model.addAttribute("comment", commentList);
        model.addAttribute("count", commentService.CommentCount(board.getBoardId()));

        return "detailboard";
    }

    @PostMapping("/detailboard")
    public String CommentWrite(@ModelAttribute CommentDto commentDto) {
        commentService.Commentinsert(commentDto);
        return "redirect:/main/detailboard/" + commentDto.getBoardId();
    }

    @PostMapping("/detail")
    @ResponseBody
    public List<CommentEntity> MoreComment(@RequestParam("boardId") int id, @RequestParam("page") int page, Model model) {
        int pageSize = 10; // 페이지당 댓글 수
        int offset = (page - 1) * pageSize; // 페이지
        List<CommentEntity> commentList = commentRepository.findByCommentList(id, offset, pageSize);
        model.addAttribute("commentList", commentList);
        return commentList;
    }

    @PostMapping("/like")
    @ResponseBody
    public boolean like(@RequestParam("boardId") int id, @RequestParam("userEmail") String email, Model model) {
        LikedDto likedDto = new LikedDto();
        likedDto.setBoardId(id);
        likedDto.setUserEmail(email);
        boolean isLiked = boardService.isLiked(likedDto);
        if (likedRepository.existsById(likedDto.getBoardId())) {
            boardService.likeContent(likedDto);
        } else {
            boardService.likeContent(likedDto);
            boardService.likeview(id);
        }
        return isLiked;
    }

    @GetMapping("/write")
    public String write(Model model) {
        List<BoardTypeEntity> list = boardService.selectAllBoardType();
        model.addAttribute("allBoard", list);
        return "write";
    }

    @PostMapping("/write")
    public String write(@RequestParam("image") MultipartFile file, @ModelAttribute WriteDTO writeDTO, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("login");
        if (file.isEmpty()) {
            writeService.writePost1(writeDTO);
            writeService.increaseCount(writeDTO, user);

        } else {
            writeService.writePost2(file, writeDTO);
            writeService.increaseCount(writeDTO, user);
        }
        return "redirect:/main";
    }

    @GetMapping("")
    public String mainController(Model model) {
        Set<Integer> set = randomService.getGesipanSet(boardRepository);

        int i = 0;
        for (int x : set) {
            List<BoardEntity> list = writeRepository.findByboardType(x);
            BoardTypeEntity bte = boardRepository.findByboardType(x);
            System.out.println(list);
            model.addAttribute("boardList" + i, list);
            model.addAttribute("board" + i, bte);
            i++;
        }

        List<BoardEntity> list = writeRepository.getHotGesigeul();
        List<BoardTypeEntity> list2 = boardService.HotBoardType();
        List<BoardTypeEntity> list3 = boardService.selectAllBoardType();

        model.addAttribute("hotGesigeul", list);
        model.addAttribute("hotBoard", list2);
        model.addAttribute("allBoard", list3);
        return "main";
    }

    @RequestMapping("/searchresult")
    public String searchController(Model model, @RequestParam String query, @RequestParam int page, @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<BoardEntity> res = searchService.searchResult(query, writeRepository, pageable);
        ArrayList<BoardTypeEntity> bteList = new ArrayList<BoardTypeEntity>();

        for (int i = 0; i < res.toList().size(); i++) {
            bteList.add(boardRepository.findByboardType(res.toList().get(i).getBoardType()));
        }

        int nowPag = res.getPageable().getPageNumber() + 1;
        int startPag = Math.max(nowPag - 4, 1);
        int endPag = Math.min(nowPag + 5, res.getTotalPages());
        List<BoardTypeEntity> list3 = boardService.selectAllBoardType();

        System.out.println(nowPag + "/" + startPag + "/" + endPag + "/" + res.getTotalPages());

        model.addAttribute("nowpage", nowPag);
        model.addAttribute("startpage", startPag);
        model.addAttribute("endpage", endPag);

        model.addAttribute("query", query);
        model.addAttribute("result", res.getContent());
        model.addAttribute("bteList", bteList);
        model.addAttribute("page", page);
        model.addAttribute("allBoard", list3);

        return "searchresult";
    }

    @PostMapping("/favorite")
    public String favorite(FavoriteDto dto) {
        boardService.addFavorite(dto);
        return "redirect:/main/board/" + dto.getBoardType();
    }
}
