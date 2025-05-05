package com.plandiy.command.issue;

import com.plandiy.command.Command;
import com.plandiy.model.issue.Issue;

import java.time.LocalDate;

/**
 * Command for changing the start date of an {@link Issue}.
 */
public class ChangeStartCommand implements Command {
    private final Issue issue;
    private final LocalDate newDateOfStart;
    private LocalDate oldDateOfStart;

    /**
     * Constructs a ChangeStartCommand.
     *
     * @param issue the issue to modify
     * @param newDateOfStart the new start date
     */
    public ChangeStartCommand (Issue issue, LocalDate newDateOfStart) {
        this.issue = issue;
        this.newDateOfStart = newDateOfStart;
    }


    @Override
    public boolean execute() {
        oldDateOfStart = issue.getDateOfStart();
        issue.updateDateOfStart(newDateOfStart);
        return true;
    }

    @Override
    public void undo() {
        issue.updateDateOfStart(oldDateOfStart);
    }

    @Override
    public void redo() {
        issue.updateDateOfStart(newDateOfStart);
    }
}
