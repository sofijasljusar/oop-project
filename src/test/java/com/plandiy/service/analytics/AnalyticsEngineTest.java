package com.plandiy.service.analytics;

import static org.junit.jupiter.api.Assertions.*;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.TaskType;
import com.plandiy.model.project.Project;
import com.plandiy.model.user.User;
import com.plandiy.model.user.UserRole;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AnalyticsEngineTest {

    @Test
    public void testPredictEndDate_NoTasks() {
        // Setup: Project with no tasks
        User alice = new User("Alice", "alice@example.com", UserRole.TEAMMATE);
        Project project = new Project(
                alice,
                "Test Project",
                "Project for analytics test",
                LocalDate.now().minusDays(5),  // Start Date
                LocalDate.now().plusDays(10),  // Planned End Date
                new BigDecimal("1000")  // Budget
        );

        // Predict the end date
        AnalyticsEngine analyticsEngine = new AnalyticsEngine();
        LocalDate predictedEndDate = analyticsEngine.predictProjectEndDate(project);

        // Test: Predicted end date should be the same as the planned project end date
        assertEquals(project.getDateOfEnd(), predictedEndDate);
    }

    @Test
    public void testPredictEndDate_TasksBeforeEnd() {
        // Setup: Project with tasks that end before the planned project end date
        User alice = new User("Alice", "alice@example.com", UserRole.TEAMMATE);
        Project project = new Project(
                alice,
                "Test Project",
                "Project for analytics test",
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(10),
                new BigDecimal("1000")
        );

        project.addTask("Task 1", "Basic task", IssueStatus.DONE, IssuePriority.LOW, LocalDate.now().minusDays(3), LocalDate.now().plusDays(3), TaskType.FEATURE);
        project.addTask("Task 2", "Another task", IssueStatus.DONE, IssuePriority.HIGH, LocalDate.now().minusDays(2), LocalDate.now().plusDays(6), TaskType.FEATURE);
        // Predict the end date
        AnalyticsEngine analyticsEngine = new AnalyticsEngine();
        LocalDate predictedEndDate = analyticsEngine.predictProjectEndDate(project);

        // Test: Predicted end date should be the same as the planned project end date
        assertEquals(LocalDate.now().plusDays(6), predictedEndDate);
    }

    @Test
    public void testPredictEndDate_TasksAfterEnd() {
        // Setup: Project with tasks that end after the planned project end date
        User alice = new User("Alice", "alice@example.com", UserRole.TEAMMATE);
        Project project = new Project(
                alice,
                "Test Project",
                "Project for analytics test",
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(10),
                new BigDecimal("1000")
        );

        project.addTask("Task 1", "Basic task", IssueStatus.DONE, IssuePriority.LOW, LocalDate.now().minusDays(3), LocalDate.now().plusDays(15), TaskType.FEATURE);
        project.addTask("Task 2", "Another task", IssueStatus.IN_PROGRESS, IssuePriority.HIGH, LocalDate.now().minusDays(1), LocalDate.now().plusDays(20), TaskType.FEATURE);
        // Predict the end date
        AnalyticsEngine analyticsEngine = new AnalyticsEngine();
        LocalDate predictedEndDate = analyticsEngine.predictProjectEndDate(project);

        // Test: Predicted end date should be later than the planned project end date
        assertTrue(predictedEndDate.isAfter(project.getDateOfEnd()));
    }

    @Test
    public void testPredictEndDate_SameAsPlannedEndDate() {
        // Setup: Project with a task ending exactly on the planned project end date
        User alice = new User("Alice", "alice@example.com", UserRole.TEAMMATE);
        Project project = new Project(
                alice,
                "Test Project",
                "Project for analytics test",
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(10),
                new BigDecimal("1000")
        );

        project.addTask("Task 1", "Basic task", IssueStatus.DONE, IssuePriority.LOW, LocalDate.now().minusDays(3), LocalDate.now().plusDays(10), TaskType.FEATURE);

        // Predict the end date
        AnalyticsEngine analyticsEngine = new AnalyticsEngine();
        LocalDate predictedEndDate = analyticsEngine.predictProjectEndDate(project);

        // Test: Predicted end date should be the same as the planned project end date
        assertEquals(project.getDateOfEnd(), predictedEndDate);
    }
}
