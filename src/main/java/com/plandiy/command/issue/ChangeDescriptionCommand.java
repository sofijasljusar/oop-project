package com.plandiy.command.issue;

import com.plandiy.command.Command;
import com.plandiy.model.issue.Issue;

public class ChangeDescriptionCommand implements Command {
    private final Issue issue;
    private final String newDescription;
    private String oldDescription;

    public ChangeDescriptionCommand(Issue issue, String newDescription) {
        this.issue = issue;
        this.newDescription = newDescription;
    }


    @Override
    public boolean execute() {
        oldDescription = issue.getDescription();       // store the original value
        issue.updateDescription(newDescription);
        return true;
    }

    @Override
    public void undo() {
        issue.updateDescription(oldDescription);
    }

    @Override
    public void redo() {
        issue.updateDescription(newDescription);
    }
}
