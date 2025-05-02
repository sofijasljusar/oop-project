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

public class MainApplication extends Application {
    double x, y = 0;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-view.fxml")));
        Scene sc = new Scene(root);
        MainController.pStage = stage;

        // Get the screen size (visual bounds)
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
