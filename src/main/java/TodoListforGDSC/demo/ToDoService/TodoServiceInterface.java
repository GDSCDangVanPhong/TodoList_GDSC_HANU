package TodoListforGDSC.demo.ToDoService;

import TodoListforGDSC.demo.ToDoEntity.ToDoEntity;

import java.util.List;

public interface TodoServiceInterface {
    ToDoEntity postTask(ToDoEntity task);

    List<ToDoEntity> showAllTask();

    void deleteTask(Long id);

    ToDoEntity updateTask(ToDoEntity task, Long id);

    boolean exist(Long id);
}
