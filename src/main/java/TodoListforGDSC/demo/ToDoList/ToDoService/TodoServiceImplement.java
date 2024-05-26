package TodoListforGDSC.demo.ToDoList.ToDoService;

import TodoListforGDSC.demo.ToDoList.ToDoEntity.ToDoEntity;
import TodoListforGDSC.demo.ToDoList.ToDoRepository.TaskSpecification;
import TodoListforGDSC.demo.ToDoList.ToDoRepository.TodoRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
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

    public List<ToDoEntity> searchTask(String title,
                                       LocalDateTime createdBefore,
                                       LocalDateTime createdAfter,
                                       String status) {
        Specification<ToDoEntity> spec =Specification.where(null);
        if (title != null && !title.isEmpty()) {
            spec = spec.and(TaskSpecification.hasTitle(title));
        }
        if (status != null && !status.isEmpty()) {
            spec = spec.and(TaskSpecification.withStatus(status));
        }
        if (createdAfter != null) {
            spec = spec.and(TaskSpecification.createdAfter(createdAfter));
        }
        if (createdBefore != null) {
            spec = spec.and(TaskSpecification.createdBefore(createdBefore));
        }
        return ToDoRepositoryInterface.findAll(spec);

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
