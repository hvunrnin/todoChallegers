package login.loginspring.controller;

import login.loginspring.domain.Member;
import login.loginspring.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    /**로그인 폼*/
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**회원가입 폼*/
    @GetMapping("/signUp")
    public String signUpForm() {
        return "signup";
    }

//    @PostMapping("/signUP")
//    public String signUpFail() {return "signUp_denied";}

    /**로그인 실패 폼*/
    @GetMapping("/access_denied")
    public String accessDenied(){
        return "access_denied";
    }

    /**회원가입 진행*/
    @PostMapping("/signUp")
    public String signUp(Member member) {
        boolean result= memberService.joinUser(member);
        if(result==true){
            return "redirect:/login"; //로그인 구현 예정
        }
        return "signUp_denied";
    }
}