package login.loginspring.repository;

import login.loginspring.domain.Goals;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaGoalRepository implements GoalRepository {
    private final EntityManager em;

    public JpaGoalRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Goals save(Goals goals) {
        em.persist(goals);
        return goals;
    }

    @Override
    public Goals remove(Goals goals) {
        em.remove(goals);
        return goals;
    }

    @Override
    public Optional<Goals> findById(Integer id) {
        Goals goals = em.find(Goals.class, id);
        return Optional.ofNullable(goals);
    }

    @Override
    public Optional<Goals> findByOrderNum(Integer orderNum) {
        Goals goals = em.createQuery("select m from goals m where m.order_num = :order_num", Goals.class).setParameter("order_num", orderNum).getSingleResult();
        return Optional.ofNullable(goals);
    }

    @Override
    public Optional<Goals> findByCategory(String category) {
        List<Goals> result = em.createQuery("select m from goals m where m.category = :category", Goals.class).setParameter("category", category).getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Goals> findAll() {
        List<Goals> result = em.createQuery("select m from goals m", Goals.class).getResultList();
        return result;
    }

//    @Override
//    public List<Goals> findGoalsOrderBYOrderNum() {
//        List<Goals> result = em.createQuery("select m from goals m order by m.order_num",Goals.class).getResultList();
//        return result;
//    }
}
