package TodoListforGDSC.demo.TaskService;

import TodoListforGDSC.demo.TaskEntity.TaskEntity;

import java.util.List;

public interface ServiceInterface {
    public TaskEntity postTask(TaskEntity task);
    public List<TaskEntity> showAllTask();

    public void deleteTask(Long id);
    public TaskEntity updateTask(TaskEntity task);
}
