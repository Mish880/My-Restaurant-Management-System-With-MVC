package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderDetails;
import tm.OrderDetailTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoadAllFoodOrderDetailsController {
    public TableView tblFoodOrderDetails;
    public TableColumn colFoodItemCode;
    public TableColumn colOrderId;
    public TableColumn colQTY;
    public TableColumn colPrice;

    public void initialize(){
        try{
            colFoodItemCode.setCellValueFactory(new PropertyValueFactory<>("FoodsItemCode"));
            colOrderId.setCellValueFactory(new PropertyValueFactory<>("Orderid"));
            colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

            LoadAllFoodOrderDetailsTbl(new FoodOrderDetailSQController().getAllOrderDetails());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void LoadAllFoodOrderDetailsTbl(ArrayList<OrderDetails>orderDetails){

        ObservableList<OrderDetailTm> obList = FXCollections.observableArrayList();
        orderDetails.forEach(e->{
            obList.add(new OrderDetailTm(e.getFOODSitemCode(),e.getOrderId(),e.getQty(),e.getPrice()));
        });
      tblFoodOrderDetails.setItems(obList);
    }
}
