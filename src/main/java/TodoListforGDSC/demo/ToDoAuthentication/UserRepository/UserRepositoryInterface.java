package TodoListforGDSC.demo.ToDoAuthentication.UserRepository;

import TodoListforGDSC.demo.ToDoAuthentication.UserEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public interface UserRepositoryInterface extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
}
