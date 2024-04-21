package TodoListforGDSC.demo.ToDoAuthentication.AuthenticationController;


import TodoListforGDSC.demo.ToDoAuthentication.Exception.UserNotFoundException;
import TodoListforGDSC.demo.ToDoAuthentication.Jwt.JwtService;
import TodoListforGDSC.demo.ToDoAuthentication.UserEntity.UserEntity;
import TodoListforGDSC.demo.ToDoAuthentication.UserEntity.UserService;
import TodoListforGDSC.demo.ToDoAuthentication.UserRepository.UserRepositoryInterface;
import TodoListforGDSC.demo.ToDoList.ToDoBodyResponse.ToDoResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final ToDoResponseHandler toDoResponseHandler;
    private final JwtService jwtService;
    private final UserService userService;
    @Autowired
    private UserRepositoryInterface userRepositoryInterface;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authenticationRequest =
                    UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authenticationResponse =
                    this.authenticationManager.authenticate(authenticationRequest);
            UserEntity user = (UserEntity) userService.loadUserByUsername(loginRequest.getUsername());
            return ToDoResponseHandler.ToDoResponseBody("Login Successfully"
                    , "SUCCESS"
                    , jwtService.generateToken(user)
                    , HttpStatus.OK);
        } catch (UsernameNotFoundException ex) {
            throw new UserNotFoundException();
        }

    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setEmail(request.getEmail());
        userRepositoryInterface.save(user);
        return ToDoResponseHandler.ToDoResponseBody("Registration successfully"
                , "SUCCESS"
                , user
                , HttpStatus.CREATED);
    }


}
