package TodoListforGDSC.demo.TaskService;

import TodoListforGDSC.demo.TaskEntity.TaskEntity;
import TodoListforGDSC.demo.TaskRepository.RepoInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceTodoImplement implements ServiceInterface {
    @Autowired
    private RepoInterface repoInterface;
    public TaskEntity postTask(TaskEntity task){
        return repoInterface.save(task);
    }
    public List<TaskEntity> showAllTask(){
        return repoInterface.findAll();
    }

    public void deleteTask(Long id){
        repoInterface.deleteById(id);
    }
    public TaskEntity updateTask(TaskEntity task){
        return  repoInterface.save(task);
    }


}
