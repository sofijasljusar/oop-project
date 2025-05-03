package com.plandiy.service.progress;

import com.plandiy.model.issue.Issue;
import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.FeatureTask;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.user.User;
import com.plandiy.model.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserProgressCalculationTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Alice", "alice@example.com", UserRole.TEAMMATE);
    }

    private Task createTask(String id, IssueStatus status) {
        return new FeatureTask(id, "Task " + id, status, IssuePriority.MEDIUM,
                LocalDate.now(), LocalDate.now().plusDays(3));
    }

    @Test
    void testProgressWithNoTasks() {
        assertEquals(0, user.calculateProgress());
    }

    @Test
    void testProgressWithAllIncompleteTasks() throws Exception {
        var t1 = createTask("T1", IssueStatus.TO_DO);
        var t2 = createTask("T2", IssueStatus.IN_PROGRESS);

        var addTaskMethod = User.class.getDeclaredMethod("addIssue", Issue.class);
        addTaskMethod.setAccessible(true);
        addTaskMethod.invoke(user, t1);
        addTaskMethod.invoke(user, t2);

        assertEquals(0, user.calculateProgress());
    }

    @Test
    void testProgressWithSomeCompletedTasks() throws Exception {
        var t1 = createTask("T1", IssueStatus.DONE);
        var t2 = createTask("T2", IssueStatus.IN_PROGRESS);
        var t3 = createTask("T3", IssueStatus.DONE);

        var addTaskMethod = User.class.getDeclaredMethod("addIssue", Issue.class);
        addTaskMethod.setAccessible(true);
        addTaskMethod.invoke(user, t1);
        addTaskMethod.invoke(user, t2);
        addTaskMethod.invoke(user, t3);

        assertEquals(66, user.calculateProgress());
    }

    @Test
    void testProgressWithAllCompletedTasks() throws Exception {
        var t1 = createTask("T1", IssueStatus.DONE);
        var t2 = createTask("T2", IssueStatus.DONE);

        var addTaskMethod = User.class.getDeclaredMethod("addIssue", Issue.class);
        addTaskMethod.setAccessible(true);
        addTaskMethod.invoke(user, t1);
        addTaskMethod.invoke(user, t2);

        assertEquals(100, user.calculateProgress());
    }
}
