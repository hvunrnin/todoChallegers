package login.loginspring.repository;

import login.loginspring.domain.Challenge;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Transactional
public interface ChallengeRepository{
    Challenge save(Challenge challenge);
    List<Challenge> findAll();
    Optional<Challenge> findByTitle(String title);
    void delete(Challenge challenge);
    void deleteByTitle(String title);
}
