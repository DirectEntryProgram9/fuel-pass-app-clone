package controller;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class AdminLoginFormController {
    private static int attemps = 3;
    private static final String ADMIN_PASSWORD = "dep9@PUBUDU";
    public PasswordField txtPassword;
    public AnchorPane pneAdminLoginForm;

    public void initialize() {
        FadeTransition fd = new FadeTransition(Duration.millis(1000),pneAdminLoginForm);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.playFromStart();

        Platform.runLater(txtPassword::requestFocus);
    }

    public void txtPassword_OnAction(ActionEvent actionEvent) throws IOException, URISyntaxException {
        if (!txtPassword.getText().equals(ADMIN_PASSWORD)) {
            if (attemps == 0) {
                new Alert(Alert.AlertType.ERROR,"You have reached maximum number of attemps...").showAndWait();
                Platform.exit();
                return;
            }
            URL resource = this.getClass().getResource("/audios/alertSound.mp3");
            Media media = new Media(resource.toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();

            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Admin Password. You have " + attemps + " more attemps to try again");
            attemps -= 1;

            InputStream resourceAsStream = this.getClass().getResourceAsStream("/images/padlock.png");
            Image image = new Image(resourceAsStream);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(45);
            imageView.setFitHeight(45);
            alert.setGraphic(imageView);
            alert.setTitle("Access Denied");
            alert.setHeaderText("Invalid Login Credentials");
            alert.showAndWait();

            mediaPlayer.dispose();

            txtPassword.requestFocus();
            return;
        }
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/ControlCenterForm.fxml"));
        AnchorPane pneContainer = (AnchorPane) pneAdminLoginForm.getParent();
        pneContainer.getChildren().clear();
        pneContainer.getChildren().add(root);
        AnchorPane.setLeftAnchor(root,0.0);
        AnchorPane.setRightAnchor(root,0.0);
        AnchorPane.setBottomAnchor(root,0.0);
        AnchorPane.setTopAnchor(root,0.0);
    }
}
