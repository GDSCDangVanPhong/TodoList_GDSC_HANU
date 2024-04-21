package TodoListforGDSC.demo.ToDoList.ToDoService;

import TodoListforGDSC.demo.ToDoList.ToDoEntity.ToDoEntity;
import TodoListforGDSC.demo.ToDoList.ToDoRepository.TodoRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@org.springframework.stereotype.Service
public class TodoServiceImplement implements TodoServiceInterface {
    @Autowired
    private final TodoRepository ToDoRepositoryInterface;

    public ToDoEntity postTask(ToDoEntity task) {
        return ToDoRepositoryInterface.save(task);
    }

    public List<ToDoEntity> showAllTask() {
        return ToDoRepositoryInterface.findAll();
    }

    public void deleteTask(Long id){
        ToDoRepositoryInterface.deleteById(id);
    }

    public ToDoEntity updateTask(ToDoEntity task, Long id) {
        task.setId(id);
        return ToDoRepositoryInterface.save(task);
    }

    public boolean exist(Long id) {
        return ToDoRepositoryInterface.existsById(id);
    }


}
