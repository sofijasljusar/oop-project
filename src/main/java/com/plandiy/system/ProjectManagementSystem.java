package com.plandiy.system;

import com.plandiy.model.resource.Resource;
import com.plandiy.model.resource.ResourceType;
import com.plandiy.model.project.Project;
import com.plandiy.model.user.User;
import com.plandiy.model.user.UserRole;
import com.plandiy.service.analytics.AnalyticsEngine;
import com.plandiy.service.report.Report;
import com.plandiy.service.report.ReportType;
import com.plandiy.service.report.factory.BudgetReportCreator;
import com.plandiy.service.report.factory.ProjectReportCreator;
import com.plandiy.service.report.factory.ReportCreator;
import com.plandiy.service.report.factory.TeamReportCreator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Singleton class that represents the core Project Management System.
 * <p>
 * This class provides functionalities to manage users, projects, resources,
 * reports, and analytics within the application.
 */
public class ProjectManagementSystem {
    //TODO: Strategy Для реалізації різних стратегій розподілу ресурсів.
    private ArrayList<Project> projects = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Resource> resources = new ArrayList<>();

    private static ProjectManagementSystem instance;
    private final AnalyticsEngine analyticsEngine = new AnalyticsEngine();

    /**
     * Private constructor to enforce Singleton pattern.
     */
    private ProjectManagementSystem() {}

    /**
     * Returns the singleton instance of the ProjectManagementSystem.
     *
     * @return the singleton instance
     */
    public static ProjectManagementSystem getInstance() {
        if (instance == null) {
            instance = new ProjectManagementSystem();
        }
        return instance;
    }

    /**
     * Returns the list of all managed projects.
     *
     * @return list of projects
     */
    public ArrayList<Project> getProjects() {
        return this.projects;
    }

    /**
     * Returns the list of all registered resources.
     *
     * @return list of resources
     */
    public ArrayList<Resource> getResources() {
        return this.resources;
    }

    /**
     * Creates and registers a new user.
     *
     * @param name  the user's name
     * @param email the user's email
     * @param role  the user's role
     */
    public void createUser(String name, String email, UserRole role) {
        users.add(new User(name, email, role));
    }

    /**
     * Finds a user by their email address.
     *
     * @param email the email to search for
     * @return the matching {@link User}
     * @throws IllegalArgumentException if no user with the given email is found
     */
    public User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        throw new IllegalArgumentException("User with email not found: " + email);
    }

    /**
     * Creates and registers a new project.
     *
     * @param ownerEmail   email of the project owner
     * @param name         name of the project
     * @param description  project description
     * @param dateOfStart  project start date
     * @param dateOfEnd    project end date
     * @param budget       project budget
     */
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

    /**
     * Finds a project by its ID.
     *
     * @param projectId the project ID
     * @return the matching {@link Project}
     * @throws IllegalArgumentException if no project with the given ID is found
     */
    public Project findProjectById(String projectId) {
        for (Project project : projects) {
            if (project.getId().equalsIgnoreCase(projectId)) {
                return project;
            }
        }
        throw new IllegalArgumentException("Project with ID not found: " + projectId);

    }

    /**
     * Adds a new resource to the system.
     *
     * @param name  the name of the resource
     * @param type  the type of the resource
     * @param price the cost of the resource
     */
    public void addResource(String name, ResourceType type, BigDecimal price) {
        resources.add(new Resource(name, type, price));
    }

    /**
     * Removes a resource from the system.
     *
     * @param resource the resource to remove
     */
    public void deleteResource(Resource resource) {
        resources.remove(resource);
    }

    /**
     * Finds a resource by its ID.
     *
     * @param resourceId the resource ID
     * @return the matching {@link Resource}
     * @throws IllegalArgumentException if no resource with the given ID is found
     */
    public Resource findResourceById(String resourceId) {
        for (Resource resource : resources) {
            if (resource.getId().equals(resourceId)) {
                return resource;
            }
        }
        throw new IllegalArgumentException("Resource with ID not found: " + resourceId);
    }

    /**
     * Marks a resource as reserved.
     *
     * @param resourceId the ID of the resource to reserve
     */
    public void reserveResource(String resourceId) {
        Resource resource = findResourceById(resourceId);
        resource.reserve();
    }

    /**
     * Marks a resource as available.
     *
     * @param resourceId the ID of the resource to release
     */
    public void makeResourceAvailable(String resourceId) {
        Resource resource = findResourceById(resourceId);
        resource.makeAvailable();
    }

    /**
     * Generates a report of the specified type for the given date range.
     *
     * @param reportType the type of report to generate
     * @param start      the start date of the report range
     * @param end        the end date of the report range
     */
    public void generateReport(ReportType reportType, LocalDate start, LocalDate end) {
        ReportCreator creator = switch (reportType) {
            case PROJECT_PROGRESS -> new ProjectReportCreator();
            case TEAM_PRODUCTIVITY -> new TeamReportCreator();
            case BUDGET_USAGE -> new BudgetReportCreator();
        };
        Report report = creator.createReport(start, end);
        report.generateReport();
    }

    /**
     * Runs analytical insights for the specified project.
     * This includes productivity analysis, end date prediction, and risk identification.
     *
     * @param projectId the ID of the project to analyze
     */
    public void runProjectAnalytics(String projectId) {
        Project project = findProjectById(projectId);
        analyticsEngine.printTeamProductivity(project);
        System.out.println("\nPredicted end date: " + analyticsEngine.predictProjectEndDate(project));
        analyticsEngine.identifyRisks(project);
    }

}
