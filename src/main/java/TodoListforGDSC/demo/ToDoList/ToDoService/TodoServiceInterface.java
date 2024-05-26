package TodoListforGDSC.demo.ToDoList.ToDoService;

import TodoListforGDSC.demo.ToDoList.ToDoEntity.ToDoEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface TodoServiceInterface {
    ToDoEntity postTask(ToDoEntity task);

    List<ToDoEntity> searchTask(String title, LocalDateTime createdBefore, LocalDateTime createdAfter, String status, String sortDirection);

    void deleteTask(Long id);

    ToDoEntity updateTask(ToDoEntity task, Long id);

    boolean exist(Long id);
}
