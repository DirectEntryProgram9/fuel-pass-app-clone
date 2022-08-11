package controller;

import db.InMemoryDB;
import db.User;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import util.Navigation;
import util.Routes;

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

    public static boolean isValidNIC(String input) {
        if (input.length() != 10) return false;
        if (!(input.endsWith("V") || input.endsWith("v"))) return false;
        if (!input.substring(0,9).matches("\\d+")) return false;
        return true;
    }

    public void initialize() {
        Platform.runLater(txtNIC::requestFocus);
        FadeTransition fd = new FadeTransition(Duration.millis(1000),pneRegisterForm);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.playFromStart();

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
                        lblNICStatus.setText("Invalid NIC ❌");
                        lblNICStatus.setTextFill(Color.RED);
                    }
                }
            }
        });
    }

    public void lblLogin_OnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.LOGIN);
    }

    public void btnRegister_OnAction(ActionEvent actionEvent) throws IOException {
        String firstName = txtFirstName.getText();
        if (firstName.isBlank()) {
            new Alert(Alert.AlertType.ERROR,"First name can't be empty").showAndWait();
            txtFirstName.requestFocus();
            return;
        }
        else if (!isName(firstName)) {
            new Alert(Alert.AlertType.ERROR,"First name is invalid, Please enter a valid first name").showAndWait();
            txtFirstName.requestFocus();
            txtFirstName.selectAll();
            return;
        }
        else if (!isName(txtLastName.getText())) {
            new Alert(Alert.AlertType.ERROR,"Last name is invalid, Please enter a valid last name").showAndWait();
            txtLastName.requestFocus();
            txtLastName.selectAll();
            return;
        }
        else if (txtAddress.getText().trim().length() < 3) {
            new Alert(Alert.AlertType.ERROR,"Address is invalid, Please enter a valid address").showAndWait();
            txtAddress.requestFocus();
            txtAddress.selectAll();
            return;
        }
        boolean result = InMemoryDB.registerUser(new User(txtNIC.getText(), txtFirstName.getText(), txtLastName.getText(), txtAddress.getText(),16));
        if (result) new Alert(Alert.AlertType.INFORMATION,"Registration is success, You will be redirected to Login Form").showAndWait();
        else {
            new Alert(Alert.AlertType.ERROR,"NIC is already existed, Please double check your NIC").showAndWait();
            txtNIC.requestFocus();
            txtNIC.selectAll();
            return;
        }
        lblLogin_OnMouseClicked(null);
    }
    public boolean isName(String input) {
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            if (!Character.isLetter(aChar) && aChar != ' ') return false;
        }
        return true;
    }
}