package com.plandiy.command.issue;

import com.plandiy.command.Command;
import com.plandiy.model.issue.Issue;

import java.time.LocalDate;

public class ChangeStartCommand implements Command {
    private final Issue issue;
    private final LocalDate newDateOfStart;
    private LocalDate oldDateOfStart;

    public ChangeStartCommand (Issue issue, LocalDate newDateOfStart) {
        this.issue = issue;
        this.newDateOfStart = newDateOfStart;
    }


    @Override
    public boolean execute() {
        oldDateOfStart = issue.getDateOfStart();       // store the original value
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
