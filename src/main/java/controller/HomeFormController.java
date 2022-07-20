package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomeFormController {

    public AnchorPane pneContainer;
    public AnchorPane pneLogin;

    public void initialize() throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/WelcomeForm.fxml"));
        pneContainer.getChildren().clear();
        pneContainer.getChildren().add(root);
        AnchorPane.setLeftAnchor(root,0.0);
        AnchorPane.setRightAnchor(root,0.0);
        AnchorPane.setBottomAnchor(root,0.0);
        AnchorPane.setTopAnchor(root,0.0);
    }

    public void imgLogo_OnMouseClicked(MouseEvent mouseEvent) throws IOException {
        initialize();
    }

    public void pneLogin_OnMouseClicked(MouseEvent mouseEvent) throws IOException {
        pneContainer.getChildren().clear();
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/AdminLoginForm.fxml"));
        pneContainer.getChildren().add(root);
        AnchorPane.setLeftAnchor(root,0.0);
        AnchorPane.setRightAnchor(root,0.0);
        AnchorPane.setBottomAnchor(root,0.0);
        AnchorPane.setTopAnchor(root,0.0);
    }

    public void pneLogin_OnKeyReleased(KeyEvent keyEvent) throws IOException {
        if (keyEvent.getCode() == KeyCode.ENTER || keyEvent.getCode() == KeyCode.SPACE) {
            pneLogin_OnMouseClicked(null);
        }
    }
}
