package controller;

import db.DbConnection;
import model.OrderO;
import model.RoomTypeDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomOrderController {

   public String getOrderId() throws SQLException,ClassNotFoundException {
       ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT OrderId FROM  Rorder ORDER BY OrderId LIMIT 1").executeQuery();
       if(rst.next()){
           int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
           tempId = tempId +1;
           if (tempId <=9){
               return "0-00" +tempId;
           }else if (tempId < 99) {
               return "0-0" +tempId;
           }else{
               return "0-" +tempId;
           }
       }else{
           return "0-001";
       }
   }
   public boolean placeOrder(OrderO order) throws SQLException{
       Connection connection = null;
       try{
           connection = DbConnection.getInstance().getConnection();
           connection.setAutoCommit(false);
           PreparedStatement stm = connection.prepareStatement("INSERT INTO  Rorder VALUES (?,?,?,?,?)");

           stm.setObject(1,order.getOrderId());
           stm.setObject(2,order.getCustomerId());
           stm.setObject(3,order.getOrderDate());
           stm.setObject(4,order.getOrderDate());
           stm.setObject(5,order.getOrderTime());
           stm.setObject(5,order.getCost());

           if(stm.executeUpdate() >0) {
               if (saveOrderDetail(order.getOrderId(),order.getRooms())) {
                   connection.commit();
                   return true;
               }else{
                   connection.rollback();
                   return false;
               }
           }else{
               connection.rollback();
               return false;
           }
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }finally {
           try{
               connection.setAutoCommit(true);
           } catch (SQLException throwables) {
               throwables.printStackTrace();
           }
       }
      return false;
   }

    private boolean saveOrderDetail(String OrderId, ArrayList<RoomTypeDetail> rooms) throws SQLException, ClassNotFoundException {

        for (RoomTypeDetail temp : rooms) {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO  OrderDetailO VALUES (?,?,?,?)");
            stm.setObject(1,temp.getRoomtypeCode());
            stm.setObject(2,OrderId);
            stm.setObject(3,temp.getQtyForSell());
            stm.setObject(4,temp.getUnitPrice());
            if(stm.executeUpdate() >0){

                if (updateqty(temp.getRoomtypeCode(),temp.getQtyForSell())) {

                }else{
                    return false;
                }

                }else{
                return false;
            }
            }

        return true;
   }

    private boolean updateqty(String roomtypeCode, int qtyForSell) throws SQLException, ClassNotFoundException {


        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE rooms SET qtyOnHand=(qtyOnHand-" + qtyForSell
                                + ") WHERE Rcode='" + roomtypeCode + "'");
        return stm.executeUpdate() > 0;

   }

}
