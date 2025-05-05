package com.plandiy.command;

import java.util.Stack;

/**
 * Manages execution, undo, and redo of {@link Command} objects.
 * Acts both as the Invoker and the Command history manager.
 * <p>
 * Executes commands.
 * Manages undo and redo operations with two internal stacks.
 */
public class CommandManager {
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    /**
     * Executes a command and adds it to the undo history if successful.
     *
     * @param command the command to execute
     */
    public void executeCommand(Command command) {
        if (command.execute()) {
            undoStack.push(command);
            redoStack.clear();
        }
    }

    /**
     * Undoes the last executed command.
     */
    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    /**
     * Redoes the last undone command.
     */
    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.redo();
            undoStack.push(command);
        }
    }
}
