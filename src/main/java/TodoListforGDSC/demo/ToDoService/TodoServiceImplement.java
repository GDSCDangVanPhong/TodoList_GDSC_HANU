package TodoListforGDSC.demo.ToDoService;

import TodoListforGDSC.demo.ToDoEntity.ToDoEntity;
import TodoListforGDSC.demo.ToDoRepository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class TodoServiceImplement implements TodoServiceInterface {
    @Autowired
    private TodoRepository repoInterface;

    public ToDoEntity postTask(ToDoEntity task) {
        return repoInterface.save(task);
    }

    public List<ToDoEntity> showAllTask() {
        return repoInterface.findAll();
    }

    public void deleteTask(Long id){
        repoInterface.deleteById(id);
    }

    public ToDoEntity updateTask(ToDoEntity task, Long id) {
        task.setId(id);
        return  repoInterface.save(task);
    }

    public boolean exist(Long id) {
        return repoInterface.existsById(id);
    }


}
