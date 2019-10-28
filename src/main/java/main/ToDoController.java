package main;

import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks/")
    public List<Task> list() {
        return Storage.getTasks();
    }

    @PostMapping("/tasks/")
    public int add(Task task) {
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id) {
//        Task task = Storage.getTask(id);
        Optional optionalTask = taskRepository.findById(id);
        if (task == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        else return new ResponseEntity(task, HttpStatus.OK);
    }
}
