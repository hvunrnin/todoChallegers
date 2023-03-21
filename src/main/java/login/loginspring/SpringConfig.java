package login.loginspring;

import login.loginspring.repository.*;
import login.loginspring.service.ChallengeService;
import login.loginspring.service.GoalService;
import login.loginspring.service.MemberService;
import login.loginspring.service.TodoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {
    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    @Bean
    public GoalService goalService() { return new GoalService(goalRepository()); }
    @Bean
    public TodoService todoService() {return new TodoService(todoRepository());}
    @Bean
    public ChallengeService challengeService(){
        return new ChallengeService(challengeRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }

    @Bean
    public GoalRepository goalRepository(){ return new JpaGoalRepository(em);}
    @Bean
    public TodoRepository todoRepository(){ return new JpaTodoRepository(em);}
    @Bean
    public ChallengeRepository challengeRepository(){
        return new JpaChallengeRepository(em);
    }

}
