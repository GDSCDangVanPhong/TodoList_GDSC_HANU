package TodoListforGDSC.demo.TaskController;

import TodoListforGDSC.demo.TaskEntity.TaskEntity;
import TodoListforGDSC.demo.TaskException.TaskNotFoundException;
import TodoListforGDSC.demo.TaskService.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ServiceInterface ServiceResponse;

    @GetMapping(value = "/getList")
    public ResponseEntity<?> getAllTask() {
        List<TaskEntity> tasks = ServiceResponse.showAllTask();
        if (tasks.isEmpty()) {
            return ResponseEntity.ok("No task found!");
        } else {
        return ResponseEntity.ok(ServiceResponse.showAllTask());
        }
    }

    @PostMapping(value = "/postTask")
    public ResponseEntity<?> postNew(@RequestBody TaskEntity task) {

        return ResponseEntity.ok(ServiceResponse.postTask(task));
    }

    @PutMapping(value = "/update/{id}")
    @ExceptionHandler
    public ResponseEntity<TaskEntity> updateTask(@RequestBody TaskEntity task, @PathVariable Long id) {

        if (!ServiceResponse.exist(id)) {
            throw new TaskNotFoundException("Task ko ton tai");
        } else {
            return ResponseEntity.ok(ServiceResponse.updateTask(task, id));
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        if (!ServiceResponse.exist(id)) {
            throw new TaskNotFoundException("Task ko ton tai");
        } else {
            ServiceResponse.deleteTask(id);
            return ResponseEntity.ok(true);
        }
    }


}
