    package com.plandiy.controller;

    import com.plandiy.model.dao.Dao;
    import com.plandiy.model.dao.DemoTaskDao;
    import com.plandiy.model.dao.DemoUserDao;
    import com.plandiy.model.issue.task.*;
    import com.plandiy.util.IconCache;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import com.plandiy.model.issue.IssueStatus;
    import com.plandiy.model.issue.IssuePriority;
    import com.plandiy.model.user.User;
    import com.plandiy.model.user.UserRole;

    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.Button;
    import javafx.scene.control.TableView;
    import javafx.scene.control.TableColumn;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.stage.Modality;
    import javafx.stage.Stage;

    import java.io.IOException;
    import java.net.URL;
    import java.time.LocalDate;
    import java.util.List;
    import java.util.Random;
    import java.util.ResourceBundle;
    import java.util.Stack;

    public class HelloController implements Initializable {
        private final Dao<User, String> userDao = new DemoUserDao();

        @FXML
        private TableView tbVTasks;

        @FXML
        private TableColumn<Task, String> tcId;

        @FXML
        private TableColumn<Task, String> tcName;

        @FXML
        private TableColumn<Task, ImageView> tcStatusIcon;

        @FXML
        private TableColumn<Task, ImageView> tcPriorityIcon;

        @FXML
        private TableColumn<Task, String> tcAssignedTo;

        @FXML
        private TableColumn<Task, ImageView> tcTaskIcon;

        private ObservableList<Task> data;

        @FXML
        private Button btnAddTask;

        public static Stage pStage;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
            tcStatusIcon.setCellValueFactory(cellData -> {
                Task task = cellData.getValue();
                IssueStatus issueStatus = task.getStatus(); // Assuming each task has a type

                // Load the icon based on task type
                String iconPath = "/com/plandiy/images/" + issueStatus.getIconFileName();
                Image image = IconCache.get(issueStatus.getIconFileName());
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(14);
                imageView.setPreserveRatio(true);
                return new javafx.beans.property.SimpleObjectProperty<>(imageView);
            });

            tcPriorityIcon.setCellValueFactory(cellData -> {
                Task task = cellData.getValue();
                IssuePriority issuePriority = task.getPriority(); // Assuming each task has a type

                // Load the icon based on task type
                String iconPath = "/com/plandiy/images/" + issuePriority.getIconFileName();
                Image image = IconCache.get(issuePriority.getIconFileName());
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(18);
                imageView.setPreserveRatio(true);
                return new javafx.beans.property.SimpleObjectProperty<>(imageView);
            });

            tcAssignedTo.setCellValueFactory(new PropertyValueFactory<>("assignedTo"));
            tcAssignedTo.setCellValueFactory(cellData -> {
                User user = cellData.getValue().getAssignedTo();  // Assuming getAssignedTo() exists
                return (user != null) ? new javafx.beans.property.SimpleStringProperty(user.getName()) : new javafx.beans.property.SimpleStringProperty("Unassigned");
            });

            tcTaskIcon.setCellValueFactory(cellData -> {
                Task task = cellData.getValue();
                TaskType taskType = task.getType(); // Assuming each task has a type

                // Load the icon based on task type
                String iconPath = "/com/plandiy/images/" + taskType.getIconFileName();
                Image image = IconCache.get(taskType.getIconFileName());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(20);  // Set the size of the icon
                imageView.setFitHeight(20);

                return new javafx.beans.property.SimpleObjectProperty<>(imageView);
            });

            User user = userDao.read("dana.lee@example.com");

            data = FXCollections.observableArrayList();
            Random rnd = new Random();

            for (int i = 1; i <= 31; i++) {
                String id = String.format("RFRE-%d", i);
                String name = "Drink " + i + " bottles of water";
                String desc = "Auto-generated description for task #" + i;
                IssueStatus st = IssueStatus.values()[rnd.nextInt(IssueStatus.values().length)];
                IssuePriority pr = IssuePriority.values()[rnd.nextInt(IssuePriority.values().length)];
                LocalDate start = LocalDate.of(2025,5,1);
                LocalDate end   = LocalDate.of(2025,5,30);

                // pick a random TaskType
                TaskType type = TaskType.values()[ rnd.nextInt(TaskType.values().length) ];
                Task task = switch (type) {
                    case FEATURE -> new FeatureTask(id, name, desc, st, pr, start, end);
                    case BUG -> new BugTask(id, name, desc, st, pr, start, end);
                    case RESEARCH -> new ResearchTask(id, name, desc, st, pr, start, end);
                };
                task.assignTo(user);
                data.add(task);
            }

            tbVTasks.setItems(data);

            // 1. ALWAYS fill the table’s width
            tbVTasks.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            // 2. Fix the width of all columns except “Name”
            List<TableColumn<Task,?>> fixedCols = List.of(
                    tcTaskIcon, tcId, tcStatusIcon, tcPriorityIcon, tcAssignedTo
            );
            fixedCols.forEach(col -> {
                double w = col.getPrefWidth();
                // lock min/max/pref all to the same value
                col.setMinWidth(w);
                col.setMaxWidth(w);
                col.setResizable(false);
            });

            // 3. Let “Name” expand
            //    (it already has a prefWidth in your FXML, but we now allow it to grow)
            //    You can also give it a reasonable minimum so that very small windows don’t squash it too far:
            tcName.setMinWidth(100);
            tcName.setMaxWidth(Double.MAX_VALUE);
            tcName.setResizable(true);

        }

        @FXML
        void handleButtonClick(ActionEvent event) {
            if (event.getSource() == btnAddTask) {
                showDialogue("add-task");
            }
        }

        private void showDialogue(String fxml) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/com/plandiy/"+fxml+".fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setAlwaysOnTop(true);
                stage.setX(pStage.getX() + 50);
                stage.setY(pStage.getY() + 50);
                stage.initOwner(pStage);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
                // Center the dialog on the screen
                stage.setOnShown(e -> {
                    javafx.geometry.Rectangle2D screenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
                    stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
                    stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
                });
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
