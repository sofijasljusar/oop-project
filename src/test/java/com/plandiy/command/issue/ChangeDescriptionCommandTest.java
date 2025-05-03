package com.plandiy.command.issue;
import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.FeatureTask;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.issue.subtask.Subtask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ChangeDescriptionCommandTest {

    @Test
    public void testChangeDescriptionOnTask() {
        Task task = new FeatureTask("T1", "Task", IssueStatus.DONE, IssuePriority.LOW, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 11, 30));
        task.updateDescription("Initial description");

        ChangeDescriptionCommand command = new ChangeDescriptionCommand(task, "Updated description");
        assertTrue(command.execute());
        assertEquals("Updated description", task.getDescription());

        command.undo();
        assertEquals("Initial description", task.getDescription());

        command.redo();
        assertEquals("Updated description", task.getDescription());
    }

    @Test
    public void testChangeDescriptionOnSubtask() {
        Subtask subtask = new Subtask("S1", "Subtask", "", IssueStatus.TO_DO, IssuePriority.HIGH, LocalDate.of(2024, 3, 1), LocalDate.of(2024, 11, 30));
        subtask.updateDescription("Original subtask desc");

        ChangeDescriptionCommand command = new ChangeDescriptionCommand(subtask, "New subtask desc");
        assertTrue(command.execute());
        assertEquals("New subtask desc", subtask.getDescription());

        command.undo();
        assertEquals("Original subtask desc", subtask.getDescription());

        command.redo();
        assertEquals("New subtask desc", subtask.getDescription());
    }
}
