package com.plandiy.model.dao;

import com.plandiy.model.issue.task.Task;
import com.plandiy.model.project.Project;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class DemoTaskDao implements Dao<Task> {
    private final Map<String, Task> taskMap = new HashMap<>();
    private static DemoTaskDao instance;

    private DemoTaskDao() {}

    public static DemoTaskDao getInstance() {
        if (instance == null) {
            instance = new DemoTaskDao();
        }
        return instance;
    }

    @Override
    public void create(Task entity) {
        taskMap.put(entity.getId(), entity);
    }

    @Override
    public Task read(String id) {
        Task task = taskMap.get(id);
        if (task == null) throw new NoSuchElementException("Task not found: " + id);
        return task;
    }

    @Override
    public void update(Task entity) {
        taskMap.put(entity.getId(), entity);
    }

    @Override
    public void delete(String id) {
        taskMap.remove(id);
    }

    @Override
    public Map<String, Task> getAll() {
        return new HashMap<>((taskMap));
    }
}
