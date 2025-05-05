package com.plandiy.util;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Utility class to show temporary toast-style popup messages in a JavaFX application.
 * <p>
 * This can be used to display brief notifications to the user,
 * such as confirmations or alerts, with a fade-out effect.
 */
public class Toast {

    /**
     * Displays a toast message near the top center of the given stage.
     *
     * @param ownerStage       the stage on which the toast should be shown
     * @param message          the message to display
     * @param durationInMillis the duration (in milliseconds) before the toast disappears
     */
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
