package TodoListforGDSC.demo.TaskService;

import TodoListforGDSC.demo.TaskEntity.TaskEntity;

import java.util.List;

public interface ServiceInterface {
    TaskEntity postTask(TaskEntity task);
    List<TaskEntity> showAllTask();

    void deleteTask(Long id);

    TaskEntity updateTask(TaskEntity task, Long id);

    boolean exist(Long id);
}
