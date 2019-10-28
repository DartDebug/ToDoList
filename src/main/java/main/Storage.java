package main;

import main.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    private static int currentId = 1;
    private static Map<Integer, Task> tasksHashMap = new HashMap<>();
    public static int addTask(Task task) {
        int id = currentId++;
        task.setId(id);
        tasksHashMap.put(id, task);
        return id;
    }

    public static List<Task> getTasks() {
        return new ArrayList<>(tasksHashMap.values());
    }

    public static Task getTask(int taskId) {
        return tasksHashMap.getOrDefault(taskId, null);
    }
}
