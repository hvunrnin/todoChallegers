package todo_challenger.todo_challengerspring.repository;

import org.springframework.stereotype.Repository;
import todo_challenger.todo_challengerspring.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository{

    private Map<Long, Member> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); }

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.ofNullable(store.get(email)); }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) .findAny();
    }

    @Override
    public Optional<Member> matchPw(String pw) {
        return store.values().stream()
                .filter(member -> member.getPw().equals(pw)) .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore() {
        store.clear();
    }
}
