package controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.io.IOException;

public class RegisterFormController {

    public AnchorPane pneRegisterForm;
    public TextField txtNIC;
    public Label lblNICStatus;
    public TextField txtFirstName;
    public TextField txtLastName;
    public TextField txtAddress;
    public Button btnRegister;

    private void setDisableCmp(boolean disable) {
        txtFirstName.setDisable(disable);
        txtLastName.setDisable(disable);
        txtAddress.setDisable(disable);
        btnRegister.setDisable(disable);
    }

    private boolean isValidNIC(String input) {
        if (input.length() != 10) return false;
        if (!(input.endsWith("V") || input.endsWith("v"))) return false;
        if (!input.substring(0,9).matches("\\d+")) return false;
        return true;
    }

    public void initialize() {
        Platform.runLater(txtNIC::requestFocus);
        btnRegister.setDisable(true);
        txtNIC.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String previous, String current) {
                setDisableCmp(true);
                if (current.isBlank()) {
                    lblNICStatus.setText("Please Enter valid NIC to proceed");
                    lblNICStatus.setTextFill(Color.BLACK);
                    return;
                }
                else {
                    if (isValidNIC(current)) {
                        lblNICStatus.setText("Valid NIC ✅");
                        lblNICStatus.setTextFill(Color.GREEN);
                        setDisableCmp(false);
                    }
                    else {
                        lblNICStatus.setText("Invalid NIC ❎");
                        lblNICStatus.setTextFill(Color.RED);
                    }
                }
            }
        });
    }

    public void lblLogin_OnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/LoginForm.fxml"));
        AnchorPane pneContainer = (AnchorPane) pneRegisterForm.getParent();
        pneContainer.getChildren().clear();
        pneContainer.getChildren().add(root);
        AnchorPane.setTopAnchor(root,0.0);
        AnchorPane.setRightAnchor(root,0.0);
        AnchorPane.setLeftAnchor(root,0.0);
        AnchorPane.setBottomAnchor(root,0.0);
    }
}
