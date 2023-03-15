package login.loginspring.service;

import login.loginspring.domain.Todos;
import login.loginspring.repository.TodoRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Integer join(Todos todos){
        todoRepository.save(todos);
        return null;
    }

    public List<Todos> findContents(Integer goalId) {return todoRepository.findByGoalId(goalId).stream().toList();}
    public List<Todos> findTodos() {
        return todoRepository.findAll();
    }
    public Optional<Todos> findById(Integer id){
        return todoRepository.findById(id);
    }
}
