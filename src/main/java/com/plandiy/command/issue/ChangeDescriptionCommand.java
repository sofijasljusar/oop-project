package com.plandiy.command.issue;

import com.plandiy.command.Command;
import com.plandiy.model.issue.Issue;

/**
 * Command for changing the description of an {@link Issue}.
 */
public class ChangeDescriptionCommand implements Command {
    private final Issue issue;
    private final String newDescription;
    private String oldDescription;

    /**
     * Constructs a ChangeDescriptionCommand.
     *
     * @param issue the issue to modify
     * @param newDescription the new description text
     */
    public ChangeDescriptionCommand(Issue issue, String newDescription) {
        this.issue = issue;
        this.newDescription = newDescription;
    }


    @Override
    public boolean execute() {
        oldDescription = issue.getDescription();
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
