package com.plandiy.model.dao;

import com.plandiy.model.project.Project;
import com.plandiy.model.user.User;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.IssuePriority;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class DemoProjectDao implements Dao<Project> {
    private final Dao<User> userDao;
    private final Dao<Task> taskDao;
    private final Map<String, Project> projectMap = new HashMap<>();
    private static DemoProjectDao instance;

    private DemoProjectDao(Dao<User> userDao, Dao<Task> taskDao) {
        this.userDao = userDao;
        this.taskDao = taskDao;

        User owner = userDao.read("alice.johnson@example.com");

        Project project = new Project(
                owner,
                "Health Monitor",
                "Track health vitals with a wearable device.",
                LocalDate.of(2025, 1, 10),
                LocalDate.of(2025, 6, 30),
                new BigDecimal("150000")
        );

        project.addTask("Build sensor", IssueStatus.TO_DO, IssuePriority.HIGH, LocalDate.now(), LocalDate.now().plusDays(10));
        for (Task task : project.getListOfTasks()) {
            taskDao.create(task);
        }

        create(project);


    }

    public static DemoProjectDao getInstance(Dao<User> userDao, Dao<Task> taskDao) {
        if (instance == null) {
            instance = new DemoProjectDao(userDao, taskDao);
        }
        return instance;
    }

    @Override
    public void create(Project entity) {
        String key = entity.getKey();
        if (projectMap.containsKey(key)) {
            throw new IllegalArgumentException(("Duplicate project key" + key));
        }
        projectMap.put(key, entity);
    }

    @Override
    public Project read(String key) {
        Project project = projectMap.get(key);
        if (project == null) throw new NoSuchElementException("Project not found: " + key);
        return project;
    }

    @Override
    public void update(Project entity) {
        projectMap.put(entity.getKey(), entity);
    }

    @Override
    public void delete(String key) {
        projectMap.remove(key);
    }

    @Override
    public Map<String, Project> getAll() {
        return new HashMap<>(projectMap);
    }
}
