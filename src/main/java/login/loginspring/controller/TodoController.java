package login.loginspring.controller;

import login.loginspring.domain.Member;
import login.loginspring.domain.updateMember;
import login.loginspring.service.CalenderService;
import login.loginspring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class TodoController {
    private final CalenderService calenderService;
    private final MemberService memberService;
    LocalDate now = LocalDate.now();
    int current_month = now.getMonthValue();
    int current_year = now.getYear();

    @Autowired
    public TodoController(CalenderService calenderService, MemberService memberService) {
        this.calenderService = calenderService;
        this.memberService = memberService;
    }


    @RequestMapping("/todolist")
    public String userAccess(Model model, Authentication authentication){
        System.out.println("1");
        Member member = (Member) authentication.getPrincipal(); //로그인된 사용자 정보
        System.out.println(member.getUserName());
        model.addAttribute("name", member.getUserName());

        ArrayList<String> cal = calenderService.changeYearMonth(current_year, current_month);
        ArrayList<String> h_cal = calenderService.renderCalender(cal);
        model.addAttribute("h_cal", h_cal);
        model.addAttribute("year", current_year);
        model.addAttribute("month", current_month);

        return "todolist";
    }


    @PostMapping("/todolist")
    public String ButtonControl(Member member, Model model, DiffController diff){
//      String name = member.getUserName();
        System.out.println("2");
        int differ = Integer.parseInt(diff.getDiff());
        int[] date = calenderService.changeMonth(current_year, current_month, differ);
        current_year = date[0];
        current_month = date[1];
        return "redirect:/todolist"; //접근 html
    }

    @GetMapping("/profile_edit")
    public String ProfileEdit(Model model){
        return "profile_edit";
    }

    @PostMapping("/profile_edit")
    public String ProfileUpdate (updateMember member){
        memberService.updateUser(member);
        return "redirect:/todolist";
    }
}
