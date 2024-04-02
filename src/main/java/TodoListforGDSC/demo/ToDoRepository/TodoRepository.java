package TodoListforGDSC.demo.ToDoRepository;

import TodoListforGDSC.demo.ToDoEntity.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<ToDoEntity, Long> {

}

