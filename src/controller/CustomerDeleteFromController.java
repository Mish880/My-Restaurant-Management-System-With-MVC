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

public class CustomerDeleteFromController {
    public TextField txtId;
    public JFXButton btnDelete;

    public void SearchCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String customerId = txtId.getText();

        Customer c1= new CustomerController().getCustomer(customerId);
        if (c1==null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }


    }

    void setData(Customer c) {
    txtId.setText(c.getId());
    }

    public void DeleteCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new CustomerController().deleteCustomer(txtId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void CustID_keyPress(KeyEvent keyEvent) {

        String regEx="^[C]{1}[0-9]{0,50}$";
        String typeText = txtId.getText();
        Pattern compile = Pattern.compile(regEx);
        boolean matches = compile.matcher(typeText).matches();

        if (matches){
            txtId.setStyle("-fx-border-color: green");
            btnDelete.setDisable(false);
        }else {
            txtId.setStyle("-fx-border-color: red");
            btnDelete.setDisable(true);
        }
    }
}
