package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.OrderDetails;
import tm.OrderDetailTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CheffFormController {
    public TableView tblFoodOrderDetails;
    public TableColumn colFoodItemCode;
    public TableColumn colOrderId;
    public TableColumn colQTY;
    public TableColumn colPrice;
    public AnchorPane cheffcontext;

    public void initialize(){
    try{
        colFoodItemCode.setCellValueFactory(new PropertyValueFactory<>("FoodsItemCode"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("Orderid"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        setFoodOrderDetailTable(new FoodOrderDetailSQController().getAllOrderDetails());

    } catch (SQLException throwables) {
        throwables.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}
private void setFoodOrderDetailTable(ArrayList<OrderDetails>orderdetails){
    ObservableList<OrderDetailTm> obList = FXCollections.observableArrayList();
    orderdetails.forEach(e->{
        obList.add(new OrderDetailTm(e.getFOODSitemCode(),e.getOrderId(),e.getQty(),e.getPrice()));
    });
    tblFoodOrderDetails.setItems(obList);
  }

    public void openLoginFormOnAction(ActionEvent actionEvent) throws IOException {

        Parent load = FXMLLoader.load(getClass().getResource("../View/LoginForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = (Stage) cheffcontext.getScene().getWindow();
        stage.setScene(scene);
        stage.show();



    }
}
