package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import model.Customer;
import model.Food;
import model.FoodItemDetail;
import model.Order;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import tm.CartTm;
import tm.OrderDetailTm;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FoodPlaceOrderFormController {
    public Label lblTime;
    public Label lblOrderId;
    public Label lblDate;
    public ComboBox cmbCustomerIds;
    public ComboBox cmbFoodItemIds;
    public TableView tblCart;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colQTY;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtSalary;
    public JFXTextField txtFoodDescription;
    public JFXTextField txtFoodqtyOnHand;
    public JFXTextField txtFoodUnitPrice;
    public JFXTextField txtFoodQTY;
    public Label txtTtl;
    public JFXButton btnreportparameter;

    int cartSelectedRowForRemove = -1;

    public void initialize(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadDateAndTime();
        setOrderId();

        try{
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
        cmbFoodItemIds.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try{
                setFoodItemData((String) newValue);
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
            lblOrderId.setText(new FoodOrderController().getOrderId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void setFoodItemData(String foodCode) throws SQLException,ClassNotFoundException{
        Food f1 = new FoodController().getFood(foodCode);
        if(f1 == null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set");

        }else{
            txtFoodDescription.setText(f1.getDescription());
            txtFoodqtyOnHand.setText(String.valueOf(f1.getQtyOnHand()));
            txtFoodUnitPrice.setText(String.valueOf(f1.getUnitPrice()));
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
        List<String> itemIds = new FoodController().getAllFoodIds();
        cmbFoodItemIds.getItems().addAll(itemIds);
    }
    private void loadCustomerIds() throws SQLException, ClassNotFoundException {

        List<String> customerIds = new CustomerController()
                .getCustomerIds();
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

    public void PlaceOrderOnAction(ActionEvent actionEvent) throws SQLException {

        ArrayList<FoodItemDetail> foods = new ArrayList<>();
        double total=0;
        for (CartTm tempTm:obList
        ) {
            total+=tempTm.getTotal();
            foods.add(new FoodItemDetail(tempTm.getCode(),tempTm.getUnitPrice(),
                    tempTm.getQty()));
        }

        Order order= new Order(lblOrderId.getText(), (String) cmbCustomerIds.getValue(),
                lblDate.getText(),
                lblTime.getText(),
                total,
                foods
        );

        if (new FoodOrderController().placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderId();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

    }

    public void ClearOnAction(ActionEvent actionEvent) {

        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRowForRemove);
            calculateCost();
            tblCart.refresh();
        }



    }
  ObservableList<CartTm> obList = FXCollections.observableArrayList();
    public void addtoCartOnAction(ActionEvent actionEvent) {
    String description = txtFoodDescription.getText();
    int qtyOnHand = Integer.parseInt(txtFoodqtyOnHand.getText());
    double unitPrice = Double.parseDouble(txtFoodUnitPrice.getText());
    int qtyForCustomer = Integer.parseInt(txtFoodQTY.getText());
    double total = qtyForCustomer * unitPrice;

    if (qtyOnHand<qtyForCustomer){
        new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
        return;
    }
        CartTm tm = new CartTm(
                (String) cmbFoodItemIds.getValue(),
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
            CartTm temp = obList.get(rowNumber);
            CartTm newTm = new CartTm(
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
        for (CartTm tm:obList
        ) {
            ttl+=tm.getTotal();
        }
        txtTtl.setText(ttl+" /=");
    }

    private int isExists(CartTm tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getCode().equals(obList.get(i).getCode())){
                return i;
            }
        }
        return -1;


    }

    public void reportfoodpara(MouseEvent mouseEvent) {

        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/View/Report/FoodOrderParameter.jrxml"));
            JasperReport compileReport = JasperCompileManager.compileReport(design);

            String descriptionText = txtFoodDescription.getText();
            String  qtyText = txtFoodQTY.getText();
            String orderid = lblOrderId.getText();
            String foodUnitPrice = txtFoodUnitPrice.getText();

            HashMap map = new HashMap();
            map.put("description",descriptionText);
            map.put("qty",qtyText);
            map.put("OrderId",orderid);
            map.put("price",foodUnitPrice);

            JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, map, new JREmptyDataSource());
            JasperViewer.viewReport(jasperPrint,false);



        } catch (JRException e) {
            e.printStackTrace();
        }


    }
}
