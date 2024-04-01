package TodoListforGDSC.demo.TaskController;

import TodoListforGDSC.demo.TaskEntity.TaskEntity;
import TodoListforGDSC.demo.TaskService.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
class ApiExceptionHandler extends RuntimeException {
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException(TaskEntity task) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task with ID: " + task.getId() + "is not existed !");
    }

    static class TaskNotFoundException extends RuntimeException {
    }

}
@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private ServiceInterface ServiceResponse;


    @GetMapping("/getList")
    public ResponseEntity<?> getAllTask() {
        List<TaskEntity> tasks = ServiceResponse.showAllTask();
        if (tasks.isEmpty()) {
            return ResponseEntity.ok("No task found!");
        } else {
        return ResponseEntity.ok(ServiceResponse.showAllTask());
        }
    }
    @PostMapping("/postTask")
    public ResponseEntity<?> postNew(@RequestBody TaskEntity task) {

        return ResponseEntity.ok(ServiceResponse.postTask(task));

    }

    @PutMapping("/update/{id}")
    @ExceptionHandler
    public ResponseEntity<TaskEntity> updateTask(@RequestBody TaskEntity task, @PathVariable Long id) {
        if (!ServiceResponse.exist(id)) {
            throw new ApiExceptionHandler.TaskNotFoundException();
        } else {
            return ResponseEntity.ok(ServiceResponse.updateTask(task, id));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        if (!ServiceResponse.exist(id)) {
            throw new ApiExceptionHandler.TaskNotFoundException();
        } else {
            ServiceResponse.deleteTask(id);
            return ResponseEntity.ok(true);
        }
    }


}
