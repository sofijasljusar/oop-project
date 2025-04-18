package com.plandiy.model;

import com.plandiy.model.task.Subtask;
import com.plandiy.model.task.TaskPriority;
import com.plandiy.model.task.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    private Subtask subtask;
    LocalDate start = LocalDate.now();
    LocalDate end = LocalDate.now().plusDays(5);

    @BeforeEach
    void setUp() {
        subtask = new Subtask(
                "SUB-1",
                "Write unit tests",
                "Add tests for core logic",
                TaskStatus.TO_DO,
                TaskPriority.MEDIUM,
                start,
                end

        );
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("SUB-1", subtask.getId());
        assertEquals("Write unit tests", subtask.getName());
        assertEquals("Add tests for core logic", subtask.getDescription());
        assertEquals(TaskStatus.TO_DO, subtask.getStatus());
        assertEquals(TaskPriority.MEDIUM, subtask.getPriority());
        assertEquals(start, subtask.getDateOfStart());
        assertEquals(end, subtask.getDeadline());
        assertNull(subtask.getAssignedTo());
        assertTrue(subtask.getListOfSubtasks().isEmpty());
    }

    @Test
    void testIsBlockingDefaultFalse() {
        assertFalse(subtask.isBlocking());
    }

    @Test
    void testSetBlockingTrue() {
        subtask.setBlocking(true);
        assertTrue(subtask.isBlocking());
    }
}
