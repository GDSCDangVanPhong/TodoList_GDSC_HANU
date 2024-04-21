package TodoListforGDSC.demo.ToDoList.ToDoController;

import TodoListforGDSC.demo.ToDoList.ToDoBodyResponse.ToDoResponseHandler;
import TodoListforGDSC.demo.ToDoList.ToDoEntity.ToDoEntity;
import TodoListforGDSC.demo.ToDoList.ToDoException.TaskNotFoundException;
import TodoListforGDSC.demo.ToDoList.ToDoService.TodoServiceInterface;
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