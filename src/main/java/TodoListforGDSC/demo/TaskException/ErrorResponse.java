package TodoListforGDSC.demo.TaskException;

import org.springframework.http.HttpStatus;


public class ErrorResponse {
    private HttpStatus httpStatus;
    private String message;


    public ErrorResponse(HttpStatus httpStatus, String message) {
    }
}
