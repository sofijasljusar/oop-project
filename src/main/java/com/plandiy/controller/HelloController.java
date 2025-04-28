    package com.plandiy.controller;

    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import com.plandiy.model.issue.IssueStatus;
    import com.plandiy.model.issue.IssuePriority;
    import com.plandiy.model.issue.task.Task;
    import com.plandiy.model.issue.task.FeatureTask;
    import com.plandiy.model.user.User;
    import com.plandiy.model.user.UserRole;

    import javafx.scene.control.TableView;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.cell.PropertyValueFactory;

    import java.net.URL;
    import java.time.LocalDate;
    import java.util.ResourceBundle;
    public class HelloController implements Initializable {

        @FXML
        private TableView tbVTasks;

        @FXML
        private TableColumn<Task, String> tcId;

        @FXML
        private TableColumn<Task, String> tcName;

        @FXML
        private TableColumn<Task, IssueStatus> tcStatus;

        @FXML
        private TableColumn<Task, IssuePriority> tcPriority;

        @FXML
        private TableColumn<Task, String> tcAssignedTo;


        private ObservableList<Task> data;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
            tcStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            tcPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
            tcAssignedTo.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));
            tcAssignedTo.setCellValueFactory(cellData -> {
                User user = cellData.getValue().getAssignedTo();  // Assuming getAssignedTo() exists
                return (user != null) ? new javafx.beans.property.SimpleStringProperty(user.getName()) : new javafx.beans.property.SimpleStringProperty("Unassigned");
            });

            User user = new User("John Doe", "john.doe@example.com", UserRole.TEAMMATE);


            data = FXCollections.observableArrayList();

            for (int i = 1; i <= 31; i++) {
                FeatureTask featureTask = new FeatureTask(
                        "FT-" + String.format("%03d", i),  // Task ID like FT-001, FT-002, ...
                        "Task #" + i + " description",
                        "Description for task #" + i,
                        IssueStatus.TO_DO,
                        IssuePriority.HIGH,
                        LocalDate.of(2025, 5, 1),
                        LocalDate.of(2025, 5, 30)
                );
                featureTask.assignTo(user);  // Assign user to the task
                data.add(featureTask);  // Add task to data
            }

            tbVTasks.setItems(data);
        }
    }