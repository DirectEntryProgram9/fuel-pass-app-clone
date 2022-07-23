package controller;

import javafx.animation.FadeTransition;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class LoginFormController {
    public AnchorPane pneLoginForm;

    public void initialize() {
        FadeTransition fd = new FadeTransition(Duration.millis(1000),pneLoginForm);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.playFromStart();
    }
}
