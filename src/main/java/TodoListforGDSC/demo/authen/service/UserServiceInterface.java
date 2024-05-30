package TodoListforGDSC.demo.authen.service;

import TodoListforGDSC.demo.authen.controller.LoginRequest;
import TodoListforGDSC.demo.authen.controller.LogoutRequest;
import TodoListforGDSC.demo.authen.controller.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface UserServiceInterface {

    ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest);

    ResponseEntity<Object> register(@RequestBody RegisterRequest registerRequest);
    ResponseEntity<Object> logout(@RequestHeader("Authorization") String token);
}
