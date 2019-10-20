package main;

import response.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Storage {
    private static int currentId = 1;
    private static Map<Integer, Task> toDoHashMap = new HashMap<>();
    public static int addRecord(Task task) {
        int id = currentId++;
        task.setId(id);
        toDoHashMap.put(id, task);
        return id;
    }

    public static List<Task> getTasks() {
        return new ArrayList<>(toDoHashMap.values());
    }

    public static Task getTask(int taskId) {
        return toDoHashMap.getOrDefault(taskId, null);
    }
}
