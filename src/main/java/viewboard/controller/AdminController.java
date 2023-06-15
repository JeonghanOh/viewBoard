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
import viewboard.dto.SearchuserDto;
import viewboard.entity.BoardTypeEntity;
import viewboard.entity.UserEntity;
import viewboard.service.AdminService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/user")
    public String userManage(){
        return "ad_user";
    }

    @GetMapping("/search_user")
    public String iframe(SearchuserDto searchuserDto, Model model, @RequestParam(value = "page", defaultValue = "0") int page, @PageableDefault(page = 0, size = 2, direction = Sort.Direction.DESC) Pageable pageable) {
        pageable = PageRequest.of(page, pageable.getPageSize(), pageable.getSort());
        Page<UserEntity> res = adminService.getUserList(searchuserDto, pageable);

        int nowPag = res.getPageable().getPageNumber() + 1;
        int startPag = Math.max(nowPag - 4, 1);
        int endPag = Math.min(nowPag + 5, res.getTotalPages());

        model.addAttribute("nowpage", nowPag);
        model.addAttribute("startpage", startPag);
        model.addAttribute("endpage", endPag);
        model.addAttribute("result", res.getContent());
        return "ad_user";
    }
    @GetMapping("/board")
    public String boardManage(Model model, @PageableDefault(page = 0, size = 5, sort = "boardType", direction = Sort.Direction.DESC) Pageable pageable){
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
    @PostMapping("/board/delete")
    public String boardDelete(AdminBoardDto dto){
        List<Integer> list =  dto.getBoardType();
        adminService.deleteBoard(list);
        return "redirect:/admin/board";
    }
    @PostMapping("/board/add")
    public String boardAdd(AdminBoardDto dto){
        adminService.addBoard(dto);
        return "redirect:/admin/board";
    }
    @PostMapping("/board/update")
    public String boardUpdate(AdminBoardDto dto,Model model){

        if(adminService.editTitle(dto)){
            model.addAttribute("notice","존재하지 않는 게시판입니다");
        }
        return "redirect:/admin/board";
    }
}
