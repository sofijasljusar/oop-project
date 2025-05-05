package com.plandiy.command.issue;

import com.plandiy.command.Command;
import com.plandiy.model.issue.Issue;

import java.time.LocalDate;

/**
 * Command for changing the deadline of an {@link Issue}.
 * <p>
 * Supports undo and redo operations.
 */
public class ChangeDeadlineCommand implements Command {
    private final Issue issue;
    private final LocalDate newDeadline;
    private LocalDate oldDeadline;

    /**
     * Constructs a ChangeDeadlineCommand with the specified issue and new deadline.
     *
     * @param issue the issue to update
     * @param newDeadline the new deadline date
     */
    public ChangeDeadlineCommand(Issue issue, LocalDate newDeadline) {
        this.issue = issue;
        this.newDeadline = newDeadline;
    }


    @Override
    public boolean execute() {
        oldDeadline = issue.getDeadline();       // store the original value
        issue.updateDeadline(newDeadline);
        return true;
    }

    @Override
    public void undo() {
        issue.updateDeadline(oldDeadline);
    }

    @Override
    public void redo() {
        issue.updateDeadline(newDeadline);
    }
}
