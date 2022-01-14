package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Food;
import model.Room;
import tm.FoodTM;
import tm.RoomTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class RoomDetailsController {
    public JFXTextField txtId;
    public JFXTextField txtDescription;
    public JFXTextField txtQty;
    public JFXTextField txtUnitPrice;
    public TableView tblRoom;
    public TableColumn colRoomId;
    public TableColumn colRoomtype;
    public TableColumn colUnitPrice;
    public TableColumn colqty;
    public JFXButton btnupdateroom;
    public JFXButton btnremoveroom;
    public JFXButton btnaddroom;

    ObservableList<RoomTM> ObList = FXCollections.observableArrayList();

    public void initialize(){

        try{
            colRoomId.setCellValueFactory(new PropertyValueFactory<>("Id"));
            colRoomtype.setCellValueFactory(new PropertyValueFactory<>("description"));
            colqty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

            setRoomDetailtoTable(new RoomController().getAllRooms());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void SaveRoomOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        RoomTM roomTM = new RoomTM(txtId.getText(),txtDescription.getText(),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtUnitPrice.getText()));
        ObList.add(roomTM);

        Room R1 = new Room (
                txtId.getText(),txtDescription.getText(),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtUnitPrice.getText())
        );
        if(new RoomController().saveRoom(R1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }


    }

    public void UpdateRoomOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Room R1 = new Room(
                txtId.getText(),txtDescription.getText(),Integer.parseInt(txtQty.getText()),Double.parseDouble(txtUnitPrice.getText())
        );
        if(new RoomController().updateRoom(R1)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        }else {
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        }


    }

    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (new RoomController().deleteRoom(txtId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }


    }
    private void setRoomDetailtoTable(ArrayList<Room> rooms){
        ObservableList<FoodTM> obList = FXCollections.observableArrayList();
        rooms.forEach(e->{
            obList.add(new FoodTM(e.getCode(),e.getDescription(),e.getQtyOnHand(),e.getUnitPrice()));
        });
        tblRoom.setItems(obList);
    }


    public void RoomId_keyPress(KeyEvent keyEvent) {
        btnaddroom.setDisable(true);
        btnupdateroom.setDisable(true);
        btnremoveroom.setDisable(true);

        String regEx="^[R]{1}[0-9]{0,50}$";
        String typeText = txtId.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (matches){
            txtId.setStyle("-fx-border-color: green");

        }else {
            txtId.setStyle("-fx-border-color: red");


        }
        if(keyEvent.getCode() == KeyCode.ENTER){
            if (matches){
                txtDescription.requestFocus();
            }
        }


    }

    public void Room_Name(KeyEvent keyEvent) {
        String regEx = "^[A-z]{3,20}$";
        String typeText = txtDescription.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if(matches){
            txtDescription.setStyle("-fx-border-color: green");


        }else {
            txtDescription.setStyle("-fx-border-color: red");

        }
        if (keyEvent.getCode() ==KeyCode.ENTER){
            if (matches){
                txtQty.requestFocus();
            }
        }

    }

    public void Room_QtyPress(KeyEvent keyEvent) {
        String regEx = "^[0-9]{1,4}?$";
        String typeText = txtQty.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (matches){
            txtQty.setStyle("-fx-border-color: green");

        }else{
            txtQty.setStyle("-fx-border-color: red");

        }
        if (keyEvent.getCode() ==KeyCode.ENTER){
            if (matches){
                txtUnitPrice.requestFocus();
            }
        }

    }

    public void Room_SalaryPress(KeyEvent keyEvent) {
        String regEx = "^[1-9][0-9]*[0-9]{2}?$";
        String typeText = txtUnitPrice.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (matches){
            txtUnitPrice.setStyle("-fx-border-color: green");
            btnaddroom.setDisable(false);
            btnupdateroom.setDisable(false);
            btnremoveroom.setDisable(false);
        }else{
            txtUnitPrice.setStyle("-fx-border-color: red");

        }

    }
}
