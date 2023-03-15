package login.loginspring.service;

import login.loginspring.domain.Goals;
import login.loginspring.repository.GoalRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class GoalService {
    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Integer join(Goals goals){
        goalRepository.save(goals);
        return goals.getId();
    }

    public List<Goals> findGoals() {
        return goalRepository.findAll(); //find by id로 해야함.
    }
//    public List<Goals> findOrderedGoals() { return goalRepository.findAll(Sort.by(Sort.Direction.ASC, "orderNum"));}

    public Optional<Goals> findOne(Integer id){
        return goalRepository.findById(id);
    }

 //   public List<Goals> findOrderedGoals() {return goalRepository.findGoalsOrderBYOrderNum();}


}
