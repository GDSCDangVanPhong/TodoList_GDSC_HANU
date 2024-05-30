package TodoListforGDSC.demo.share;


import TodoListforGDSC.demo.todolist.ToDoBodyResponse.ToDoResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ToDoExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleTaskNotFoundException() {
        return ToDoResponseHandler.ToDoResponseBody("Task not found", "NOT_FOUND", null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleUserNotFoundException() {
        return ToDoResponseHandler.ToDoResponseBody("User not found", "NOT_FOUND", null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JwtIsInvalidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> JwtNotValidException() {
        return ToDoResponseHandler.ToDoResponseBody("Invalid Jwt Token", "NOT_FOUND", null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JwtIsExpiredException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> JwtIsExpiredException() {
        return ToDoResponseHandler.ToDoResponseBody("Jwt is expired", "NOT_FOUND", null, HttpStatus.NOT_FOUND);
    }
}