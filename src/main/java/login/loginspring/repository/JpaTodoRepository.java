package login.loginspring.repository;


import login.loginspring.domain.Todos;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class JpaTodoRepository implements TodoRepository{
    private final EntityManager em;

    public JpaTodoRepository(EntityManager em) {this.em = em;}

    @Override
    public Todos save(Todos todos) {
        em.persist(todos);
        return todos;
    }

    @Override
    public Todos remove(Todos todos) {
        em.remove(todos);
        return todos;
    }

    @Override
    public Optional<Todos> findById(Integer id) {
        Todos todos = em.find(Todos.class, id);
        return Optional.ofNullable(todos);
    }

    @Override
    public List<Todos> findByUserId(String userId) {
        List<Todos> result = em.createQuery("select t from todos t where t.userId = :userId", Todos.class).setParameter("userId",userId).getResultList();
        return result;
    }

    @Override
    public List<Todos> findByGoalId(Integer goalId) {
        List<Todos> result = em.createQuery("select t from todos t where t.goalId = :goalId", Todos.class).setParameter("goalId",goalId).getResultList();
        return result;
    }

    @Override
    public List<Todos> findByDate(Date date) {
        List<Todos> result = em.createQuery("select t from todos t where t.date = :date", Todos.class).setParameter("date",date).getResultList();
        return result;
    }

    @Override
    public List<Todos> findByOrderNum(Integer orderNum) {
        List<Todos> result = em.createQuery("select t from todos t where t.order_num = :user_id", Todos.class).setParameter("order_num",orderNum).getResultList();
        return result;
    }

    @Override
    public List<Todos> findAll() {
        List<Todos> result = em.createQuery("select t from todos t", Todos.class).getResultList();
        return result;
    }

    @Override
    public void deleteById(Integer id){
        Todos todos = em.find(Todos.class, id);
        em.remove(todos);
    }

}
