package TodoListforGDSC.demo.ToDoController;

import TodoListforGDSC.demo.ToDoEntity.ToDoEntity;
import TodoListforGDSC.demo.ToDoException.TaskNotFoundException;
import TodoListforGDSC.demo.ToDoService.TodoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/todo")
public class ToDoController {

    @Autowired
    private TodoServiceInterface todoService;

    @GetMapping(value = "/getList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTask() {
        List<ToDoEntity> tasks = todoService.showAllTask();
        if (tasks.isEmpty()) {
            return ResponseEntity.ok("No task found!");
        } else {
            return ResponseEntity.ok(todoService.showAllTask());
        }
    }

    @PostMapping(value = "/postTask", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postNew(@RequestBody ToDoEntity task) {

        return ResponseEntity.ok(todoService.postTask(task));
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDoEntity> updateTask(@RequestBody ToDoEntity task, @PathVariable Long id) {
        if (!todoService.exist(id)) {
            throw new TaskNotFoundException("Task ko ton tai");
        } else {
            return ResponseEntity.ok(todoService.updateTask(task, id));
        }
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        if (!todoService.exist(id)) {
            throw new TaskNotFoundException("Task ko ton tai");
        } else {
            todoService.deleteTask(id);
            return ResponseEntity.ok(true);
        }
    }


}
