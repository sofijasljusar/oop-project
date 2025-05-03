package com.plandiy.command.issue;
import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.ResearchTask;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.issue.subtask.Subtask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class ChangeDeadlineCommandTest {

    @Test
    public void testChangeDeadlineOnTask() {
        Task task = new ResearchTask("T1", "Task", IssueStatus.TO_DO, IssuePriority.MEDIUM,
                LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));

        LocalDate originalDeadline = task.getDeadline();
        LocalDate newDeadline = LocalDate.of(2025, 6, 30);

        ChangeDeadlineCommand command = new ChangeDeadlineCommand(task, newDeadline);
        assertTrue(command.execute());
        assertEquals(newDeadline, task.getDeadline());

        command.undo();
        assertEquals(originalDeadline, task.getDeadline());

        command.redo();
        assertEquals(newDeadline, task.getDeadline());
    }

    @Test
    public void testChangeDeadlineOnSubtask() {
        Subtask subtask = new Subtask("S1", "Subtask", "", IssueStatus.TO_DO, IssuePriority.MEDIUM,
                LocalDate.of(2024, 3, 1), LocalDate.of(2024, 9, 30));

        LocalDate originalDeadline = subtask.getDeadline();
        LocalDate newDeadline = LocalDate.of(2025, 3, 31);

        ChangeDeadlineCommand command = new ChangeDeadlineCommand(subtask, newDeadline);
        assertTrue(command.execute());
        assertEquals(newDeadline, subtask.getDeadline());

        command.undo();
        assertEquals(originalDeadline, subtask.getDeadline());

        command.redo();
        assertEquals(newDeadline, subtask.getDeadline());
    }
}
