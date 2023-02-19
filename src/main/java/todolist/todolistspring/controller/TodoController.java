package todolist.todolistspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import todolist.todolistspring.service.CalenderService;

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

    @GetMapping("todolist") //접근url
    public String login(@RequestParam("id") String id, Model model) {
        model.addAttribute("id", id);

        ArrayList<String> cal = calenderService.changeYearMonth(current_year, current_month);
        ArrayList<String> h_cal = calenderService.renderCalender(cal);
        model.addAttribute("h_cal", h_cal);
        model.addAttribute("year", current_year);
        model.addAttribute("month", current_month);

        return "todolist";
    }

//    @GetMapping("calender") //접근 url
//    public String calenderLoad(Model model) {
//        ArrayList<String> cal = calenderService.changeYearMonth(current_year, current_month);
//        ArrayList<String> h_cal = calenderService.renderCalender(cal);
//        model.addAttribute("h_cal", h_cal);
//        model.addAttribute("year", current_year);
//        model.addAttribute("month", current_month);
//        return "calender"; //접근 html
//    }

    @PostMapping("todolist")
    public String ButtonControl(@RequestParam("id") String id, Model model, DiffController diff){
        int differ = Integer.parseInt(diff.getDiff());
        int[] date = calenderService.changeMonth(current_year, current_month, differ);
        current_year = date[0];
        current_month = date[1];
        return "redirect:/todolist?id=so0biin"; //접근 html
    }

    @GetMapping("hello") //접근url
    public String hello(Model model) {
        return "hello";
    }
}
