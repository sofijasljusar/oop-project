package com.plandiy;

import com.plandiy.command.CommandManager;
import com.plandiy.command.issue.RenameIssueCommand;
import com.plandiy.model.budget.Budget;
import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.FeatureTask;
import com.plandiy.model.issue.task.ResearchTask;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.issue.task.TaskType;
import com.plandiy.model.project.Project;
import com.plandiy.model.project.ProjectStatus;
import com.plandiy.model.user.User;
import com.plandiy.model.user.UserRole;
import com.plandiy.service.analytics.AnalyticsEngine;
import com.plandiy.service.progress.TaskCompletionProgress;
import com.plandiy.service.progress.TimeProgress;
import com.plandiy.service.report.ReportType;
import com.plandiy.service.report.factory.BudgetReportCreator;
import com.plandiy.service.report.Report;
import com.plandiy.service.report.factory.ReportCreator;
import com.plandiy.service.risk.RiskManager;
import com.plandiy.system.ProjectManagementSystem;


import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * ConsoleMain class serves as a demo and test environment for the PlanDiy project management system.
 * <p>
 * This file contains a variety of commented and uncommented test cases used to:
 * - Demonstrate project/task creation
 * - Execute budget operations
 * - Test reports and analytics
 * - Evaluate risk
 * - Apply design patterns (Command, Strategy, Observer, etc.)
 * <p>
 * To test individual features, uncomment (Ctrl + Shift + /) the corresponding block and run the main method.
 */
