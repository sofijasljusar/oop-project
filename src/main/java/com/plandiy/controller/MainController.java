    package com.plandiy.controller;

    import com.plandiy.model.dao.Dao;
    import com.plandiy.model.dao.DemoProjectDao;
    import com.plandiy.model.dao.DemoTaskDao;
    import com.plandiy.model.dao.DemoUserDao;
    import com.plandiy.model.issue.task.*;
    import com.plandiy.model.project.Project;
    import com.plandiy.util.IconCache;
    import com.plandiy.util.Toast;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.ActionEvent;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import com.plandiy.model.issue.IssueStatus;
    import com.plandiy.model.issue.IssuePriority;
    import com.plandiy.model.user.User;

    import javafx.geometry.Insets;
    import javafx.geometry.Pos;
    import javafx.scene.Parent;
    import javafx.scene.Scene;
    import javafx.scene.control.*;
    import javafx.scene.control.cell.PropertyValueFactory;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.layout.Pane;
    import javafx.scene.layout.VBox;
    import javafx.stage.Modality;
    import javafx.stage.Stage;
    import javafx.stage.StageStyle;

    import java.io.IOException;
    import java.net.URL;
    import java.util.List;
    import java.util.Map;
    import java.util.ResourceBundle;
    import java.util.function.Consumer;

    public class MainController implements Initializable {
        private final Dao<User> userDao = DemoUserDao.getInstance();
        private final Dao<Task> taskDao = DemoTaskDao.getInstance();
        private final Dao<Project> projectDao = DemoProjectDao.getInstance(userDao, taskDao);

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

        @FXML
        private Button btnDeleteTask;

        public static Stage pStage;

        @FXML
        private VBox projectListVBox;

        public Stage getPrimaryStage() {
            return pStage;
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
            tcId.setCellFactory(column -> {
                return new TableCell<Task, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            Label label = new Label(item);
                            label.setStyle("-fx-text-fill: rgba(0, 0, 0, 0.8);");

                            // Add hover effect for underline
                            label.setOnMouseEntered(e -> label.setStyle("-fx-text-fill: rgba(0, 0, 0, 0.8); -fx-underline: true; -fx-cursor: hand"));
                            label.setOnMouseExited(e -> label.setStyle("-fx-text-fill: rgba(0, 0, 0, 0.8);"));
                            label.setOnMouseClicked(e -> {
                                Task task = getTableView().getItems().get(getIndex());
                                showDialogue("edit-task", controller -> {
                                    EditTaskController c = (EditTaskController) controller;
                                    c.setTask(task);
                                    Project project = projectDao.read("STAF");
                                    c.setup(project, MainController.this);
                                });
                            });
//                            label.setOnMouseClicked(e -> {
//                                Toast.show(getPrimaryStage(), "Feature in development :D", 2000);
//                                }
//                            );

                            setGraphic(label);
                        }
                    }
                };
            });


            tcName.setCellValueFactory(new PropertyValueFactory<>("name"));

            tcStatusIcon.setCellValueFactory(cellData -> {
                Task task = cellData.getValue();
                IssueStatus issueStatus = task.getStatus();
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
                IssuePriority issuePriority = task.getPriority();
                String iconPath = "/com/plandiy/images/" + issuePriority.getIconFileName();
                Image image = IconCache.get(issuePriority.getIconFileName());
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(18);
                imageView.setPreserveRatio(true);
                return new javafx.beans.property.SimpleObjectProperty<>(imageView);
            });

            tcAssignedTo.setCellValueFactory(cellData -> {
                User user = cellData.getValue().getAssignedTo();  // Assuming getAssignedTo() exists
                return (user != null) ? new javafx.beans.property.SimpleStringProperty(user.getName()) : new javafx.beans.property.SimpleStringProperty("Unassigned");
            });

            tcTaskIcon.setCellValueFactory(cellData -> {
                Task task = cellData.getValue();
                TaskType taskType = task.getType();
                String iconPath = "/com/plandiy/images/" + taskType.getIconFileName();
                Image image = IconCache.get(taskType.getIconFileName());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(20);  // Set the size of the icon
                imageView.setFitHeight(20);

                return new javafx.beans.property.SimpleObjectProperty<>(imageView);
            });

            Map<String, Project> projects = projectDao.getAll();
            for (Project project : projects.values()) {
                Button projectButton = new Button(project.getName());
                projectButton.setPrefWidth(184);
                projectButton.setPrefHeight(36);
                projectButton.setStyle("-fx-text-fill: white;");
                projectButton.getStyleClass().add("button1");
                projectButton.setAlignment(Pos.BASELINE_LEFT);
                projectButton.setPadding(new Insets(0, 0, 0, 30));

                ImageView icon = new ImageView(IconCache.get("board.png"));
                icon.setFitHeight(18);
                icon.setFitWidth(22);
                icon.setPreserveRatio(true);
                projectButton.setGraphic(icon);

//                projectButton.setOnAction(e -> {
//                    // handle loading project data
//                });

                projectListVBox.getChildren().add(projectButton);
            }

            Project demoProject = projectDao.read("STAF");  // todo: change from demo's hardcoded

            data = FXCollections.observableArrayList(demoProject.getListOfTasks());
            tbVTasks.setItems(data);

            // 1. Always fill the table’s width
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
            tcName.setMinWidth(100);
            tcName.setMaxWidth(Double.MAX_VALUE);
            tcName.setResizable(true);
            btnDeleteTask.disableProperty().bind(
                    tbVTasks.getSelectionModel().selectedItemProperty().isNull()
            );

        }

        @FXML
        void handleButtonClick(ActionEvent event) {
            if (event.getSource() == btnAddTask) {
                showDialogue("add-task", controller-> {
                    AddTaskController c = (AddTaskController) controller;
                    Project project = projectDao.read("STAF");
                    c.setup(project, this);
                });
            } else if (event.getSource() == btnDeleteTask) {
                Task selectedTask = (Task) tbVTasks.getSelectionModel().getSelectedItem();

                if (selectedTask == null) {
                    Toast.show(getPrimaryStage(), "No task selected!", 2500);
                    return;
                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setGraphic(null);
                alert.setResizable(false);
                alert.setTitle("Delete Task");
                alert.setHeaderText("Are you sure you want to delete this task?");
                alert.setContentText("Task: " + selectedTask.getName());

                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.getStyleClass().add("custom-alert");
                alert.getDialogPane().setStyle("-fx-font-family: 'Arial'; -fx-font-weight: normal; -fx-font-size: 14px;");
                Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.initStyle(StageStyle.TRANSPARENT);
                Pane headerPane = new Pane();
                headerPane.setStyle("-fx-background-color: #6F5CC2;");
                headerPane.setPrefHeight(53);

                Label headerLabel = new Label("Are you sure you want to delete this task?");
                headerLabel.setStyle("-fx-font-family: 'Arial'; -fx-font-weight: normal; -fx-font-size: 16px; -fx-text-fill: white;");
                headerPane.getChildren().add(headerLabel);
                headerLabel.layoutXProperty().bind(headerPane.widthProperty().subtract(headerLabel.widthProperty()).divide(2));
                headerLabel.layoutYProperty().bind(headerPane.heightProperty().subtract(headerLabel.heightProperty()).divide(2));

                alert.getDialogPane().setHeader(headerPane);

                dialogPane.getStylesheets().add(
                        getClass().getResource("/com/plandiy/css/styling.css").toExternalForm()
                );

                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        // Remove from DAO and table
                        taskDao.delete(selectedTask.getId());
                        data.remove(selectedTask); // Remove from ObservableList
                        refreshTaskTable();
                        Toast.show(getPrimaryStage(), "Task deleted!", 2500);
                    }
                });
            }
        }
        private void showDialogue(String fxml, Consumer<Object> controllerConfigurator) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/plandiy/" + fxml + ".fxml"));
                Parent parent = loader.load();
                Object controller = loader.getController();
                controllerConfigurator.accept(controller);
//                controller.setProject(projectDao.read("STAF"));
//                controller.setMainController(this);

                Stage stage = new Stage();
                Scene scene = new Scene(parent);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setAlwaysOnTop(true);
                stage.initOwner(pStage);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initStyle(javafx.stage.StageStyle.UNDECORATED);

                parent.applyCss();
                parent.layout();

                double dialogWidth = parent.prefWidth(-1);
                double dialogHeight = parent.prefHeight(-1);

                // Center BEFORE showing
                javafx.geometry.Rectangle2D screenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
                stage.setX((screenBounds.getWidth() - dialogWidth) / 2);
                stage.setY((screenBounds.getHeight() - dialogHeight) / 2);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void addTaskToTable(Task task) {
            data.add(task);
            refreshTaskTable();
            Toast.show(getPrimaryStage(), "Task added!", 3000);
        }

        public void refreshTaskTable() {
            tbVTasks.refresh();
        }


    }
