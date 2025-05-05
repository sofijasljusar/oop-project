package com.plandiy.command;

/**
 * The Command interface defines methods for executing, undoing, and redoing operations.
 */
public interface Command {

    /**
     * Executes the command.
     *
     * @return true if execution was successful, otherwise false
     */
    boolean execute();

    /**
     * Undoes the command.
     */
    void undo();

    /**
     * Redoes the command.
     */
    void redo();
}
