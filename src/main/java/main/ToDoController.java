package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Task;

import java.util.List;

@RestController
public class ToDoController {
    @GetMapping("/tasks/")
    public List<Task> list() {
        return Storage.getTasks();
    }
    @PostMapping("/tasks/")
    public int add(Task task) {
        return Storage.addRecord(task);
    }
    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Task task = Storage.getTask(id);
        if (task == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else return new ResponseEntity(task, HttpStatus.OK);
    }
}
