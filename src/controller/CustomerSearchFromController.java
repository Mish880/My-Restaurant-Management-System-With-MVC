package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Customer;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class CustomerSearchFromController {
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerAddress;
    public TextField txtCustomerSalary;
    public JFXButton btnSearch;

    public void SearchCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

    String customerId = txtCustomerId.getText();

        Customer c1 = new CustomerController().getCustomer(customerId);
      if (c1==null){
          new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
      }else{
          setData(c1);
      }
    }

     void setData(Customer c) {
        txtCustomerId.setText(c.getId());
        txtCustomerName.setText(c.getName());
        txtCustomerAddress.setText(c.getAddress());
        txtCustomerSalary.setText(String.valueOf(c.getSalary()));
    }

    public void CustID_keyPress(KeyEvent keyEvent) {
        String regEx="^[C]{1}[0-9]{0,50}$";
        String typeText = txtCustomerId.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (matches){
            txtCustomerId.setStyle("-fx-border-color: green");
            btnSearch.setDisable(false);
        }else {
            txtCustomerId.setStyle("-fx-border-color: red");
            btnSearch.setDisable(true);
        }
    }
}
