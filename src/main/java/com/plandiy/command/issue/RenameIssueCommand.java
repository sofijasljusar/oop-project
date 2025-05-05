package com.plandiy.command.issue;

import com.plandiy.command.Command;
import com.plandiy.model.issue.Issue;

/**
 * Redoes the last undone command.
 */
public class RenameIssueCommand implements Command {
    private final Issue issue;
    private final String newName;
    private String oldName;

    /**
     * Constructs a RenameIssueCommand.
     *
     * @param issue the issue to rename
     * @param newName the new name for the issue
     */
    public RenameIssueCommand(Issue issue, String newName) {
        this.issue = issue;
        this.newName = newName;
    }


    @Override
    public boolean execute() {
        oldName = issue.getName();
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
