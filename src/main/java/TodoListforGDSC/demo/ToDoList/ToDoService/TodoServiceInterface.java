package TodoListforGDSC.demo.ToDoList.ToDoService;

import TodoListforGDSC.demo.ToDoList.ToDoEntity.ToDoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoServiceInterface {
    ToDoEntity postTask(ToDoEntity task);

    List<ToDoEntity> showAllTask();

    void deleteTask(Long id);

    ToDoEntity updateTask(ToDoEntity task, Long id);

    boolean exist(Long id);
}
