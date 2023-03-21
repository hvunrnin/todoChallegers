package login.loginspring.controller;

import login.loginspring.domain.Challenge;
import login.loginspring.domain.Member;
import login.loginspring.service.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class ChallengeController {
    private final ChallengeService challengeService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public ChallengeController(ChallengeService challengeService){
        this.challengeService = challengeService;
    }
    @GetMapping("/challenges/new")
    public String ChallengeInputForm(){
        return "Challenge/createChallenge";
    }

    @PostMapping("/challenges/new")
    public String create(ChallengeInputForm form, Authentication authentication) {

        Member member = (Member) authentication.getPrincipal(); //로그인한 사용자 정보
        String memberId = member.getUserId();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Challenge challenge = new Challenge();
        challenge.setUserId(memberId);
        challenge.setTitle(form.getTitle());

        try {
            challenge.setStart_date(formatter.parse(form.getStart_date()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            challenge.setEnd_date(formatter.parse(form.getEnd_date()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        challenge.setMemo(form.getMemo());

        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getUsername(), member.getUserRpw()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        if(challengeService.checkTitle(challenge)) {
            return "Challenge/createChallenge_fail";
        }
        else{
            challengeService.make(challenge);
            return "redirect:/todolist";
        }
    }

    @GetMapping("/challenge/{no}")
    public String detail(@PathVariable("no") String no, Model model){
        Challenge challenge = challengeService.getPost(no);

        model.addAttribute("challenge", challenge);
        return "Challenge/challengeList_detail";
    }

    @DeleteMapping("/challenge/{no}")
    public String delete(@PathVariable("no") String no){
        challengeService.deleteChallenge(no);
        return "redirect:/todolist";
    }
}
