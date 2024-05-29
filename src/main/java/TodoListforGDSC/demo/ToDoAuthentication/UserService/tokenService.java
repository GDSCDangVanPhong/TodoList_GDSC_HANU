package TodoListforGDSC.demo.ToDoAuthentication.UserService;

import TodoListforGDSC.demo.ToDoAuthentication.AuthenticationController.LogoutRequest;
import TodoListforGDSC.demo.ToDoAuthentication.UserEntity.JwtEntity;
import TodoListforGDSC.demo.ToDoAuthentication.UserRepository.JwtRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class tokenService {
    @Autowired
    private JwtRepositoryInterface jwtRepository;

    public void saveToken( String token){
        JwtEntity userToken = new JwtEntity();
        userToken.setJwt(token);
        jwtRepository.save(userToken);
    }
    public void deleteToken(LogoutRequest request){
        String username =request.getUsername();
        Optional<JwtEntity> jwtEntity = jwtRepository.findByUsername(username);
        if(jwtEntity.isPresent()){
            JwtEntity targetToken = jwtEntity.get();
            jwtRepository.delete(targetToken);
        }
    }
}
