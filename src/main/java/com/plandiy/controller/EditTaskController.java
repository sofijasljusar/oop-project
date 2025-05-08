package com.plandiy.controller;

import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.issue.task.TaskType;
import com.plandiy.model.project.Project;
import com.plandiy.model.user.User;
import com.plandiy.util.IconCache;
import com.plandiy.util.Toast;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class EditTaskController implements Initializable {
    private MainController mainController;  // Add a reference to MainController
    private Task task;

    @FXML private TextField txtName;
    @FXML private TextArea txtDescription;
    @FXML private ComboBox<TaskType> comboTaskType;
    @FXML private ComboBox<IssueStatus> comboIssueStatus;
    @FXML private ComboBox<IssuePriority> comboIssuePriority;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private ComboBox<User> assignee;
    @FXML private Button btnClose;
    @FXML private Button btnEditTask;
    private Project project;


    public void setTask(Task task) {
        this.task = task;
        populateFields();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private void populateFields() {
        if (task == null) return;

        txtName.setText(task.getName());
        txtDescription.setText(task.getDescription());
        comboTaskType.setValue(task.getType());
        comboIssueStatus.setValue(task.getStatus());
        comboIssuePriority.setValue(task.getPriority());
        startDate.setValue(task.getDateOfStart());
        endDate.setValue(task.getDeadline());
        assignee.setValue(task.getAssignedTo());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtName.setFocusTraversable(false);
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

    @FXML
    private void handleEditTask() {
        if (task == null) return;

        if (txtName.getText().isEmpty() || startDate.getValue() == null || endDate.getValue() == null) {
            Toast.show(mainController.getPrimaryStage(), "All fields are required!", 3000);
            return;
        }

        task.updateName(txtName.getText());
        task.updateDescription(txtDescription.getText());
        task.updateType(comboTaskType.getValue());
        task.updateStatus(comboIssueStatus.getValue());
        task.updatePriority(comboIssuePriority.getValue());
        task.updateDateOfStart(startDate.getValue());
        task.updateDeadline(endDate.getValue());
        task.assignTo(assignee.getValue());

        mainController.refreshTaskTable();

        Stage stage = (Stage) btnEditTask.getScene().getWindow();
        stage.close();
    }

    public void setup(Project project, MainController mainController) {
        this.project = project;
        this.mainController = mainController;

        ObservableList<User> users = FXCollections.observableArrayList(project.getContributors());
        assignee.setItems(users);
        assignee.setValue(task.getAssignedTo());

        assignee.setCellFactory(cb -> new ListCell<>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                setText((empty || item == null) ? null : item.getName());
            }
        });

        assignee.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                setText((empty || item == null) ? null : item.getName());
            }
        });
    }

}