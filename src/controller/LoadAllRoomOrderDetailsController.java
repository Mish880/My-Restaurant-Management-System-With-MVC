package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.RoomOrderDetails;
import tm.RoomOrderDetailsTm;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoadAllRoomOrderDetailsController {


    public TableView tblRoomOrderDetails;
    public TableColumn colRoomItemCode;
    public TableColumn colOrderId;
    public TableColumn colQty;
    public TableColumn colPrice;


    public void initialize(){

        try{
            colRoomItemCode.setCellValueFactory(new PropertyValueFactory<>("ROOMSitemCode"));
            colOrderId.setCellValueFactory(new PropertyValueFactory<>("OrderId"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

            loadAllRoomOrderDetailTbl(new RoomOrderDetailSQController().getAllRoomOrderDetail());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void loadAllRoomOrderDetailTbl(ArrayList<RoomOrderDetails>roomOrderDetails){

        ObservableList<RoomOrderDetailsTm> obList = FXCollections.observableArrayList();
        roomOrderDetails.forEach(e->{
            obList.add(new RoomOrderDetailsTm(e.getROOMSitemCode(),e.getOrderId(),e.getQty(),e.getPrice()));
        });

       tblRoomOrderDetails.setItems(obList);
    }


}
