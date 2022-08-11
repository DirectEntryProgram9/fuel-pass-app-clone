package controller;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import db.InMemoryDB;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import util.Navigation;
import util.Routes;

import java.io.IOException;

public class LoginFormController {
    public AnchorPane pneLoginForm;
    public TextField txtNIC;
    public Button btnLogin;

    public void initialize() {
        FadeTransition fd = new FadeTransition(Duration.millis(1000),pneLoginForm);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.playFromStart();
        Platform.runLater(txtNIC::requestFocus);
    }

    public void lblRegister_OnMouseClicked(MouseEvent mouseEvent) throws IOException {
        Navigation.navigate(Routes.REGISTRATION);
    }

    public void btnLogin_OnAction(ActionEvent actionEvent) throws IOException, WriterException {
        if (!RegisterFormController.isValidNIC(txtNIC.getText()) || InMemoryDB.findUser(txtNIC.getText()) == null) {
            new Alert(Alert.AlertType.ERROR,"Please enter a valid NIC number to login !").showAndWait();
            txtNIC.selectAll();
            txtNIC.requestFocus();
            return;
        }
        UserDashboardFormController ctrl = (UserDashboardFormController) Navigation.navigate(Routes.DASHBOARD);
        ctrl.setData(txtNIC.getText());
    }
}
