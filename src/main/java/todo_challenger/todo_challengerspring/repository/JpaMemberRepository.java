package todo_challenger.todo_challengerspring.repository;

import todo_challenger.todo_challengerspring.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }


    @Override
    public Optional<Member> findByEmail(String email) { /** 이건 이메일이 존재하는지 확인하는 것*/
        List<Member> result = em.createQuery("select m from Member m where m.email = :email", Member.class)
                .setParameter("email", email).getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();

        return result.stream().findAny();
    }

    @Override
    public Optional<Member> matchPw(String email) { /**이건 위에서 받아온 이메일의 회원의 비밀번호 받아오는 것*/

        List<Member> result = em.createQuery("select m.pw from Member m where m.email = :email", Member.class)
                .setParameter("email", email).getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
