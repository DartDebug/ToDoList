package main;

import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks/")
    public List<Task> list() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> tasks = new ArrayList<>();
        for (var task : taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    @PostMapping("/tasks/")
    public int add(Task task) {
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.map(task -> new ResponseEntity(task, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/tasks/{id}")
    public void del(@PathVariable int id) {
        taskRepository.deleteById(id);
    }
}
