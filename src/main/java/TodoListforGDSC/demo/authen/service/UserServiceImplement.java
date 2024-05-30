package TodoListforGDSC.demo.authen.service;

import TodoListforGDSC.demo.authen.controller.LoginRequest;
import TodoListforGDSC.demo.authen.controller.LogoutRequest;
import TodoListforGDSC.demo.authen.controller.RegisterRequest;

import TodoListforGDSC.demo.authen.entity.JwtEntity;
import TodoListforGDSC.demo.authen.repository.JwtRepositoryInterface;
import TodoListforGDSC.demo.share.UserNotFoundException;
import TodoListforGDSC.demo.authen.entity.UserEntity;
import TodoListforGDSC.demo.authen.repository.UserRepositoryInterface;
import TodoListforGDSC.demo.todolist.ToDoBodyResponse.ToDoResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@Service
public class UserServiceImplement implements UserServiceInterface {
    @Autowired
    UserRepositoryInterface userRepositoryInterface;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private tokenService tokenService;
    @Autowired
    private JwtRepositoryInterface jwtRepositoryInterface;

    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authenticationRequest =
                    UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authenticationResponse =
                    this.authenticationManager.authenticate(authenticationRequest);
            UserEntity user = (UserEntity) userService.loadUserByUsername(loginRequest.getUsername());
            JwtEntity jwtEntity = new JwtEntity();
            String token =jwtService.generateToken(user);
            Optional<JwtEntity> userTokenOptional = jwtRepositoryInterface.findByAccountId(user.getId());
            JwtEntity userToken;
            if (userTokenOptional.isPresent()) {
                userToken =userTokenOptional.get();
                userToken.setJwt(token);
            } else {
                userToken = new JwtEntity();
                userToken.setAccountId(user.getId());
                userToken.setJwt(token);
            }
            jwtRepositoryInterface.save(userToken);
            return ToDoResponseHandler.ToDoResponseBody("Login Successfully"
                    , "SUCCESS"
                    , token
                    , HttpStatus.OK);
        } catch (UsernameNotFoundException ex) {
            throw new UserNotFoundException();
        }
    }

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
    public ResponseEntity<Object> logout(@RequestHeader("Authorization") String token){
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        Optional<JwtEntity> optionalJwtEntity = jwtRepositoryInterface.findByJwt(token);
        if(optionalJwtEntity.isPresent()){
            tokenService.deleteToken(optionalJwtEntity);
            SecurityContextHolder.clearContext();return ToDoResponseHandler.ToDoResponseBody("Logout successfully"
                    , "SUCCESS"
                    , null
                    , HttpStatus.OK);
        }
        return ToDoResponseHandler.ToDoResponseBody("Invalid token"
                , "ERROR"
                , null
                , HttpStatus.BAD_REQUEST);

    }
}
