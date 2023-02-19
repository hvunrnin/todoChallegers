package todo_challenger.todo_challengerspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import todo_challenger.todo_challengerspring.domain.Member;
import todo_challenger.todo_challengerspring.service.MemberService;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

   @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        member.setPw(form.getPw());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/login/")
    public String loginForm(){
        return "members/login";
    }

    @PostMapping("/login/")
    public String login(LoginForm form) {
        Member member = new Member();
        member.setEmail(form.getEmail());
        member.setPw(form.getPw());

        if(memberService.checkEmail(member)) {
            if(memberService.matchPw(member)) {
                return "loginSuccess";
            }
            return "loginFail_noMatch";
        }
        return "loginFail_noExist";
    }

}
