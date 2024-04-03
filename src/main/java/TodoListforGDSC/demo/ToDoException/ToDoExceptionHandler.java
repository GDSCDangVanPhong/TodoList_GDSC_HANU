package TodoListforGDSC.demo.ToDoException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ToDoExceptionHandler {
    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ToDoErrorResponse handleTaskNotFoundException(TaskNotFoundException exception) {
        return new ToDoErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

}
