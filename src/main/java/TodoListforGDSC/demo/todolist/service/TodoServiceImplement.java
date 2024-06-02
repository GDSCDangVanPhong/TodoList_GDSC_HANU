package TodoListforGDSC.demo.todolist.service;

import TodoListforGDSC.demo.todolist.entity.CategoryEntity;
import TodoListforGDSC.demo.todolist.entity.ToDoEntity;
import TodoListforGDSC.demo.todolist.repository.CategoryRepository;
import TodoListforGDSC.demo.todolist.repository.TaskSpecification;
import TodoListforGDSC.demo.todolist.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@org.springframework.stereotype.Service
public class TodoServiceImplement implements TodoServiceInterface {
    @Autowired
    private final TodoRepository ToDoRepositoryInterface;
    @Autowired
    private  CategoryRepository categoryRepository;

    public ToDoEntity postTask(ToDoEntity task) {
        return ToDoRepositoryInterface.save(task);
    }
    public CategoryEntity createCategory(CategoryEntity category) {
        return categoryRepository.save(category);
    }



    public List<ToDoEntity> showAllTask(){
        return ToDoRepositoryInterface.findAll();
    }
    public List<ToDoEntity> getTasksByCategoryName(String categoryName) {
        Optional<CategoryEntity> category = categoryRepository.findByName(categoryName);
        if (category.isPresent()) {
            return ToDoRepositoryInterface.findByCategoryName(categoryName);
        } else {
            throw new IllegalArgumentException("Category not found");
        }
    }

    public List<ToDoEntity> searchTask(String categoryName,
                                        String title,
                                       LocalDateTime createdBefore,
                                       LocalDateTime createdAfter,
                                       String status,
                                       String sortDirection) {
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
        Sort sort = Sort.by(Sort.Direction.ASC,"time");
        if("desc".equalsIgnoreCase(sortDirection)){
            sort= Sort.by(Sort.Direction.DESC,"time");
        }
        return ToDoRepositoryInterface.findAll(spec, sort);

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
