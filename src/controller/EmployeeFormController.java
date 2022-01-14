package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class EmployeeFormController {
    public AnchorPane Context3;
    public AnchorPane LoadContext1;

    public void OpenCustomerFormOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../View/CustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        LoadContext1.getChildren().clear();
        LoadContext1.getChildren().add(load);


    }

    public void OpenRoomDetailsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/RoomDetails.fxml");
        Parent load = FXMLLoader.load(resource);
        LoadContext1.getChildren().clear();
        LoadContext1.getChildren().add(load);
    }

    public void OpenFoodDetailsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../View/FoodsDetails.fxml");
        Parent load = FXMLLoader.load(resource);
        LoadContext1.getChildren().clear();
        LoadContext1.getChildren().add(load);

    }

    public void OpenPlaceOrderFormOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../View/FoodPlaceOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        LoadContext1.getChildren().clear();
        LoadContext1.getChildren().add(load);

    }

    public void OpenPalceOrderFromOne(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../View/RoomPlaceOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        LoadContext1.getChildren().clear();
        LoadContext1.getChildren().add(load);


    }

    public void openLoginFormOnAction(ActionEvent actionEvent) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("../View/LoginForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = (Stage) Context3.getScene().getWindow();
        stage.setScene(scene);
        stage.show();


    }
}
