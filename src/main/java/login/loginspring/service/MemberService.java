package login.loginspring.service;


import login.loginspring.domain.Member;
import login.loginspring.domain.updateMember;
import login.loginspring.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService implements UserDetailsService {

    HttpServletRequest request;
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
        member.setUserPw(passwordEncoder.encode(member.getPassword()));
        member.setUserAuth("ROLE_USER");
        member.setAppendDate(localTime);
        member.setUpdateDate(localTime);
        memberRepository.save(member);
    }

    @Transactional
    public void updateUser(updateMember UpdateMmember){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String userId = ((UserDetails) principal).getUsername();
//        System.out.println(userId);
        Member member = memberRepository.findById(userId).get();
//        System.out.println(member.getUserName());
//        System.out.println(UpdateMmember.getUpdateName());
        member.setUserName(UpdateMmember.getUpdateName());
        memberRepository.save(member);

        System.out.println(member.getUserName());

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

