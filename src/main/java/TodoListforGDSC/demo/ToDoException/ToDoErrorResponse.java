package TodoListforGDSC.demo.ToDoException;

import org.springframework.http.HttpStatus;


public class ToDoErrorResponse {
    private final HttpStatus httpStatus;
    private final String message;


    public ToDoErrorResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
