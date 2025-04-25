package com.plandiy.command.issue;

import com.plandiy.command.Command;
import com.plandiy.model.issue.Issue;

import java.time.LocalDate;

public class ChangeDeadlineCommand implements Command {
    private final Issue issue;
    private final LocalDate newDeadline;
    private LocalDate oldDeadline;

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
