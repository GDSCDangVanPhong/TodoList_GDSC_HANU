package TodoListforGDSC.demo.todolist.controller;

import TodoListforGDSC.demo.todolist.ToDoBodyResponse.ToDoResponseHandler;
import TodoListforGDSC.demo.todolist.entity.CategoryEntity;
import TodoListforGDSC.demo.todolist.entity.ToDoEntity;
import TodoListforGDSC.demo.share.TaskNotFoundException;
import TodoListforGDSC.demo.todolist.service.TodoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("/todo")
public class ToDoController {

    @Autowired
    private TodoServiceInterface todoService;
    @GetMapping
    private ResponseEntity<Object> showAllTask(@RequestParam(required = false) String title,
                                               @RequestParam(required = false) LocalDateTime createdBefore,
                                               @RequestParam(required = false) LocalDateTime createdAfter,
                                               @RequestParam(required = false )String status,
                                               @RequestParam(required = false)String sortDirection){
        return ToDoResponseHandler.ToDoResponseBody("Get all tasks successfully",
                "SUCCESS",
                todoService.searchTask(null,title, createdBefore, createdAfter, status, sortDirection),
                HttpStatus.OK);
    }
    @GetMapping("/{categoryName}")
    public ResponseEntity<Object> getTasksByCategoryName(@PathVariable String categoryName,
                                                         @RequestParam(required = false) String title,
                                                         @RequestParam(required = false) LocalDateTime createdBefore,
                                                         @RequestParam(required = false) LocalDateTime createdAfter,
                                                         @RequestParam(required = false) String status,
                                                         @RequestParam(required = false) String sortDirection) {
        List<ToDoEntity> tasks = todoService.getTasksByCategoryName(categoryName);
        return ToDoResponseHandler.ToDoResponseBody("Get all tasks successfully",
                "SUCCESS",
                tasks,
                HttpStatus.OK);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> postNew(@RequestBody ToDoEntity task) {
        todoService.postTask(task);
        return ToDoResponseHandler.ToDoResponseBody("Post task successfully!", "SUCCESS", task.getId(), HttpStatus.CREATED);
    }
    @PostMapping("/createCategory")
    private ResponseEntity<Object> createCategory(@RequestBody CategoryEntity category){
        todoService.createCategory(category);
        return ToDoResponseHandler.ToDoResponseBody("Created new category!","SUCCESS",category.getId(),
                HttpStatus.OK);
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