package controller;

import db.DbConnection;
import model.OrderDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodOrderDetailSQController implements OrderDetailService {

   public List<String>getFoodOrderDetails() throws SQLException,ClassNotFoundException{
       ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM  orderdetail").executeQuery();
       List <String> ids = new ArrayList<>();
       while (rst.next()) {
           ids.add(rst.getString(1)
           );
       }
      return ids;
   }


    @Override
    public ArrayList<OrderDetails> getAllOrderDetails() throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM orderdetail");
        ResultSet rst = stm.executeQuery();
        ArrayList<OrderDetails> orderDetails = new ArrayList<>();
        while (rst.next()){
            orderDetails.add(new OrderDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            ));
        }
       return orderDetails;
   }
}
