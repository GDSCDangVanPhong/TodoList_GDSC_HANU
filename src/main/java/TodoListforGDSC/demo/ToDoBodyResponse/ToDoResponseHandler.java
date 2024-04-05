package TodoListforGDSC.demo.ToDoBodyResponse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ToDoResponseHandler {
    private String message;
    private Object data;

    private String code;


    public static ResponseEntity<Object> ToDoResponseBody(String msg, String code, Object TodoObject, HttpStatus httpStatus) {
        Map<String, Object> ToDoResponse = new HashMap<>();
        ToDoResponse.put("message: ", msg);
        ToDoResponse.put("data: ", TodoObject);
        ToDoResponse.put("code: ", code);
        return new ResponseEntity<Object>(ToDoResponse, httpStatus);
    }

}
