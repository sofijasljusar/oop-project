package com.plandiy.controller;

import com.plandiy.model.issue.task.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class EditTaskController {
        private Task task;
    @FXML
    private Button btnClose;

    public void setTask(Task task) {
        this.task = task;
    }

    @FXML
    private void handleClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
