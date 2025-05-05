package com.plandiy;

import com.plandiy.controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen;

import java.io.IOException;
import java.util.Objects;

/**
 * Main class for launching the PlanDiy UI application.
 * <p>
 * This class initializes the JavaFX application, loads the main view
 * from FXML, sets the primary stage to full screen size, and displays it.
 * </p>
 *
 */
public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        Scene sc = new Scene(root);
        MainController.pStage = stage;

        Screen screen = Screen.getPrimary();
        double screenWidth = screen.getVisualBounds().getWidth();
        double screenHeight = screen.getVisualBounds().getHeight();

        stage.setWidth(screenWidth);
        stage.setHeight(screenHeight);

        stage.setTitle("PlanDiy");
        stage.setScene(sc);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
