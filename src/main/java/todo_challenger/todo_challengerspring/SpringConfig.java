package todo_challenger.todo_challengerspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import todo_challenger.todo_challengerspring.repository.JpaMemberRepository;
import todo_challenger.todo_challengerspring.repository.MemberRepository;
import todo_challenger.todo_challengerspring.repository.MemoryMemberRepository;
import todo_challenger.todo_challengerspring.service.MemberService;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }
}
