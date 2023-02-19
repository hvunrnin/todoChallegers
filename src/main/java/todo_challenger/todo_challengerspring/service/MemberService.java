package todo_challenger.todo_challengerspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo_challenger.todo_challengerspring.domain.Member;
import todo_challenger.todo_challengerspring.repository.MemberRepository;
import todo_challenger.todo_challengerspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     *회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByEmail(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이메일입니다");
                });
    }

    /**
     * 로그인
     */
    public boolean login(Member member)
    {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        //Optional<Member> match = memberRepository.matchPw(member.getPw());

        if(findMember.isPresent()){
            if(findMember.get().getPw().equals(member.getPw())) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
    public boolean checkEmail(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());

        if(findMember.isPresent()){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean matchPw(Member member){
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember.get().getPw().equals(member.getPw())) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *전체 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId); }
}
