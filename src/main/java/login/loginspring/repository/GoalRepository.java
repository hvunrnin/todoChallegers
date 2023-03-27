package login.loginspring.repository;


import login.loginspring.domain.Goals;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface GoalRepository {
    Goals save(Goals goals);

    Goals remove(Goals goals);

    Optional<Goals> findById(Integer id);
    List<Goals> findByUserId(String userId);
    Optional<Goals> findByOrderNum(Integer orderNum);
    Optional<Goals> findByCategory(String category);
    List<Goals> findAll();
}
