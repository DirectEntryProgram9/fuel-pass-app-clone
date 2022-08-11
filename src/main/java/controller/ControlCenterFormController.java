package controller;

import db.InMemoryDB;
import db.User;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Optional;

public class ControlCenterFormController {
    public AnchorPane pneControlCenter;
    public TableView<User> tblUsers;
    public TextField txtSearch;
    public Button btnUpdateQuota;
    public Button btnRemoveUsers;
    public Spinner<Integer> txtQuota;
    //public TableColumn colAddress;

    public void initialize() {
        Platform.runLater(pneControlCenter::requestFocus);
        FadeTransition fd = new FadeTransition(Duration.millis(1000),pneControlCenter);
        fd.setFromValue(0);
        fd.setToValue(1);
        fd.playFromStart();
        txtQuota.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,20,16));

/*        pneControlCenter.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number prev, Number curr) {
                if (prev.doubleValue() == 0.0) return;
                double diff = curr.doubleValue() - prev.doubleValue();
                double preWidth = colAddress.getWidth() + diff;
                if (preWidth < 148) preWidth = 148;
                colAddress.setPrefWidth(preWidth);
            }
        });*/

        tblUsers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblUsers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("quota"));
        tblUsers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tblUsers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tblUsers.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));

        tblUsers.setItems(FXCollections.observableArrayList(InMemoryDB.getUserDatabase()));
        tblUsers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);                          // Multiple selection criteria

        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String previous, String current) {
                if (previous.equals(current)) return;
                tblUsers.setItems(FXCollections.observableArrayList(InMemoryDB.findUsers(current)));
            }
        });
        btnRemoveUsers.setDisable(true);
        txtQuota.setDisable(true);
        btnUpdateQuota.setDisable(true);
        tblUsers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observableValue, User previous, User current) {
                btnRemoveUsers.setDisable(tblUsers.getSelectionModel().getSelectedItems().isEmpty());
                txtQuota.setDisable(btnRemoveUsers.isDisable());
                btnUpdateQuota.setDisable(btnRemoveUsers.isDisable());
            }
        });
        /*ObservableList<User> olUsers = tblUsers.getItems();
        for (User user : InMemoryDB.getUserDatabase()) {
            olUsers.add(user);
        }*/
    }


    public void btnUpdateQuota_OnAction(ActionEvent actionEvent) {
        /*if (txtQuota.getValue() < 0) {
            new Alert(Alert.AlertType.ERROR,"Invalid Quota").showAndWait();
            return;
        }*/
        ObservableList<User> selectedUsers = tblUsers.getSelectionModel().getSelectedItems();
        for (User user : selectedUsers) {
            user.setQuota(txtQuota.getValue());
        }
        tblUsers.refresh();
    }

    public void btnRemoveUsers_OnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete these users ?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.NO) {
            tblUsers.getSelectionModel().clearSelection();
            return;
        }
        ObservableList<User> selectedUsers = tblUsers.getSelectionModel().getSelectedItems();
        for (User user : selectedUsers) {
            InMemoryDB.removeUser(user.getNic());
        }
        tblUsers.getItems().removeAll(selectedUsers);
        tblUsers.getSelectionModel().clearSelection();
    }
}
