package TodoListforGDSC.demo.TaskRepository;
import TodoListforGDSC.demo.TaskEntity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoInterface extends JpaRepository<TaskEntity, Long> {

}

