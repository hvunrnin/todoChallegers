package login.loginspring.repository;

import login.loginspring.domain.Todos;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository /*extends JpaRepository<Todos,Integer>*/ {
    Todos save(Todos todos);
    Todos remove(Todos todos);
    Optional<Todos> findById(Integer id);
    List<Todos> findByUserId(String userId);
    List<Todos> findByGoalId(Integer goalId);
    List<Todos> findByDate(Date date);
    List<Todos> findByOrderNum(Integer orderNum);
    List<Todos> findAll();
}
