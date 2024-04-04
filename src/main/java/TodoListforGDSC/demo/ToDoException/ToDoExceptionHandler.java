package TodoListforGDSC.demo.ToDoException;

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
        return ToDoErrorResponse.ToDoResponseBody("Task not existing!", "NOT_FOUND", null, HttpStatus.NOT_FOUND);
    }


}