package com.plandiy;

import com.plandiy.model.budget.Budget;
import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.FeatureTask;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.issue.task.TaskType;
import com.plandiy.model.project.Project;
import com.plandiy.model.user.User;
import com.plandiy.model.user.UserRole;
import com.plandiy.service.analytics.AnalyticsEngine;
import com.plandiy.service.report.ReportType;
import com.plandiy.service.report.factory.BudgetReportCreator;
import com.plandiy.service.report.Report;
import com.plandiy.service.report.factory.ReportCreator;
import com.plandiy.system.ProjectManagementSystem;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ConsoleMain {
    public static void main(String[] args) {
//        User user1 = new User("Lola", "lola@gmail.com", UserRole.MANAGER);
//        Project project1 = new Project(user1,
//                "Moodle",
//                "very nice",
//                LocalDate.of(2024, 9, 1),
//                LocalDate.of(2025, 6, 1),
//                new BigDecimal("100000.00"));
//
//        System.out.println(project1.getKey());
//        System.out.println(project1.getStatus());
//        System.out.println(project1.projectInfo());
//
//        project1.addTask("Important task!", TaskStatus.DONE, TaskPriority.HIGH, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
//        project1.addTask("Regular task", TaskStatus.TO_DO, TaskPriority.MEDIUM, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
//        project1.addTask("Cool task!", TaskStatus.TO_DO, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
//        project1.addTask("Weird task!", TaskStatus.TO_DO, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
//        project1.addTask("Lazy task!", TaskStatus.DONE, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
//        project1.addTask("Sweet task!", TaskStatus.DONE, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
//        project1.addTask("Sour task!", TaskStatus.TO_DO, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
//        project1.addTask("Creamy task!", TaskStatus.TO_DO, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
//        project1.addTask("Jumpy task!", TaskStatus.DONE, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
//        project1.addTask("Slow task!", TaskStatus.DONE, TaskPriority.LOW, LocalDate.of(2025, 4, 15), LocalDate.of(2025, 5, 15));
//
//        System.out.println(project1.getListOfTasks());
//        for (Task task: project1.getListOfTasks()) {
//            System.out.println(task.getInfo());
//        }
//
//        System.out.println(project1.calculateProgress());
//
//        Budget budget = new Budget(BigDecimal.valueOf(10000));
//        budget.addExpense(BigDecimal.valueOf(100), "Coffee machine");
//        budget.addExpense(BigDecimal.valueOf(200), "Cookies");
//        budget.addExpense(BigDecimal.valueOf(300), "Microwave");
//        System.out.println(budget.getRemainingAmount());
//        System.out.println(budget.generateFinancialReport());
//        ReportCreator reportManager = new BudgetReportCreator();
//        Report budgetReport = reportManager.createReport(
//                LocalDate.of(2025, 4, 18),
//                LocalDate.of(2025, 4, 25));
//        System.out.println(budgetReport.formatReportData());
        // Create users
//        User owner = new User("Alice", "alice@example.com", UserRole.TEAMMATE);
//        User contributor1 = new User("Bob", "bob@example.com", UserRole.TEAMMATE);
//        User contributor2 = new User("Charlie", "charlie@example.com", UserRole.TEAMMATE);
//
//        // Create a project
//        Project project = new Project(
//                owner,
//                "Apollo",
//                "A rocket management project",
//                LocalDate.of(2025, 4, 1),
//                LocalDate.of(2025, 9, 30),
//                new BigDecimal("50000")
//        );
//
//        // Add contributors to project
//        project.addContributor(contributor1);
//        project.addContributor(contributor2 );
//
//
//        // Simulate project status update
//        System.out.println("==== Updating project status ====");
//        project.updateStatus(ProjectStatus.IN_PROGRESS);
//
//        // Notify observers (owner + contributors)
//        project.notifyObservers();
//

//        TaskCompletionProgress taskCompletionProgress;
//        TimeProgress timeProgress;
//        Project project;
//        User user;
//        taskCompletionProgress = new TaskCompletionProgress();
//        timeProgress = new TimeProgress();
//
//        user = new User("John Doe", "john.doe@example.com", UserRole.TEAMMATE);
//        project = new Project(user,"Project 1", "", LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), new BigDecimal(70000));
//
//        project.addTask("Task 1", IssueStatus.DONE, IssuePriority.MEDIUM, LocalDate.now().minusDays(5), LocalDate.now().plusDays(5));
//        project.addTask("Task 2", IssueStatus.TO_DO, IssuePriority.MEDIUM, LocalDate.now().minusDays(7), LocalDate.now().plusDays(3));
//
//        Task task3 = new ResearchTask("T-3", "Task 3", IssueStatus.TO_DO, IssuePriority.MEDIUM, LocalDate.now().minusDays(7), LocalDate.now().plusDays(3));
//        Task task4 = new ResearchTask("T-4", "Task 4", IssueStatus.TO_DO, IssuePriority.MEDIUM, LocalDate.now().minusDays(7), LocalDate.now().plusDays(3));
//        user.addTask(task3);
//        user.addTask(task4);
//
//        LocalDate start = LocalDate.now().minusDays(10);
//        LocalDate end = LocalDate.now().plusDays(5);
//
//        project.setDateOfStart(start);
//        project.setDateOfEnd(end);
//        project.setProgressStrategy(timeProgress);
//
//        int progress = project.calculateProgress();
//        System.out.println(progress);
//
//
//        double totalDays = start.until(end, java.time.temporal.ChronoUnit.DAYS); //todo
//        double passedDays = start.until(LocalDate.now(), java.time.temporal.ChronoUnit.DAYS);
//        int expectedProgress = (int) ( passedDays / totalDays * 100);
//        System.out.println(expectedProgress);

//        Task task = new FeatureTask(
//                "TASK-1",
//                "Initial Name",
//                IssueStatus.TO_DO,
//                IssuePriority.MEDIUM,
//                LocalDate.now(),
//                LocalDate.now().plusDays(7)
//        );
//
//        CommandManager manager = new CommandManager();
//        System.out.println("Before any command: " + task.getName());
//
//        // 4) Execute a rename
//        manager.executeCommand(new RenameIssueCommand(task, "First Rename"));
//        System.out.println("After execute(): "  + task.getName());
//
//        manager.undo();
//        System.out.println("After undo(): "     + task.getName());
//
//        manager.redo();
//        System.out.println("After redo(): "     + task.getName());
//        manager.executeCommand(new RenameIssueCommand(task, "First Rename"));
//        System.out.println("After execute(): "  + task.getName());
//        manager.executeCommand(new RenameIssueCommand(task, "Second Rename"));
//        System.out.println("After execute(): "  + task.getName());
//        manager.executeCommand(new RenameIssueCommand(task, "Third Rename"));
//        System.out.println("After execute(): "  + task.getName());
//        manager.undo();
//        System.out.println("After undo(): "     + task.getName());
//        manager.redo();
//        System.out.println("After redo(): "     + task.getName());

//        User testUser = new User("Test User", "testuser@example.com", UserRole.ADMIN);
//
//
//        // Initializing the Project with necessary parameters
//        Project project = new Project(
//                testUser,
//                "Risk Analysis Project",
//                "Project to test risk evaluation.",
//                LocalDate.now().minusDays(10),
//                LocalDate.now().plusDays(20),
//                new BigDecimal("10000.00")
//        );
//
//        project.addTask(
//                "Fix Critical Bug",
//                "Fix it now!",
//                IssueStatus.IN_PROGRESS,
//                IssuePriority.CRITICAL,
//                LocalDate.now().minusDays(5),
//                LocalDate.now(),
//                TaskType.BUG
//        );
//
//        // Low risk task
//        project.addTask(
//                "Add Help Page",
//                "Not urgent",
//                IssueStatus.TO_DO,
//                IssuePriority.LOW,
//                LocalDate.now(),
//                LocalDate.now().plusDays(10),
//                TaskType.FEATURE
//        );
//
//        // Medium risk task
//        project.addTask(
//                "Refactor module",
//                "Improve maintainability",
//                IssueStatus.IN_PROGRESS,
//                IssuePriority.MEDIUM,
//                LocalDate.now().minusDays(3),
//                LocalDate.now().plusDays(2),
//                TaskType.FEATURE
//        );
//
//        // Very high risk task
//        project.addTask(
//                "Fix Login",
//                "Users can't login!",
//                IssueStatus.IN_PROGRESS,
//                IssuePriority.HIGH,
//                LocalDate.now().minusDays(10),
//                LocalDate.now().minusDays(1),
//                TaskType.BUG
//        );
//
//        // Initializing the RiskManager and adding creators
//        RiskManager riskManager = new RiskManager();
//
//        // Identify risks based on tasks in the project
//        riskManager.identifyRisks(project);  // Identify risks for the project
//
//        // Print risks and evaluate their probability/impact
//        System.out.println("Identified Risks: ");
//        for (Risk risk : riskManager.getRisks()) {
//            System.out.printf("%s | Prob: %.2f | Impact: %.2f%n",
//                    risk.getDescription(), risk.getProbability(), risk.getImpact());
//        }
//
//        // Evaluate risks
//        riskManager.evaluateRisks();

//        ProjectManagementSystem pms = ProjectManagementSystem.getInstance();
//
//        LocalDate start = LocalDate.of(2024, 1, 1);
//        LocalDate end = LocalDate.of(2024, 12, 31);
//
//        pms.generateReport(ReportType.PROJECT_PROGRESS, start, end);
//        pms.generateReport(ReportType.BUDGET_USAGE, start, end);
//        pms.generateReport(ReportType.TEAM_PRODUCTIVITY, start, end);

//        User alice = new User("Alice", "alice@example.com", UserRole.TEAMMATE);
//        User bob = new User("Bob", "bob@example.com", UserRole.TEAMMATE);
//
//        // Create project
//        Project project = new Project(
//                alice,
//                "Test Project",
//                "Project for analytics test",
//                LocalDate.now().minusDays(5),
//                LocalDate.now().plusDays(10),
//                new BigDecimal("1000")
//        );
//
//        // Assign contributors
//        project.addContributor(alice);
//        project.addContributor(bob);
//        LocalDate start = LocalDate.now().plusDays(2);
//        LocalDate end = start.plusDays(5);
//        // Create tasks
//        Task task1 = new FeatureTask("Task 1", "Basic task", IssueStatus.DONE ,IssuePriority.LOW, start, end);
//        task1.assignTo(alice);
//
//        Task task2 = new FeatureTask("Task 2", "High priority task", IssueStatus.TO_DO, IssuePriority.HIGH, start, end);
//        task2.assignTo(bob);
//
//        Task task3 = new FeatureTask("Task 3", "Another high priority", IssueStatus.DONE, IssuePriority.HIGH, start, end);
//        task3.assignTo(bob);
//
//        // Analytics
//        AnalyticsEngine analyticsEngine = new AnalyticsEngine();
//        analyticsEngine.printTeamProductivity(project);

        // Create Users
        User alice = new User("Alice", "alice@example.com", UserRole.TEAMMATE);
        User bob = new User("Bob", "bob@example.com", UserRole.TEAMMATE);

// Create the Project Management System instance (Singleton)
        ProjectManagementSystem pms = ProjectManagementSystem.getInstance();

// Create a project
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

//        String projectId = project.getId();
//        pms.runProjectAnalytics(projectId);
        project.showTimeline();

    }
}
