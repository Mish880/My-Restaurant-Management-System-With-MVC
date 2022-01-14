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

public class CustomerUpdateFormController {


    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public JFXButton btnUpdate;

    public void selectCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

         String customerId = txtId.getText();

        Customer c1 = new CustomerController().getCustomer(customerId);
        if(c1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result Set").show();
        }else{
            setData(c1);
        }

    }
    public void updateCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

         Customer c1 = new Customer(
                 txtId.getText(),txtName.getText(),txtAddress.getText(),Double.parseDouble(txtSalary.getText())
         );

       if(new CustomerController().updateCustomer(c1))
           new Alert(Alert.AlertType.CONFIRMATION,"Updated..").show();
       else
           new Alert(Alert.AlertType.CONFIRMATION,"Try Again").show();
    }


    void setData(Customer c){

         txtId.setText(c.getId());
         txtName.setText(c.getName());
         txtAddress.setText(c.getAddress());
         txtSalary.setText(String.valueOf(c.getSalary()));
    }

    public void CustID_keyPress(KeyEvent keyEvent) {
        btnUpdate.setDisable(true);

        String regEx="^[C]{1}[0-9]{0,50}$";
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
                txtName.requestFocus();
            }
        }


    }

    public void Cust_Name(KeyEvent keyEvent) {

        String regEx="^[A-z]{3,20}$";
        String typeText = txtName.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (matches){
            txtName.setStyle("-fx-border-color: green");

        }else{
            txtName.setStyle("-fx-border-color: red");

        }
        if (keyEvent.getCode() ==KeyCode.ENTER){
            if (matches){
                txtAddress.requestFocus();
            }
        }

    }

    public void Cust_Address(KeyEvent keyEvent) {

        String regEx="^[A-z]{3,20}$";
        String typeText = txtAddress.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (matches){
            txtAddress.setStyle("-fx-border-color: green");

        }else{
            txtAddress.setStyle("-fx-border-color: red");

        }
        if (keyEvent.getCode() ==KeyCode.ENTER){
            if (matches){
                txtSalary.requestFocus();
            }
        }

    }

    public void Cust_Salary(KeyEvent keyEvent) {

        String regEx = "^[1-9][0-9]*[0-9]{2}?$";
        String typeText = txtSalary.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (matches){
            txtSalary.setStyle("-fx-border-color: green");
            btnUpdate.setDisable(false);
        }else{
            txtSalary.setStyle("-fx-border-color: red");

        }
    }
}
