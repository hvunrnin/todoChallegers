package login.loginspring.controller;

import login.loginspring.domain.Goals;
import login.loginspring.domain.Member;
import login.loginspring.service.GoalService;
import login.loginspring.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class GoalController {

    private final GoalService goalService;
    private final TodoService todoService;

    @Autowired
    public GoalController(GoalService goalService, TodoService todoService) {
        this.goalService = goalService;
        this.todoService = todoService;
    }

    @GetMapping("/goals")
    public String list(Model model, Authentication authentication) {
        Member member = (Member) authentication.getPrincipal(); //로그인한 사용자 정보
        String memberId = member.getUserId();
        List<Goals> goals = goalService.findGoalsByUserId(memberId);
        model.addAttribute("goals", goals);
        return "goals/goalsList"; // goals 폴더에 있는 goalsList.html 열기

    }

    @PostMapping("/goals")
    public String updateGoal(GoalForm form) { // 수정하고 확인을 누르면 여기로 와
        Optional<Goals> goals = goalService.findById(form.getId());
        goals.get().setCategory(form.getCategory());
        if(form.getDelete().equals("delete")) {
            todoService.deleteGoal(goals.get());
            goalService.delete(goals.get());
        }
        else { goalService.join(goals.get()); }

        return "redirect:/goals";
    }

    @GetMapping("/goals/new")
    public String createForm() {
        return "goals/createGoalForm";
    }

    @PostMapping("/goals/new")
    public String create(GoalForm form, Authentication authentication) {
        Member member = (Member) authentication.getPrincipal(); //로그인한 사용자 정보
        String memberId = member.getUserId();

        Goals goals = new Goals();

        goals.setUserId(memberId);
        goals.setOrderNum(3);

        goals.setCategory(form.getCategory());

        goalService.join(goals);
        return "redirect:/goals";
    }

    @RequestMapping("/goals/update")
    public String update(String category, Model model) {
        Optional<Goals> goal = goalService.findByCategory(category);
        model.addAttribute("goal", goal.get());
        return "goalsUpdate";
    }
}
