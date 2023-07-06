package viewboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import viewboard.dto.*;
import viewboard.entity.BoardEntity;
import viewboard.service.AuthService;
import viewboard.service.BoardService;
import viewboard.service.FindService;
import viewboard.service.UserService;

import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    FindService findService;
    @Autowired
    UserService userService;
    @Autowired
    BoardService boardService;

//    회원가입페이지
    @GetMapping("/signup")
    public String signUpHome() {
        return "signup";
    }

//  유효성검사 , 회원가입
    @PostMapping("/signup")
    public String signUp(@Validated @ModelAttribute SignUpDto dto, Errors errors, Model model, RedirectAttributes redirectAttributes) {
        if (errors.hasErrors()) {
            model.addAttribute("dto", dto);
            Map<String, String> validatorResult = authService.validateHandle(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            model.addAttribute("errors", errors);
            return "signup";
        }
        ResponseDto<?> result = authService.memberInsert(dto);
        if (result.isResult() == true) {
            redirectAttributes.addFlashAttribute("message", "회원 가입이 완료되었습니다.");
            return "redirect:/auth/login";
        } else {
            return "signup";
        }
    }
//  로그인
    @PostMapping("/loginresult")
    public String login(@ModelAttribute SignInDto dto, HttpSession session) {
        ResponseDto<SignInResponseDto> res = authService.signIn(dto);

        if (res.isResult() == true) {
            session.setAttribute("login", res.getData().getUser());
            return "redirect:/main";
        } else {
            return "login";
        }
    }
//  회원탈퇴
    @PostMapping("/cancel")
    public String cancelUser(DeleteDto dto,HttpSession session,Model model) {
        boolean state = authService.deleteUser(dto);
//        회원 탈퇴 성공시 세션제거
        if(state) {
            session.invalidate();
            model.addAttribute("state", state);
            return "oo";
        }else {
            return "secession";
        }
    }

    @GetMapping("/login")
    public String signIn() {
        return "login";
    }
    @GetMapping("/oo")
    public String oo(){
        return "oo";
    }
    @GetMapping("/service")
    public String viewfind() {
        return "authservice";
    }

//    아이디찾기
    @PostMapping("/findid")
    public String findEmail(FindDto dto, Model model) {
        String email = findService.findId(dto);
        if (email == "" || email == null) {
            model.addAttribute("find", "해당하는계정이 없습니다.");
            return "id_finder";
        }
        model.addAttribute("find", email);
        return "id_finder";
    }
//  비밀번호 찾기 ( 새비밀번호 생성)
    @PostMapping("/findpw")
    public String findPassword(FindDto dto, Model model) {
        String password = findService.findPassword(dto);
//        비밀번호가 비어있을때
        if (password == "" || password == null) {
            model.addAttribute("find", "해당하는계정이 없습니다.");
            return "pw_finder";
        }
//        10자리 랜덤 패스워드 생성
        String newPassword  = findService.getRandomPassword(10);
//        생성된패스워드 암호화 , 저장
        authService.updateNewPassword(newPassword,dto);
        model.addAttribute("find", newPassword);
        return "pw_finder";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/main";
    }

    @GetMapping("/findid")
    public String viewId() {
        return "id_finder";
    }

    @GetMapping("/findpw")
    public String viewPw() {
        return "pw_finder";
    }

    @GetMapping("/secession")
    public String viewSecession() {
        return "secession";
    }

//    내정보 페이지

    @GetMapping("/mypage")
    public String MyPage(@RequestParam("UserEmail") String email, Model model, @PageableDefault(page = 0, size = 6, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<BoardEntity> list = boardService.getMyBoardList(email, pageable);
        int nowPag = list.getPageable().getPageNumber() + 1;
        int startPag = Math.max(nowPag - 4, 1);
        int endPag = Math.min(nowPag + 5, list.getTotalPages());
        model.addAttribute("NickName", userService.findNickName(email));
        model.addAttribute("nowpage", nowPag);
        model.addAttribute("startpage", startPag);
        model.addAttribute("endpage", endPag);
        model.addAttribute("Title", list.getContent());
        model.addAttribute("favorite", userService.favBoard(email));

        return "mypage";
    }

    @PostMapping("/mypage")
    public String changeNick(@RequestParam("UserNickName") String NickName, @RequestParam("UserEmail") String email, Model model) {
        authService.ChangeNick(NickName, email);
        model.addAttribute("FixName", NickName);
        return "redirect:/auth/mypage?UserEmail=" + URLEncoder.encode(email, StandardCharsets.UTF_8);
    }

}
