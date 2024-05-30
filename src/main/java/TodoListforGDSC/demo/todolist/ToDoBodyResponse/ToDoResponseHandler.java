package TodoListforGDSC.demo.todolist.ToDoBodyResponse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Getter
@Setter
public class ToDoResponseHandler {
    private String message;
    private Object data;

    private String code;


    public static ResponseEntity<Object> ToDoResponseBody(String msg, String code, Object TodoObject, HttpStatus httpStatus) {
        Map<String, Object> ToDoResponse = new LinkedHashMap<>();
        ToDoResponse.put("code: ", code);
        ToDoResponse.put("data: ", TodoObject);
        ToDoResponse.put("message: ", msg);
        return new ResponseEntity<>(ToDoResponse, httpStatus);
    }


}
