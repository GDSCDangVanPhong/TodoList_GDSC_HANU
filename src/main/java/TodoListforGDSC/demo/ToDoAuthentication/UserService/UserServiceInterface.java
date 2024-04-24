package TodoListforGDSC.demo.ToDoAuthentication.UserService;

import TodoListforGDSC.demo.ToDoAuthentication.AuthenticationController.LoginRequest;
import TodoListforGDSC.demo.ToDoAuthentication.AuthenticationController.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserServiceInterface {

    ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest);

    ResponseEntity<Object> register(@RequestBody RegisterRequest registerRequest);
}
