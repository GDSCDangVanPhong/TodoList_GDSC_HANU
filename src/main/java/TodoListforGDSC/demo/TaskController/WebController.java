package TodoListforGDSC.demo.TaskController;

import TodoListforGDSC.demo.TaskEntity.TaskEntity;
import TodoListforGDSC.demo.TaskService.ServiceTodoImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class WebController {
    @Autowired
    private ServiceTodoImplement ServiceResponse;

    @GetMapping("/getList")
    public ResponseEntity<List<TaskEntity>> getAllTask(){
        return ResponseEntity.ok(ServiceResponse.showAllTask());
    }
    @PostMapping("/postTask")
    public ResponseEntity<TaskEntity> postNew(@RequestBody TaskEntity task){
        return ResponseEntity.ok(ServiceResponse.postTask(task));
    }
    @PutMapping("/update")
    public ResponseEntity<TaskEntity> updateTask(@RequestBody TaskEntity task ,@PathVariable Long id){
        ServiceResponse.deleteTask(id);
        ServiceResponse.postTask(task);
        return ResponseEntity.ok(ServiceResponse.updateTask(task));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Long id){
         ServiceResponse.deleteTask(id);
         return ResponseEntity.ok(true);
    }


}
