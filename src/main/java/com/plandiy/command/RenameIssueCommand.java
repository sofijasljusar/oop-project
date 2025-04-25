package com.plandiy.command;

import com.plandiy.model.issue.Issue;

public class RenameIssueCommand implements Command{
    private final Issue issue;
    private final String newName;
    private String oldName;

    public RenameIssueCommand(Issue issue, String newName) {
        this.issue = issue;
        this.newName = newName;
    }


    @Override
    public boolean execute() {
        oldName = issue.getName();       // store the original value
        issue.updateName(newName);
        return true;
    }

    @Override
    public void undo() {
        issue.updateName(oldName);
    }

    @Override
    public void redo() {
        issue.updateName(newName);
    }
}
