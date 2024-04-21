package TodoListforGDSC.demo.ToDoList.ToDoRepository;

import TodoListforGDSC.demo.ToDoList.ToDoEntity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<ToDoEntity, Long> {

}

