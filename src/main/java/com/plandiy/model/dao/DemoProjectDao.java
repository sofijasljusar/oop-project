package com.plandiy.model.dao;

import com.plandiy.model.project.Project;
import com.plandiy.model.user.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class DemoProjectDao implements Dao<Project> {
    private final Dao<User> userDao;
    private final Map<String, Project> projectMap = new HashMap<>();

    public DemoProjectDao(Dao<User> userDao) {
        this.userDao = userDao;

        User owner = userDao.read("alice.johnson@example.com");

        create(new Project(
                owner,
                "Health Monitor",
                "Track health vitals with a wearable device.",
                LocalDate.of(2025, 1, 10),
                LocalDate.of(2025, 6, 30),
                new BigDecimal("150000")
        ));

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
