package controller;

import model.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailService {

public ArrayList<OrderDetails>getAllOrderDetails() throws SQLException,ClassNotFoundException;

}
