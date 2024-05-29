package TodoListforGDSC.demo.ToDoAuthentication.UserRepository;

import TodoListforGDSC.demo.ToDoAuthentication.UserEntity.JwtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public interface JwtRepositoryInterface extends JpaRepository<JwtEntity, Long> {
    Optional<JwtEntity> findByUsername(String username);
}
