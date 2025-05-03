package com.plandiy.util;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Toast {

    public static void show(Stage ownerStage, String message, int durationInMillis) {
        Label toastLabel = new Label(message);
        toastLabel.setStyle(
                "-fx-background-radius: 8; " +
                        "-fx-background-color: rgba(0, 0, 0, 0.8); " +
                        "-fx-text-fill: white; " +
                        "-fx-padding: 10px 20px; " +
                        "-fx-font-size: 14px;"
        );

        Popup popup = new Popup();
        popup.getContent().add(toastLabel);
        popup.setAutoHide(true);
        toastLabel.setPrefWidth(300); // fixed width
        toastLabel.setAlignment(Pos.CENTER);

        toastLabel.setAlignment(Pos.CENTER);
        // Position toast at bottom center
        double x = ownerStage.getX() + (ownerStage.getWidth() - 300) / 2;
        double y = ownerStage.getY() + 30;

        popup.show(ownerStage, x, y);

        // Fade out animation
        FadeTransition fade = new FadeTransition(Duration.millis(500), toastLabel);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setDelay(Duration.millis(durationInMillis));
        fade.setOnFinished(e -> popup.hide());
        fade.play();
    }
}
