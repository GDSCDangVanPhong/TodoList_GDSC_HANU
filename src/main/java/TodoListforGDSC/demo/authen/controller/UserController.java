package TodoListforGDSC.demo.authen.controller;


import TodoListforGDSC.demo.authen.repository.UserRepositoryInterface;
import TodoListforGDSC.demo.authen.service.JwtService;
import TodoListforGDSC.demo.authen.service.UserServiceInterface;
import TodoListforGDSC.demo.todolist.ToDoBodyResponse.ToDoResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final ToDoResponseHandler toDoResponseHandler;
    private  JwtService jwtService;

    @Autowired
    private UserRepositoryInterface userRepositoryInterface;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserServiceInterface userServiceInterface;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        return userServiceInterface.login(loginRequest);

    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
        return userServiceInterface.register(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logout(@RequestHeader("Authorization") String token){

        return userServiceInterface.logout(token);    }



}
