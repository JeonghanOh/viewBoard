package viewboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import viewboard.dto.AdminBoardDto;
import viewboard.dto.AuthorityDto;
import viewboard.dto.SearchuserDto;
import viewboard.entity.BoardTypeEntity;
import viewboard.entity.SearchEntity;
import viewboard.entity.UserEntity;
import viewboard.service.AdminService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/user")
    public String userManage(HttpSession session){
        UserEntity ue = (UserEntity) session.getAttribute("login");
        // 로그인 되지 않았거나 어드민 권한이 있는 사람만 접근 가능
        if(ue != null && adminService.isGranted(ue.getUserEmail()))
            return "ad_user";

        return "redirect:/main";
    }

    // adminpage
    // pageable annotation 사용해서 유저 정보를 페이지 별로 나눠서 뿌려줌
    @GetMapping("/search_user")
    public String Search_user(SearchuserDto searchuserDto, Model model, @RequestParam(value = "page", defaultValue = "0") int page, @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable pageable) {
        pageable = PageRequest.of(page, pageable.getPageSize(), pageable.getSort());
        Page<SearchEntity> res = adminService.getUserList(searchuserDto, pageable);
        // nowPag , startPag , endPag 객체 설정 및 view단에 처리
        int nowPag = res.getPageable().getPageNumber() + 1;
        int startPag = Math.max(nowPag - 4, 1);
        int endPag = Math.min(nowPag + 5, res.getTotalPages());

        model.addAttribute("nowpage", nowPag);
        model.addAttribute("startpage", startPag);
        model.addAttribute("endpage", endPag);
        // pageable로 처리된 정보
        model.addAttribute("result", res.getContent());
        return "ad_user";
    }

    // Admin 권한 부여
    @PostMapping("/update")
    public String Check_user(AuthorityDto authorityDto){
        List<String> list = authorityDto.getUser_email();
        adminService.updategrant(list);
        return"redirect:/admin/user";
    }

    // 유저 정보 삭제
    @PostMapping("/delete")
    public String Check_user_delete(AuthorityDto authorityDto){
        List<String> list = authorityDto.getUser_email();
            adminService.deleteuser(list);
        return"redirect:/admin/user";
    }

    // Admin 권한 뺏기
    @PostMapping("/down")
    public String Check_grant_down(AuthorityDto authorityDto){
        List<String> list = authorityDto.getUser_email();
        adminService.rollbackgrant(list);
        return"redirect:/admin/user";
    }
//    관리자 게시판 페이지
    @GetMapping("/board")
    public String boardManage(Model model, HttpSession session ,@PageableDefault(page = 0, size = 5, sort = "boardType", direction = Sort.Direction.DESC) Pageable pageable){

        UserEntity ue = (UserEntity) session.getAttribute("login");

        if(ue == null || false == adminService.isGranted(ue.getUserEmail()))
            return "redirect:/main";

        Page<BoardTypeEntity> list =adminService.getBoardList(pageable);
        int nowPag = list.getPageable().getPageNumber() + 1;
        int startPag = Math.max(nowPag - 4, 1);
        int endPag = Math.min(nowPag + 5, list.getTotalPages());
        model.addAttribute("nowpage", nowPag);
        model.addAttribute("startpage", startPag);
        model.addAttribute("endpage", endPag);
        model.addAttribute("board",list.getContent());
        return "ad_board";
    }
//    게시판 삭제
    @PostMapping("/board/delete")
    public String boardDelete(AdminBoardDto dto){
        List<Integer> list =  dto.getBoardType();
        adminService.deleteBoard(list);
        return "redirect:/admin/board";
    }
//    게시판 추가
    @PostMapping("/board/add")
    public String boardAdd(AdminBoardDto dto){
        adminService.addBoard(dto);
        return "redirect:/admin/board";
    }
//    게시판 수정
    @PostMapping("/board/update")
    public String boardUpdate(AdminBoardDto dto,Model model){

        if(adminService.editTitle(dto)){
            model.addAttribute("notice","존재하지 않는 게시판입니다");
        }
        return "redirect:/admin/board";
    }
}
