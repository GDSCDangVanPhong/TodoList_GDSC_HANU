package TodoListforGDSC.demo.ToDoController;

import TodoListforGDSC.demo.ToDoEntity.ToDoEntity;
import TodoListforGDSC.demo.ToDoException.TaskNotFoundException;
import TodoListforGDSC.demo.ToDoService.TodoServiceInterface;
import TodoListforGDSC.demo.share.ToDoResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/todo")
public class ToDoController {

    @Autowired
    private TodoServiceInterface todoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> getAllTask() {
        return ToDoResponseHandler.ToDoResponseBody("Get all tasks successfully", "SUCCESS", todoService.showAllTask(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> postNew(@RequestBody ToDoEntity task) {
        todoService.postTask(task);
        return ResponseEntity.ok("");

    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> updateTask(@RequestBody ToDoEntity task, @PathVariable Long id) {
        if (!todoService.exist(id)) {
            throw new TaskNotFoundException("The task with ID :" + id + " is not existing !");
        } else {
            todoService.updateTask(task, id);
            return ResponseEntity.ok("Update task successfully!");
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> deleteTask(@PathVariable Long id) {
        if (!todoService.exist(id)) {
            throw new TaskNotFoundException("The task with ID :" + id + " is not existing !");
        } else {
            todoService.deleteTask(id);
            return ResponseEntity.ok("Delete task successfully !");
        }
    }


}