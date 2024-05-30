package TodoListforGDSC.demo.authen.repository;

import TodoListforGDSC.demo.authen.entity.JwtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


import java.util.Optional;

@Component
public interface JwtRepositoryInterface extends JpaRepository<JwtEntity, Long> {
    Optional<JwtEntity> findByJwt(String token);
    Optional<JwtEntity> findByAccountId(Integer id);
}
