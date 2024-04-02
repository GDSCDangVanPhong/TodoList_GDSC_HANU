package TodoListforGDSC.demo.ToDoException;

import org.springframework.http.HttpStatus;


public class ToDoErrorResponse {
    private HttpStatus httpStatus;
    private String message;


    public ToDoErrorResponse(HttpStatus httpStatus, String message) {
    }
}
