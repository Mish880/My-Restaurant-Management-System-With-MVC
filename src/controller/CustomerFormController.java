package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CustomerFormController {
    public AnchorPane Context4;
    public AnchorPane loadContext2;

    public void OpenCustomerSaveFormOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../View/SaveCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        loadContext2.getChildren().clear();
        loadContext2.getChildren().add(load);
    }

    public void OpenCustomerUpdateFormOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../View/CustomerUpdateForm.fxml");
        Parent load = FXMLLoader.load(resource);
        loadContext2.getChildren().clear();
        loadContext2.getChildren().add(load);
    }

    public void OpenCustomerSearchFormOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../View/Customer Search From.fxml");
        Parent load = FXMLLoader.load(resource);
        loadContext2.getChildren().clear();
        loadContext2.getChildren().add(load);
    }

    public void OpenCustomerDeleteFormOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../View/CustomerDeleteFrom.fxml");
        Parent load = FXMLLoader.load(resource);
        loadContext2.getChildren().clear();
        loadContext2.getChildren().add(load);
    }

    public void OpenAllCustomerFormOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../View/CustomerSelectAllForm.fxml");
        Parent load = FXMLLoader.load(resource);
        loadContext2.getChildren().clear();
        loadContext2.getChildren().add(load);
    }
}
