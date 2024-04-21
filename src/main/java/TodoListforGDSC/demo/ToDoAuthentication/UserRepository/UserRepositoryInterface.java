package TodoListforGDSC.demo.ToDoAuthentication.UserRepository;

import TodoListforGDSC.demo.ToDoAuthentication.UserEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryInterface extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
}
