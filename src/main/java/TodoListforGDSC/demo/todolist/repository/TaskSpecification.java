package TodoListforGDSC.demo.todolist.repository;

import TodoListforGDSC.demo.todolist.entity.ToDoEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class TaskSpecification {

    public static Specification<ToDoEntity> hasTitle(String title){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"),"%"+ title +"%"));
    }
    public static Specification<ToDoEntity> withStatus(String status){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("status"),"%"+ status+"%"));
    }
    public static  Specification<ToDoEntity> createdBefore(LocalDateTime time){
        return((root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("time"), time));
    }public static  Specification<ToDoEntity> createdAfter(LocalDateTime time){
        return((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("time"), time));
    }

}
