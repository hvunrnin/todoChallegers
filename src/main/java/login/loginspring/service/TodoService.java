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

    public void join(Todos todos){
        todoRepository.save(todos);
    }
    public void delete(Integer todoId) {
        todoRepository.deleteById(todoId);

    }
    public void deleteGoal(Integer goalId) { //goal을 삭제하면 goal하위의 todo도 삭제되야하기 떄문
        System.out.println("in todo delete " + goalId);
        List<Todos> todos = findContents(goalId);
        for(int i = 0; i < todos.size(); i++) {
            todoRepository.remove(todos.get(i));
        }
    }

    public List<Todos> findContents(Integer goalId) {return todoRepository.findByGoalId(goalId).stream().toList();}
    public List<Todos> findTodos() {
        return todoRepository.findAll();
    }
    public Optional<Todos> findById(Integer id){
        return todoRepository.findById(id);
    }
}
