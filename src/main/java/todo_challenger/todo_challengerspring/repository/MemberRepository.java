package todo_challenger.todo_challengerspring.repository;

import todo_challenger.todo_challengerspring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByEmail(String email);
    Optional<Member> matchPw(String pw);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
