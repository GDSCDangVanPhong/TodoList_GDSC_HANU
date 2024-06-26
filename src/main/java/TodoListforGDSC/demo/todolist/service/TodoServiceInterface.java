package TodoListforGDSC.demo.todolist.service;

import TodoListforGDSC.demo.todolist.entity.CategoryEntity;
import TodoListforGDSC.demo.todolist.entity.ToDoEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface TodoServiceInterface {
    ToDoEntity postTask(ToDoEntity task);

    List<ToDoEntity> searchTask(String categoryName,String title, LocalDateTime createdBefore, LocalDateTime createdAfter, String status, String sortDirection);

    void deleteTask(Long id);

    ToDoEntity updateTask(ToDoEntity task, Long id);

    boolean exist(Long id);


    CategoryEntity createCategory(CategoryEntity category);
    List<ToDoEntity> getTasksByCategoryName(String categoryName);
}
