package controller;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;

public class UserDashboardFormController {

    public AnchorPane pneDashboardContainer;

    public void initialize() {
        Platform.runLater(pneDashboardContainer::requestFocus);
    }
}
