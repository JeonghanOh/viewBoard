package viewboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import viewboard.dto.*;
import viewboard.entity.BoardEntity;
import viewboard.entity.BoardTypeEntity;
import viewboard.entity.CommentEntity;
import viewboard.entity.UserEntity;
import viewboard.repository.*;
import viewboard.service.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Autowired
    UserRepository userRepository;

    public static String removeTags(String html) {
        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(html);
        return matcher.replaceAll("");
    }

    @GetMapping("/board/{boardType}")
    public String getboard(@PathVariable("boardType") int type, Model model, @PageableDefault(page = 0, size = 10, sort = "boardId", direction = Sort.Direction.DESC) Pageable pageable, HttpSession session) {
        Page<BoardEntity> list = boardService.getList(type, pageable);
        List<BoardTypeEntity> nameList = boardService.HotBoardType();
        List<BoardTypeEntity> list3 = boardService.selectAllBoardType();
        List<BoardTypeEntity> list2 = boardService.HotBoardType();
        UserEntity user = (UserEntity) session.getAttribute("login");
        if (user != null) {

            boolean is = boardService.isFavorite(type, user);
            model.addAttribute("isfavorite", is);
            int board_count = boardService.count(user.getUserEmail());
            model.addAttribute("boardcount",board_count);
        }

        int nowPag = list.getPageable().getPageNumber() + 1;
        int startPag = Math.max(nowPag - 4, 1);
        int endPag = Math.min(nowPag + 5, list.getTotalPages());
        model.addAttribute("hotType", nameList);
        model.addAttribute("nowpage", nowPag);
        model.addAttribute("startpage", startPag);
        model.addAttribute("endpage", endPag);
        model.addAttribute("boardDetail", list.getContent());
        model.addAttribute("hot", list2);
        model.addAttribute("hotBoard", boardService.getHotboard(type));
        model.addAttribute("board", boardService.getBoardType(type));
        model.addAttribute("allBoard", list3);
        return "board";
    }

    @GetMapping("/board")
    public String board() {
        return "board";
    }

    // 해당 게시글 아이디를 주소로 받아옴.
    @GetMapping("/detailboard/{boardId}")
    public String DetailBoard(@PathVariable("boardId") int id, Model model, HttpSession session){
        // 댓글 리스트를 commentRepository를 통해 DB와 연동해서 받아옴
        List<CommentEntity> commentList = commentRepository.findByCommentLists(id);
        boardService.increaseView(id);
        BoardEntity board = writeService.getFindid(id);
        LikedDto likedDto = new LikedDto();
        likedDto.setBoardId(id);
        // User 세션 로그인 정보 확인
        UserEntity user = (UserEntity)session.getAttribute("login");
        if(user != null) {
            likedDto.setUserEmail(user.getUserEmail());
            // 해당 유저가 작성한 게시글
            int board_count = boardService.count(user.getUserEmail());
            model.addAttribute("board_count",board_count);
            // 해당 유저의 좋아요 상태
            boolean isLiked = boardService.isLiked(id, user.getUserEmail());
            if (likedRepository.existsByLikedDtoBoardIdAndLikedDtoUserEmail(id, likedDto.getUserEmail())) {
                model.addAttribute("Likestatus", isLiked);
            } else {
                model.addAttribute("Likestatus", isLiked);
            }
        } else {
            // 로그인하지 않은 사용자의 처리
            model.addAttribute("Likestatus", false);
        }
        model.addAttribute("board", board);
        // 이전글 다음글 버튼 정보
        model.addAttribute("Prev", writeService.prevPage(id, board.getBoardType()));
        model.addAttribute("Next", writeService.nextPage(id, board.getBoardType()));
        // 핫 게시글 정보
        model.addAttribute("hotBoard", writeRepository.getHotGesigeul());
        model.addAttribute("type", boardService.getBoardType(board.getBoardType()));
        // 댓글 리스트
        model.addAttribute("comment", commentList);
        // 댓글 작성 갯수
        model.addAttribute("count", commentService.CommentCount(board.getBoardId()));
        return "detailboard";
    }
    //
    @PostMapping("/detailboard")
    public String CommentWrite(@ModelAttribute CommentDto commentDto) {
        commentService.Commentinsert(commentDto);
        // 댓글 작성 작업후 다시 commentDto.getBoardId() 해당 게시글의 BoardId 주소로 리다이렉트
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
    public boolean like(@RequestParam("boardId") int id, @RequestParam("userEmail") String email , Model model){
        boolean isLiked = boardService.isLiked(id, email);
        // 좋아요 existsByLikedDtoBoardIdAndLikedDtoUserEmail 복합키 중복 체크 true, false 형태 반환
        if (likedRepository.existsByLikedDtoBoardIdAndLikedDtoUserEmail(id, email)) {
            boardService.likeContent(id, email);
        } else {
            boardService.likeContent(id,email);
            boardService.likeview(id);
        }
        return isLiked;
    }

    @GetMapping("/write")
    public String write(Model model) {
        List<BoardTypeEntity> list = boardService.selectAllBoardType();
        System.out.println("allboard / write" + list);
        model.addAttribute("allBoard", list);
        return "write";
    }

    // 게시물 작성
    @PostMapping("/write")
    public String write(@RequestParam("image") MultipartFile file, @ModelAttribute WriteDTO writeDTO, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("login");
        if (file.isEmpty()) {
            // 파일이 없을 경우
            writeService.writePost1(writeDTO);
            writeService.increaseCount(writeDTO, user);
        } else {
            // 파일이 있을 경우
            writeService.writePost2(file, writeDTO);
            writeService.increaseCount(writeDTO, user);
        }
        return "redirect:/main";
    }

    // 게시글 수정 기능 View단 처리
    @GetMapping("/write/update")
    public String Fix(@RequestParam("boardId") int id ,Model model){
        // 기존 게시글 정보를 받아옴
        List<BoardEntity> fixboard = boardService.findById(id);
        List<BoardTypeEntity> list = boardService.selectAllBoardType();
        // 기존 게시글을 다시 Write 페이지 에 뿌려줌
        model.addAttribute("allBoard", list);
        model.addAttribute("BoardContent",fixboard);
        return "write";
    }

    // 게시글 수정 기능 
    @PostMapping("/write/update")
    public String Fixboard(@RequestParam("image") MultipartFile file, WriteDTO writeDTO, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("login");
        if (file.isEmpty()) {
            // 파일이 없을 경우 처리
            writeService.updatePost1(writeDTO);
            writeService.increaseCount(writeDTO, user);
        } else {
            // 파일이 있을 경우 처리
            writeService.writePost2(file, writeDTO);
            writeService.increaseCount(writeDTO, user);
        }
        // 수정 후 다시 해당 게시글 자세히 보기로 이동
        return "redirect:/main/detailboard/" + writeDTO.getBoardId();
    }

    @GetMapping("")
    public String mainController(Model model, HttpSession session) {
        Set<Integer> set = randomService.getGesipanSet(boardRepository);

        int i = 0;
        for (int x : set) {
            List<BoardEntity> list = writeRepository.findByboardType(x);
            BoardTypeEntity bte = boardRepository.findByboardType(x);

            model.addAttribute("boardList" + i, list);
            model.addAttribute("board" + i, bte);
            i++;
        }

        List<BoardEntity> list = writeRepository.getHotGesigeul();

        List<BoardTypeEntity> list2 = boardService.HotBoardType();
        List<BoardTypeEntity> list3 = boardService.selectAllBoardType();

        UserEntity tmp = (UserEntity) session.getAttribute("login");
        UserEntity user = null;
        if(tmp != null) {
            int board_count = boardService.count(tmp.getUserEmail());
            model.addAttribute("count",board_count);
            user = userRepository.findByUserEmail(tmp.getUserEmail());
        }

        model.addAttribute("hotGesigeul", list);
        model.addAttribute("hotBoard", list2);
        model.addAttribute("allBoard", list3);
        model.addAttribute("user", user);
        return "main";
    }

    @RequestMapping("/searchresult")
    public String searchController(Model model, @RequestParam String query, @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable pageable) {
        System.out.println("query : "+query);

        if(query.equals(""))
            return "redirect:/main";

        Page<BoardEntity> res = searchService.searchResult(query, writeRepository, pageable);
        ArrayList<BoardTypeEntity> bteList = new ArrayList<BoardTypeEntity>();

        for (int i = 0; i < res.toList().size(); i++) {
            bteList.add(boardRepository.findByboardType(res.toList().get(i).getBoardType()));
        }

        int nowPag = res.getPageable().getPageNumber() + 1;
        int startPag = Math.max(nowPag - 4, 1);
        int endPag = Math.min(nowPag + 5, res.getTotalPages());
        List<BoardTypeEntity> list3 = boardService.selectAllBoardType();
        List<BoardTypeEntity> list2 = boardService.HotBoardType();


        model.addAttribute("nowpage", nowPag);
        model.addAttribute("startpage", startPag);
        model.addAttribute("endpage", endPag);

        model.addAttribute("query", query);
        model.addAttribute("result", res.getContent());
        model.addAttribute("bteList", bteList);
        model.addAttribute("allBoard", list3);
        model.addAttribute("hotBoard", list2);

        return "searchresult";
    }
    @PostMapping("/delete")
    public String deleteCon(DeleteConDto dto,HttpSession session){
        UserEntity user = (UserEntity) session.getAttribute("login");
        boardService.deleteCon(dto,user);
        return "redirect:/main/board/"+dto.getBoardType();
    }
    @PostMapping("/favorite")
    public String favorite(FavoriteDto dto) {
        boardService.addFavorite(dto);
        return "redirect:/main/board/" + dto.getBoardType();
    }
}
