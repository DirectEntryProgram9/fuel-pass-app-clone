package controller;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class ControlCenterFormController {
    public AnchorPane pneControlCenter;

    public void initialize() {
        Platform.runLater(pneControlCenter::requestFocus);
        FadeTransition fd = new FadeTransition(Duration.millis(1000),pneControlCenter);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.playFromStart();
    }
}
