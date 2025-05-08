package com.plandiy.controller;

import com.plandiy.model.dao.Dao;
import com.plandiy.model.dao.DemoTaskDao;
import com.plandiy.model.issue.IssuePriority;
import com.plandiy.model.issue.IssueStatus;
import com.plandiy.model.issue.task.Task;
import com.plandiy.model.issue.task.TaskType;
import com.plandiy.model.project.Project;
import com.plandiy.util.IconCache;
import com.plandiy.util.Toast;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddTaskController implements Initializable {
    private final Dao<Task> taskDao = DemoTaskDao.getInstance();
    private MainController mainController;  // Add a reference to MainController

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private Button btnClose;

    @FXML
    private ComboBox<TaskType> comboTaskType;

    @FXML
    private ComboBox<IssueStatus> comboIssueStatus;

    @FXML
    private ComboBox<IssuePriority> comboIssuePriority;

    @FXML
    private TextField txtName;

    @FXML
    private TextArea txtDescription;

    @FXML
    private javafx.scene.control.DatePicker startDate;

    @FXML
    private javafx.scene.control.DatePicker endDate;

    @FXML
    private Button btnAddTask;

    private Project project;

    public void setProject(Project project) {
        this.project = project;
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

    @FXML
    private void handleAddTask(ActionEvent event) {
        String name = txtName.getText();
        String description = txtDescription.getText();
        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();
        TaskType type = comboTaskType.getValue();
        IssueStatus status = comboIssueStatus.getValue();
        IssuePriority priority = comboIssuePriority.getValue();

        if (name.isEmpty() || start == null || end == null) {
            Toast.show(mainController.getPrimaryStage(), "All fields are required!", 3000);
            return;
        }
        Task newTask = project.addTask(name, description, status, priority, start, end, type);
        taskDao.create(newTask);
        System.out.println(taskDao.getAll().size());
        // Update the TableView
        mainController.addTaskToTable(newTask);

        Stage stage = (Stage) btnAddTask.getScene().getWindow();
        stage.close();
    }


}
