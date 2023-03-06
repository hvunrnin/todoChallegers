package login.loginspring.controller;

import login.loginspring.domain.Member;
import login.loginspring.service.CalenderService;
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
    LocalDate now = LocalDate.now();
    int current_month = now.getMonthValue();
    int current_year = now.getYear();

    @Autowired
    public TodoController(CalenderService calenderService) {
        this.calenderService = calenderService;
    }


    @RequestMapping("/todolist")
    public String userAccess(Model model, Authentication authentication){
        Member member = (Member) authentication.getPrincipal();
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
}
