package TodoListforGDSC.demo.todolist.repository;

import TodoListforGDSC.demo.todolist.entity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<ToDoEntity, Long>, JpaSpecificationExecutor<ToDoEntity> {

}

