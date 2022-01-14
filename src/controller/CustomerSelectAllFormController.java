package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import tm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerSelectAllFormController {
    public TableView tblCustomer;
    public TableColumn colCustomerId;
    public TableColumn colCustomerName;
    public TableColumn ColCustomerAddress;
    public TableColumn colCustomerSalary;

    public void initialize(){
        try{
            colCustomerId.setCellValueFactory(new PropertyValueFactory<>("Id"));
            colCustomerName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            ColCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colCustomerSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

           setCustomersToTable(new CustomerController().getAllCustomers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void setCustomersToTable(ArrayList<Customer>customers){
        ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
        customers.forEach(e->{
            obList.add(new CustomerTM(e.getId(),e.getName(),e.getAddress(),e.getSalary()));
        });
        tblCustomer.setItems(obList);
    }


}
