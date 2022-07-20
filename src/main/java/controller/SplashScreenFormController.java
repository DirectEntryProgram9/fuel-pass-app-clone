package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SplashScreenFormController {
    public Label lblStatus;
    public Rectangle pgbContainer;
    public Rectangle pgbLoad;

    public void initialize() {
        Timeline timeline = new Timeline();
        KeyFrame frameOne = new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblStatus.setText("Connecting with the database...!");
                pgbLoad.setWidth(pgbLoad.getWidth() + 70);
            }
        });
        KeyFrame frameTwo = new KeyFrame(Duration.millis(1250), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblStatus.setText("Loading data...!");
                pgbLoad.setWidth(pgbLoad.getWidth() + 130);
            }
        });
        KeyFrame frameThree = new KeyFrame(Duration.millis(2250), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblStatus.setText("Setting up the UI...!");
                pgbLoad.setWidth(pgbLoad.getWidth() + 250);
            }
        });
        KeyFrame frameFour = new KeyFrame(Duration.millis(3000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    pgbLoad.setWidth(pgbContainer.getWidth());
                    Parent root = FXMLLoader.load(this.getClass().getResource("/view/HomeForm.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("National Fuel Pass App");
                    stage.show();
                    stage.centerOnScreen();
                    lblStatus.getScene().getWindow().hide();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        timeline.getKeyFrames().addAll(frameOne,frameTwo,frameThree,frameFour);
        timeline.playFromStart();
    }
}
