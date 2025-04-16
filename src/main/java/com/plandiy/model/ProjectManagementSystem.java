package com.plandiy.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectManagementSystem {
    //TODO: Strategy Для реалізації різних стратегій розподілу ресурсів.
    //TODO: Command Для реалізації операцій відміни та повтору дій в системі.
    private ArrayList<Project> projects = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Resource> resources = new ArrayList<>();

    private static ProjectManagementSystem instance;

    private ProjectManagementSystem() {
        // private constructor to prevent external instantiation
    }

    public static ProjectManagementSystem getInstance() {
        if (instance == null) {
            instance = new ProjectManagementSystem();
        }
        return instance;
    }

    public ArrayList<Project> getProjects() {
        return this.projects;
    }

    public ArrayList<Resource> getResources() {
        return this.resources;
    }


    public void createUser(String name, String email, UserRole role) {
        users.add(new User(name, email, role));
    }

    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        throw new IllegalArgumentException("User with email not found: " + email);
    }

    public void createProject(String ownerEmail,
                               String name,
                               String description,
                               LocalDate dateOfStart,
                               LocalDate dateOfEnd,
                               BigDecimal budget) {
        User owner = findUserByEmail(ownerEmail);
        projects.add(new Project(owner,
                    name,
                    description,
                    dateOfStart,
                    dateOfEnd,
                    budget));


    }

    public Project findProjectById(String projectId) {
        for (Project project : projects) {
            if (project.getId().equalsIgnoreCase(projectId)) {
                return project;
            }
        }
        throw new IllegalArgumentException("Project with ID not found: " + projectId);

    }

    public void addResource(String name, ResourceType type, BigDecimal price) {
        resources.add(new Resource(name, type, price));
    }

    public void deleteResource(Resource resource) {
        resources.remove(resource);
    }

    public Resource findResourceById(String resourceId) {
        for (Resource resource : resources) {
            if (resource.getId().equals(resourceId)) {
                return resource;
            }
        }
        throw new IllegalArgumentException("Resource with ID not found: " + resourceId);
    }

    public void reserveResource(String resourceId) {
        Resource resource = findResourceById(resourceId);
        resource.reserve();
    }

    public void makeResourceAvailable(String resourceId) {
        Resource resource = findResourceById(resourceId);
        resource.makeAvailable();
    }



//    public void generateReports(Project project, ReportType reportType) {
//
//    }

}
