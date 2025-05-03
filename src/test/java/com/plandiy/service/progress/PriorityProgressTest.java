package com.plandiy.service.progress;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.FeatureTask;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.issue.task.TaskType;
import com.plandiy.model.project.Project;
import com.plandiy.model.user.User;
import com.plandiy.model.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriorityProgressTest {

    private PriorityTasksProgress priorityProgress;
    private User user;
    private Project project;
    private Task parentTask;

    @BeforeEach
    void setUp() {
        priorityProgress = new PriorityTasksProgress();

        user = new User("Alice", "alice@example.com", UserRole.TEAMMATE);
        project = new Project(user, "Test Project", "desc", LocalDate.now().minusDays(5), LocalDate.now().plusDays(5), new BigDecimal("10000"));

        parentTask = new FeatureTask("P1", "Parent", IssueStatus.IN_PROGRESS, IssuePriority.HIGH, LocalDate.now().minusDays(3), LocalDate.now().plusDays(3));
    }

    @Test
    void testUserPriorityProgress() {
        user.addIssue(new FeatureTask("T1", "Task 1", IssueStatus.DONE, IssuePriority.HIGH, LocalDate.now(), LocalDate.now().plusDays(5)));
        user.addIssue(new FeatureTask("T2", "Task 2", IssueStatus.TO_DO, IssuePriority.LOW, LocalDate.now(), LocalDate.now().plusDays(5)));
        user.setProgressStrategy(priorityProgress);

        int progress = user.calculateProgress();
        assertEquals(100, progress);
    }

    @Test
    void testProjectPriorityProgress() {
        project.addTask("Task 1", IssueStatus.TO_DO, IssuePriority.MEDIUM, LocalDate.now(), LocalDate.now().plusDays(2), TaskType.FEATURE);
        project.addTask("Task 2", IssueStatus.TO_DO, IssuePriority.LOW, LocalDate.now(), LocalDate.now().plusDays(3), TaskType.RESEARCH);
        project.setProgressStrategy(priorityProgress);

        int progress = project.calculateProgress();
        assertEquals(0, progress);
    }

    @Test
    void testTaskWithSubtasksPriorityProgress() {
        parentTask.addSubtask("S1", "Sub 1", IssueStatus.TO_DO, IssuePriority.LOW, LocalDate.now(), LocalDate.now().plusDays(1));
        parentTask.addSubtask("S2", "Sub 2", IssueStatus.DONE, IssuePriority.HIGH, LocalDate.now(), LocalDate.now().plusDays(1));
        parentTask.addSubtask("S3", "Sub 3", IssueStatus.TO_DO, IssuePriority.MEDIUM, LocalDate.now(), LocalDate.now().plusDays(1));
        parentTask.setProgressStrategy(priorityProgress);

        int progress = parentTask.calculateProgress();
        assertEquals(50, progress);  // 2 of 3 are not LOW priority
    }

    @Test
    void testEmptyListReturnsZero() {
        user.setProgressStrategy(priorityProgress);
        assertEquals(0, user.calculateProgress());
    }
}
