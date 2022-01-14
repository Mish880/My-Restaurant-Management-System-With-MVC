package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.*;
import tm.CartOTm;
import tm.CartTm;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomPlaceOrderFormController {
    public ComboBox cmbCustomerIds;
    public ComboBox cmbRoomtypeIds;
    public Label lblOrderId;
    public Label lblDate;
    public Label lblTime;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public JFXTextField txtRoomDescription;
    public JFXTextField txtRoomqtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtRoomQTY;
    public TableView tblCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colQTY;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public Label txtTtl;

    int cartSelectedRowForRemove = -1;
    public void initialize() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadDateAndTime();
        setOrderId();

        try {
            loadCustomerIds();
            loadItemIds();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbCustomerIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try{
                setCustomerData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        cmbRoomtypeIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try{
                setRoomTypeData((String) newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        tblCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });
    }
    private void setOrderId(){
        try{
            lblOrderId.setText(new RoomOrderController().getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void setRoomTypeData(String roomCode)throws SQLException,ClassNotFoundException{
        Room r1 = new RoomController().getRoom(roomCode);
        if (r1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set");
        }else{
            txtRoomDescription.setText(r1.getDescription());
            txtRoomqtyOnHand.setText(String.valueOf(r1.getQtyOnHand()));
            txtUnitPrice.setText(String.valueOf(r1.getUnitPrice()));
        }
    }
    private void setCustomerData(String customerId) throws SQLException, ClassNotFoundException {
        Customer c1 = new CustomerController().getCustomer(customerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtName.setText(c1.getName());
            txtSalary.setText(String.valueOf(c1.getSalary()));
            txtAddress.setText(c1.getAddress());
        }
    }
    private void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new RoomController().getAllRoomIds();
        cmbRoomtypeIds.getItems().addAll(itemIds);
    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController().getCustomerIds();
        cmbCustomerIds.getItems().addAll(customerIds);

    }
    private void loadDateAndTime() {
        // load Date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }


    public void ClearOnAction(ActionEvent actionEvent) {
    }

    ObservableList<CartOTm> obList = FXCollections.observableArrayList();
    public void AddtoCartOnAction(ActionEvent actionEvent) {
      String description = txtRoomDescription.getText();
      int qtyOnHand = Integer.parseInt(txtRoomqtyOnHand.getText());
      double unitPrice = Double.parseDouble(txtUnitPrice.getText());
      int qtyForCustomer = Integer.parseInt(txtRoomQTY.getText());
      double total = qtyForCustomer * unitPrice;

        if (qtyOnHand<qtyForCustomer){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
            return;
        }
        CartOTm tm = new CartOTm(
                (String) cmbRoomtypeIds.getValue(),
                description,
                qtyForCustomer,
                unitPrice,
                total
        );
        int rowNumber=isExists(tm);

        if (rowNumber==-1){// new Add
            obList.add(tm);
        }else{
            // update
            CartOTm temp = obList.get(rowNumber);
            CartOTm newTm = new CartOTm(
                    temp.getCode(),
                    temp.getDescription(),
                    temp.getQty()+qtyForCustomer,
                    unitPrice,
                    total+temp.getTotal()
            );
            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblCart.setItems(obList);
        calculateCost();

    }
    void calculateCost() {
        double ttl=0;
        for (CartOTm tm:obList
        ) {
            ttl+=tm.getTotal();
        }
        txtTtl.setText(ttl+" /=");



    }

    private int isExists(CartOTm tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getCode().equals(obList.get(i).getCode())){
                return i;
            }
        }
        return -1;

    }

    public void PlaceOrderOnAction(ActionEvent actionEvent) throws SQLException {

        ArrayList<RoomTypeDetail> rooms = new ArrayList<>();
        double total=0;
        for (CartOTm tempTm:obList
        ) {
            total+=tempTm.getTotal();
            rooms.add(new RoomTypeDetail(tempTm.getCode(),tempTm.getUnitPrice(),
                    tempTm.getQty()));
        }

        OrderO order= new OrderO(lblOrderId.getText(), (String) cmbCustomerIds.getValue(),
                lblDate.getText(),
                lblTime.getText(),
                total,
                rooms
        );

        if (new RoomOrderController().placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderId();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }


    }
}