public class ConsoleMain {
    public static void main(String[] args) {

//         === DEMO: Basic Project Creation and Task Listing ===
/*
        User user1 = new User("Lola", "lola@gmail.com", UserRole.MANAGER);
        Project project1 = new Project(user1,
                "Moodle",
                "very nice",
                LocalDate.of(2024, 9, 1),
                LocalDate.of(2025, 6, 1),
                new BigDecimal("100000.00"));


        project1.addTask("Important task!", IssueStatus.DONE, IssuePriority.HIGH, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15), TaskType.FEATURE);
        project1.addTask("Regular task", IssueStatus.TO_DO, IssuePriority.MEDIUM, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15), TaskType.BUG);
        project1.addTask("Cool task!", IssueStatus.TO_DO, IssuePriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15), TaskType.RESEARCH);
        project1.addTask("Weird task!", IssueStatus.TO_DO, IssuePriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15), TaskType.BUG);
        project1.addTask("Lazy task!", IssueStatus.DONE, IssuePriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15), TaskType.BUG);
        project1.addTask("Sweet task!", IssueStatus.DONE, IssuePriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15), TaskType.BUG);
        project1.addTask("Sour task!", IssueStatus.TO_DO, IssuePriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15), TaskType.RESEARCH);
        project1.addTask("Creamy task!", IssueStatus.TO_DO, IssuePriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15), TaskType.FEATURE);
        project1.addTask("Jumpy task!", IssueStatus.DONE, IssuePriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15), TaskType.RESEARCH);
        project1.addTask("Slow task!", IssueStatus.DONE, IssuePriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15), TaskType.FEATURE);

        System.out.println("All tasks in project " + project1.getName() + ":");
        for (Task task: project1.getListOfTasks()) {
            System.out.println("\n" + task.getInfo());
        }
*/


// === DEMO: Budget Management and Report Generation ===/*
/*
        Budget budget = new Budget(BigDecimal.valueOf(10000));
        budget.addExpense(BigDecimal.valueOf(100), "Coffee machine");
        budget.addExpense(BigDecimal.valueOf(200), "Cookies");
        budget.addExpense(BigDecimal.valueOf(300), "Microwave");
        System.out.println(budget.getRemainingAmount());
        System.out.println(budget.generateFinancialReport());
        ReportCreator reportManager = new BudgetReportCreator();
        Report budgetReport = reportManager.createReport(
                LocalDate.of(2025, 4, 18),
                LocalDate.of(2025, 4, 25));
        System.out.println(budgetReport.formatReportData());
 */


// === DEMO: Observer Pattern with Project Contributors ===
/*
        // Create users
        User owner = new User("Alice", "alice@example.com", UserRole.TEAMMATE);
        User contributor1 = new User("Bob", "bob@example.com", UserRole.TEAMMATE);
        User contributor2 = new User("Charlie", "charlie@example.com", UserRole.TEAMMATE);

        // Create a project
        Project project = new Project(
                owner,
                "Apollo",
                "A rocket management project",
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 9, 30),
                new BigDecimal("50000")
        );

        // Add contributors to project
        project.addContributor(contributor1);
        project.addContributor(contributor2 );


        // Simulate project status update
        System.out.println("==== Updating project status ====");
        project.updateStatus(ProjectStatus.IN_PROGRESS);

        // Notify observers (owner + contributors)
        project.notifyObservers();
*/


// === DEMO: Strategy Pattern for Progress Calculation ===
/*
        TaskCompletionProgress taskCompletionProgress = new TaskCompletionProgress();
        TimeProgress timeProgress = new TimeProgress();

        User user = new User("John Doe", "john.doe@example.com", UserRole.TEAMMATE);
        Project project = new Project(user,"Project 1", "", LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), new BigDecimal(70000));

        project.addTask("Task 1", IssueStatus.DONE, IssuePriority.MEDIUM, LocalDate.now().minusDays(5), LocalDate.now().plusDays(5), TaskType.FEATURE);
        project.addTask("Task 2", IssueStatus.TO_DO, IssuePriority.MEDIUM, LocalDate.now().minusDays(7), LocalDate.now().plusDays(3), TaskType.FEATURE);
        project.addTask("Task 3", IssueStatus.TO_DO, IssuePriority.MEDIUM, LocalDate.now().minusDays(7), LocalDate.now().plusDays(3), TaskType.FEATURE);
        project.addTask("Task 4", IssueStatus.TO_DO, IssuePriority.MEDIUM, LocalDate.now().minusDays(7), LocalDate.now().plusDays(3), TaskType.FEATURE);

        project.assignTaskToUser("PROJ-1", user);
        project.assignTaskToUser("PROJ-2", user);

        LocalDate start = LocalDate.now().minusDays(10);
        LocalDate end = LocalDate.now().plusDays(5);

        project.setDateOfStart(start);
        project.setDateOfEnd(end);
        System.out.println("Tasks in project completed: " + project.calculateProgress() + "%");
        project.setProgressStrategy(timeProgress);
        System.out.println("Time progress of the project: " + project.calculateProgress() + "%");
        System.out.println("User's completed tasks: " + user.calculateProgress() + "%");
*/


// === DEMO: Command Pattern for Undo/Redo Operations ===
/*
        Task task = new FeatureTask(
                "TASK-1",
                "Initial Name",
                IssueStatus.TO_DO,
                IssuePriority.MEDIUM,
                LocalDate.now(),
                LocalDate.now().plusDays(7)
        );

        CommandManager manager = new CommandManager();
        System.out.println("Before any command: " + task.getName());

        // 4) Execute a rename
        manager.executeCommand(new RenameIssueCommand(task, "First Rename"));
        System.out.println("After execute(): "  + task.getName());

        manager.undo();
        System.out.println("After undo(): "     + task.getName());

        manager.redo();
        System.out.println("After redo(): "     + task.getName());
        manager.executeCommand(new RenameIssueCommand(task, "New Rename"));
        System.out.println("After execute(): "  + task.getName());
        manager.executeCommand(new RenameIssueCommand(task, "Second Rename"));
        System.out.println("After execute(): "  + task.getName());
        manager.executeCommand(new RenameIssueCommand(task, "Third Rename"));
        System.out.println("After execute(): "  + task.getName());
        manager.undo();
        System.out.println("After undo(): "     + task.getName());
        manager.redo();
        System.out.println("After redo(): "     + task.getName());
*/


// === DEMO: Risk Identification and Evaluation ===
/*
        User testUser = new User("Test User", "testuser@example.com", UserRole.ADMIN);


        // Initializing the Project with necessary parameters
        Project project = new Project(
                testUser,
                "Test Project Analysis",
                "Project to test risk evaluation.",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(20),
                new BigDecimal("10000.00")
        );

        project.addTask(
                "Fix Critical Bug",
                "Fix it now!",
                IssueStatus.IN_PROGRESS,
                IssuePriority.CRITICAL,
                LocalDate.now().minusDays(5),
                LocalDate.now(),
                TaskType.BUG
        );

        // Low risk task
        project.addTask(
                "Add Help Page",
                "Not urgent",
                IssueStatus.TO_DO,
                IssuePriority.LOW,
                LocalDate.now(),
                LocalDate.now().plusDays(10),
                TaskType.FEATURE
        );

        // Medium risk task
        project.addTask(
                "Refactor module",
                "Improve maintainability",
                IssueStatus.IN_PROGRESS,
                IssuePriority.MEDIUM,
                LocalDate.now().minusDays(3),
                LocalDate.now().plusDays(2),
                TaskType.FEATURE
        );

        // Very high risk task
        project.addTask(
                "Fix Login",
                "Users can't log in!",
                IssueStatus.IN_PROGRESS,
                IssuePriority.HIGH,
                LocalDate.now().minusDays(10),
                LocalDate.now().minusDays(1),
                TaskType.BUG
        );

        // Initializing the RiskManager and adding creators
        RiskManager riskManager = new RiskManager();

        // Identify risks based on tasks in the project
        riskManager.identifyRisks(project);

        // Evaluate risks
        System.out.println("Identified Risks: ");
        riskManager.evaluateRisks();
*/


// === DEMO: Analytics Engine on Project Tasks ===
/*
        // Create Users
        User alice = new User("Alice", "alice@example.com", UserRole.TEAMMATE);
        User bob = new User("Bob", "bob@example.com", UserRole.TEAMMATE);

        // Create project
        Project project = new Project(
                alice,
                "Test Project",
                "Project for analytics test",
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(10),
                new BigDecimal("1000")
        );

        // Assign contributors
        project.addContributor(alice);
        project.addContributor(bob);
        LocalDate start = LocalDate.now().plusDays(2);
        LocalDate end = start.plusDays(5);

        // Create tasks
        project.addTask("Task 1", "Basic task", IssueStatus.DONE ,IssuePriority.LOW, start, end, TaskType.FEATURE);
        project.assignTaskToUser("TEST-1", alice);

        project.addTask("Task 2", "High priority task", IssueStatus.TO_DO, IssuePriority.HIGH, start, end, TaskType.FEATURE);
        project.assignTaskToUser("TEST-2", bob);

        project.addTask("Task 3", "Another high priority", IssueStatus.DONE, IssuePriority.HIGH, start, end, TaskType.FEATURE);
        project.assignTaskToUser("TEST-3", bob);

        // Analytics
        AnalyticsEngine analyticsEngine = new AnalyticsEngine();
        System.out.println("Team Productivity Analytics");
        analyticsEngine.printTeamProductivity(project);
*/


// === ACTIVE DEMO: Core System Usage (+Analytics +Timeline) ===
/*
        // Create the Project Management System instance (Singleton)
        ProjectManagementSystem pms = ProjectManagementSystem.getInstance();

        // Create a project
        User alice = new User("Alice", "alice@example.com", UserRole.TEAMMATE);
        User bob = new User("Bob", "bob@example.com", UserRole.TEAMMATE);

        Project project = new Project(
                alice,
                "Test Project",
                "Project for Analytics Test",
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(10),
                new BigDecimal("1000")
        );

        project.addTask("Task 1", "Basic task", IssueStatus.TO_DO, IssuePriority.LOW,
                LocalDate.now().minusDays(3), LocalDate.now().plusDays(3), TaskType.FEATURE);
        project.addTask("Task 2", "Another task", IssueStatus.TO_DO, IssuePriority.HIGH,
                LocalDate.now().minusDays(2), LocalDate.now().plusDays(6), TaskType.FEATURE);
        project.addContributor(alice);
        project.addContributor(bob);
        pms.getProjects().add(project);

        project.assignTaskToUser("TEST-1", alice);
        project.assignTaskToUser("TEST-2", bob);

        String projectId = project.getId();
        pms.runProjectAnalytics(projectId);
        project.showTimeline();
*/
    }
}
