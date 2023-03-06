package login.loginspring.repository;

import login.loginspring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository{
    Member save(Member member);
    Optional<Member> findById(String id);
//    Optional<Member> findByNo(Integer no);
//    Optional<Member> matchPw(String pw);
//    Optional<Member> findByName(String name);
//    List<Member> findAll();
}
