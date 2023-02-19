package todo_challenger.todo_challengerspring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import todo_challenger.todo_challengerspring.domain.Member;
import todo_challenger.todo_challengerspring.repository.MemberRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        /*Member member = new Member();
        member.setName("spring");*/
        Member member1 = new Member();
        member1.setEmail("333@naver.com");
        member1.setPw("333");

        Member member2 = new Member();
        member1.setEmail("444@naver.com");
        member1.setPw("444");

        //when
        //Long saveId = memberService.join(member);
        Long saveId1 = memberService.join(member1);
        Long saveId2 = memberService.join(member2);

        //then
        //Member findMember = memberService.findOne(saveId).get();
        //assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 로그인(){

        Member member1 = new Member();
        member1.setEmail("333@naver.com");
        member1.setPw("333");

        Member member2 = new Member();
        member1.setEmail("444@naver.com");
        member1.setPw("444");

        //when
        //Long saveId = memberService.join(member);
        Long saveId1 = memberService.join(member1);
        Long saveId2 = memberService.join(member2);

        Optional<Member> findMember = memberRepository.findByEmail(member1.getEmail());
        //Optional<Member> match = memberRepository.matchPw(member.getPw());

        if(findMember.isPresent()){
            if(findMember.get().getPw().equals(member2.getPw())) {
                System.out.println(true);
            }
            else {
                System.out.println(false);
            }
        }
        else {
            System.out.println(false);
        }

        //System.out.println("???????? " + findMember.get());
    }
}
