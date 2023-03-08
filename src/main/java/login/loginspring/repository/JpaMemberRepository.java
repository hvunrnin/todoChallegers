package login.loginspring.repository;


import login.loginspring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
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
    public Optional<Member> findById(String id) {
        List<Member> result = em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id", id).getResultList();
        return result.stream().findAny();
    }
//
//    @Override
//    public Optional<Member> findByName(String name) {
//        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
//                .setParameter("name", name).getResultList();
//        return result.stream().findAny();
//    }
//
//    @Override
//    public Optional<Member> matchPw(String id) {
//        List<Member> result = em.createQuery("select m.pw from Member m where m.id = :id", Member.class)
//                .setParameter("id", id).getResultList();
//        return result.stream().findAny();
//    }
//
//    @Override
//    public List<Member> findAll() {
//        return em.createQuery("select m from Member m", Member.class).getResultList();
//    }
//

}
