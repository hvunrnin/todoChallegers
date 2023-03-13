package login.loginspring.service;


import login.loginspring.domain.Member;
import login.loginspring.domain.updateMember;
import login.loginspring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Transactional
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date localTime = new Date();

//    회원가입 시 db에 회원 저장
    @Transactional
    public void joinUser(Member member){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setUserRpw(member.getUserPw());
        member.setUserPw(passwordEncoder.encode(member.getPassword()));
        member.setUserAuth("ROLE_USER");
        member.setAppendDate(localTime);
        member.setUpdateDate(localTime);
        memberRepository.save(member);
    }

    @Transactional
    public Member updateUser(updateMember UpdateMember){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userId = ((UserDetails) principal).getUsername();
        Member member = memberRepository.findById(userId).get();
        member.setUserName(UpdateMember.getUpdateName());
        member.setUserMessage(UpdateMember.getUpdateMessage());
        memberRepository.save(member);
        System.out.println(member.getUserName());

        return member;

    }

    @Override
    public Member loadUserByUsername(String userId) throws UsernameNotFoundException {

        Optional<Member> optionalMember = memberRepository.findById(userId);
        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원정보가 존재하지 않습니다");
        }

        Member member = optionalMember.get();

        return member;
    }
}

