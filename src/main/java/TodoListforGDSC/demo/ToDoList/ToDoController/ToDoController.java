package TodoListforGDSC.demo.ToDoList.ToDoController;

import TodoListforGDSC.demo.ToDoList.ToDoBodyResponse.ToDoResponseHandler;
import TodoListforGDSC.demo.ToDoList.ToDoEntity.ToDoEntity;
import TodoListforGDSC.demo.share.TaskNotFoundException;
import TodoListforGDSC.demo.ToDoList.ToDoService.TodoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/todo")
public class ToDoController {

    @Autowired
    private TodoServiceInterface todoService;

    @GetMapping("/search")
    private ResponseEntity<Object> searchTask(@RequestParam(required = false) String title,
                                              @RequestParam(required = false) String createdBefore,
                                              @RequestParam(required = false) String createdAfter,
                                              @RequestParam(required = false )String status) {
        LocalDateTime createdBeforeDate = null;
        LocalDateTime createdAfterDate = null;

        if (createdBefore != null && !createdBefore.isEmpty()) {
            createdBeforeDate = LocalDateTime.parse(createdBefore, DateTimeFormatter.ISO_DATE_TIME);
        }
        if (createdAfter != null && !createdAfter.isEmpty()) {
            createdAfterDate = LocalDateTime.parse(createdAfter, DateTimeFormatter.ISO_DATE_TIME);
        }
        return ToDoResponseHandler.ToDoResponseBody("Get all tasks successfully", "SUCCESS", todoService.searchTask(title, createdBeforeDate, createdAfterDate, status), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> postNew(@RequestBody ToDoEntity task) {
        todoService.postTask(task);
        return ToDoResponseHandler.ToDoResponseBody("Post task successfully!", "SUCCESS", task.getId(), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> updateTask(@RequestBody ToDoEntity task, @PathVariable Long id) {
        if (!todoService.exist(id)) {
            throw new TaskNotFoundException();
        } else {
            todoService.updateTask(task, id);
            return ToDoResponseHandler.ToDoResponseBody("Update task successfully!", "SUCCESS", null, HttpStatus.CREATED);
        }
    }
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> deleteTask(@PathVariable Long id) {
        if (!todoService.exist(id)) {
            throw new TaskNotFoundException();
        } else {
            todoService.deleteTask(id);
            return ToDoResponseHandler.ToDoResponseBody("Delete task successfully", "SUCCESS", null, HttpStatus.OK);
        }
    }


}