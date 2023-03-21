package login.loginspring.controller;

import login.loginspring.domain.Goals;
import login.loginspring.domain.Member;
import login.loginspring.domain.Todos;
import login.loginspring.service.GoalService;
import login.loginspring.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Controller
public class EasyInputController {

    private final GoalService goalService;
    private final TodoService todoService;

    @Autowired
    public EasyInputController(GoalService goalService, TodoService todoService) {
        this.goalService = goalService;
        this.todoService = todoService;
    }

    @GetMapping("/easyinput")
    public String list(Model model) {
        List<Goals> goals = goalService.findGoals();
        List<Todos> todos = todoService.findTodos();
        model.addAttribute("goals", goals);
        model.addAttribute("todos", todos);
        return "easyinput/easyinputList";
    }

    @PostMapping("/easyinput/new")
    public String afterinput(EasyInputForm form, Authentication authentication) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Todos todo = new Todos();

        Member member = (Member) authentication.getPrincipal(); //로그인한 사용자 정보
        String memberId = member.getUserId();

        todo.setUserId(memberId); // 로그인한 사용자의 id로 받아오기
        todo.setGoalId(Integer.valueOf(form.getGoalID()));
        todo.setDate(formatter.parse(form.getStartDate()));
        todo.setContent(form.getContent());

        todo.setStartDate(formatter.parse(form.getStartDate()));
        todo.setEndDate(formatter.parse(form.getEndDate()));

        todo.setIsChecked(0);

        /* isRepeat(weekday) default set == 1 */
        todo.setIsRepeatMon(1);
        todo.setIsRepeatTue(1);
        todo.setIsRepeatWed(1);
        todo.setIsRepeatThu(1);
        todo.setIsRepeatFri(1);
        todo.setIsRepeatSat(1);
        todo.setIsRepeatSun(1);

        /* everyweek */
        if(form.getEveryCheck().equals("everyweek")) {
            if(form.getIsRepeatMon() == null) {  todo.setIsRepeatMon(0); }
            if(form.getIsRepeatTue() == null) {  todo.setIsRepeatTue(0); }
            if(form.getIsRepeatWed() == null) {  todo.setIsRepeatWed(0); }
            if(form.getIsRepeatThu() == null) {  todo.setIsRepeatThu(0); }
            if(form.getIsRepeatFri() == null) {  todo.setIsRepeatFri(0); }
            if(form.getIsRepeatSat() == null) {  todo.setIsRepeatSat(0); }
            if(form.getIsRepeatSun() == null) {  todo.setIsRepeatSun(0); }
        }

        /* everymonth */
        else if(form.getEveryCheck().equals("everymonth")) {
            todo.setIsRepeatMon(0);
            todo.setIsRepeatTue(0);
            todo.setIsRepeatWed(0);
            todo.setIsRepeatThu(0);
            todo.setIsRepeatFri(0);
            todo.setIsRepeatSat(0);
            todo.setIsRepeatSun(0);
            todo.setRepeat_montly(form.getMonthCheck());
        }

        todoService.join(todo);

        return "redirect:/easyinput";
    }

    @RequestMapping("/easyinput/new")
    public String create(String category, Model model) {
        Optional<Goals> goal = goalService.findByCategory(category);
        model.addAttribute("goal", goal.get());
        return "easyinput/createEasyInputForm";
    }

    @RequestMapping("/easyinput/update")
    public String update(int todoId, Model model) {
        Optional<Todos> todo =  todoService.findById(todoId);
        model.addAttribute("todo", todo.get());
        return "easyinput/easyinputUpdate";
    }

    @PostMapping("/easyinput")
    public String updateback(EasyInputForm form, Authentication authentication) throws ParseException {// 수정하고 확인을 누르면 여기로 와
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Optional<Todos> todo = todoService.findById(Integer.valueOf(form.getId()));

        Member member = (Member) authentication.getPrincipal(); //로그인한 사용자 정보
        String memberId = member.getUserId();

        todo.get().setUserId(memberId); // 로그인한 사용자의 id로 받아오기
        todo.get().setGoalId(Integer.valueOf(form.getGoalID()));
        todo.get().setDate(formatter.parse(form.getStartDate()));
        todo.get().setContent(form.getContent());

        todo.get().setStartDate(formatter.parse(form.getStartDate()));
        todo.get().setEndDate(formatter.parse(form.getEndDate()));

        todo.get().setIsChecked(0);

        /* isRepeat(weekday) default set == 1 */
        todo.get().setIsRepeatMon(1);
        todo.get().setIsRepeatTue(1);
        todo.get().setIsRepeatWed(1);
        todo.get().setIsRepeatThu(1);
        todo.get().setIsRepeatFri(1);
        todo.get().setIsRepeatSat(1);
        todo.get().setIsRepeatSun(1);
        todo.get().setRepeat_montly(null);
        System.out.println("everycheck " + form.getEveryCheck());

        /* everyweek */
        if(form.getEveryCheck().equals("everyweek")) {
            System.out.println("in every week check");

            if(form.getIsRepeatMon() == null) {  todo.get().setIsRepeatMon(0); }
            if(form.getIsRepeatTue() == null) {  todo.get().setIsRepeatTue(0); }
            if(form.getIsRepeatWed() == null) {  todo.get().setIsRepeatWed(0); }
            if(form.getIsRepeatThu() == null) {  todo.get().setIsRepeatThu(0); }
            if(form.getIsRepeatFri() == null) {  todo.get().setIsRepeatFri(0); }
            if(form.getIsRepeatSat() == null) {  todo.get().setIsRepeatSat(0); }
            if(form.getIsRepeatSun() == null) {  todo.get().setIsRepeatSun(0); }
        }

        /* everymonth */
        else if(form.getEveryCheck().equals("everymonth")) {
            todo.get().setIsRepeatMon(0);
            todo.get().setIsRepeatTue(0);
            todo.get().setIsRepeatWed(0);
            todo.get().setIsRepeatThu(0);
            todo.get().setIsRepeatFri(0);
            todo.get().setIsRepeatSat(0);
            todo.get().setIsRepeatSun(0);
            todo.get().setRepeat_montly(form.getMonthCheck());
        }
        System.out.println("let's " + form.getDelete());
        if(form.getDelete().equals("delete")) {
            todoService.delete(todo.get().getId());
        }
        else { todoService.join(todo.get()); }

        return "redirect:/easyinput";
    }
}
