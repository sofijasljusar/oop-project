package com.plandiy.controller;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.TaskType;
import com.plandiy.util.IconCache;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTaskController implements Initializable {
    @FXML
    private Button btnClose;

    @FXML
    private ComboBox<TaskType> comboTaskType;

    @FXML
    private ComboBox<IssueStatus> comboIssueStatus;

    @FXML
    private ComboBox<IssuePriority> comboIssuePriority;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboTaskType.setItems(FXCollections.observableArrayList(TaskType.values()));
        comboTaskType.setCellFactory(cb -> new ListCell<>() {
            @Override
            protected void updateItem(TaskType item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.name());
                    Image image = IconCache.get(item.getIconFileName());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(16);
                    imageView.setFitHeight(16);
                    setGraphic(imageView);
                }
            }
        });

        comboTaskType.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(TaskType item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.name());
                    setTextFill(Color.WHITE);
                    Image image = IconCache.get(item.getIconFileName());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(16);
                    imageView.setFitHeight(16);
                    setGraphic(imageView);
                }
            }
        });

        comboTaskType.setValue(TaskType.FEATURE);

        comboIssueStatus.setItems(FXCollections.observableArrayList(IssueStatus.values()));

        comboIssueStatus.setCellFactory(cb -> new ListCell<>() {
            @Override
            protected void updateItem(IssueStatus item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(null);
                    Image image = IconCache.get(item.getIconFileName());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(14);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
            }
        });

        comboIssueStatus.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(IssueStatus item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(null);
                    Image image = IconCache.get(item.getIconFileName());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(14);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
            }
        });

        comboIssueStatus.setValue(IssueStatus.TO_DO);



        // === Issue Priority ===
        comboIssuePriority.setItems(FXCollections.observableArrayList(IssuePriority.values()));
        comboIssuePriority.setCellFactory(cb -> new ListCell<>() {
            @Override
            protected void updateItem(IssuePriority item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.name());
                    Image image = IconCache.get(item.getIconFileName());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(18);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
            }
        });
        comboIssuePriority.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(IssuePriority item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(null); // no text
                    Image image = IconCache.get(item.getIconFileName());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(18);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
            }
        });
        comboIssuePriority.setValue(IssuePriority.MEDIUM); // default

    }



    @FXML
    private void handleClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
