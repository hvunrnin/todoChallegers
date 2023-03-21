package login.loginspring.repository;

import login.loginspring.domain.Challenge;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional
public class JpaChallengeRepository implements ChallengeRepository{
    private final EntityManager em2;

    public JpaChallengeRepository(EntityManager em2) {
        this.em2 = em2;
    }

    @Override
    public Challenge save(Challenge challenge) {
        em2.persist(challenge);
        return challenge;
    }

    @Override
    public List<Challenge> findAll() {
        return em2.createQuery("select c from Challenge c order by c.end_date asc", Challenge.class)
                .getResultList();
    }

    @Override
    public Optional<Challenge> findByTitle(String title) {
        List<Challenge> result = em2.createQuery("select c from Challenge c where c.title=:title", Challenge.class)
                .setParameter("title", title).getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Challenge> findById(String userId) {
        return em2.createQuery("select c from Challenge c where c.userId=:userId", Challenge.class)
                .setParameter("userId", userId).getResultList();
    }

    @Override
    public void delete(Challenge challenge) {
        em2.remove(challenge);
    }

    @Override
    public void deleteByTitle(String title) {
        delete(findByTitle(title).orElseThrow());
    }


}
