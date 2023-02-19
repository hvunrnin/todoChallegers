package todo_challenger.todo_challengerspring.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import todo_challenger.todo_challengerspring.domain.Member;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("sp");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }
}
