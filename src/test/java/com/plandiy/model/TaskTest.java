package com.plandiy.model;

import com.plandiy.model.task.Subtask;
import com.plandiy.model.task.Task;
import com.plandiy.model.task.TaskPriority;
import com.plandiy.model.task.TaskStatus;
import com.plandiy.model.user.User;
import com.plandiy.model.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;
    private final LocalDate start = LocalDate.of(2025, 4, 1);
    private final LocalDate end = LocalDate.of(2025, 4, 30);

    @BeforeEach
    void setUp() {
        task = new Task(
                "TASK-1",
                "Implement feature X",
                "Feature details",
                TaskStatus.TO_DO,
                TaskPriority.HIGH,
                start,
                end
        );
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("TASK-1", task.getId());
        assertEquals("Implement feature X", task.getName());
        assertEquals("Feature details", task.getDescription());
        assertEquals(TaskStatus.TO_DO, task.getStatus());
        assertEquals(TaskPriority.HIGH, task.getPriority());
        assertEquals(start, task.getDateOfStart());
        assertEquals(end, task.getDeadline());
        assertNull(task.getAssignedTo());
        assertTrue(task.getListOfSubtasks().isEmpty());
    }

    @Test
    void testConstructorWithoutDescription() {
        Task taskWithoutDesc = new Task(
                "TASK-2",
                "Quick Task",
                TaskStatus.IN_PROGRESS,
                TaskPriority.MEDIUM,
                start,
                end
        );

        assertEquals("", taskWithoutDesc.getDescription());
    }

    @Test
    void testUpdateStatus() {
        task.updateStatus(TaskStatus.DONE);
        assertEquals(TaskStatus.DONE, task.getStatus());
    }

    @Test
    void testAssignTo() {
        User user = new User("Anna", "anna@mail.com", UserRole.TEAMMATE);
        task.assignTo(user);
        assertEquals(user, task.getAssignedTo());
    }

    @Test
    void testAddSubtaskAutoId() {
        task.addSubtask("Fix bug", "Fix login issue", TaskStatus.TO_DO, TaskPriority.LOW, start, end, null);
        task.addSubtask("Write tests", "Cover edge cases", TaskStatus.TO_DO, TaskPriority.MEDIUM, start, end, null);

        assertEquals(2, task.getListOfSubtasks().size());

        Subtask first = task.getListOfSubtasks().get(0);
        Subtask second = task.getListOfSubtasks().get(1);

        assertEquals("TASK-1-1", first.getId());
        assertEquals("TASK-1-2", second.getId());
    }

    @Test
    void testDeleteSubtask() {
        task.addSubtask("Code review", "Review PR", TaskStatus.TO_DO, TaskPriority.MEDIUM, start, end, null);
        Subtask subtask = task.getListOfSubtasks().get(0);
        task.deleteSubtask(subtask);
        assertTrue(task.getListOfSubtasks().isEmpty());
    }
}
