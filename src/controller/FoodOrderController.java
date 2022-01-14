package controller;

import db.DbConnection;
import model.FoodItemDetail;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodOrderController {

    public String getOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT orderId FROM `order` ORDER BY orderId DESC LIMIT 1").executeQuery();
        if (rst.next()) {
            int tempId = Integer.parseInt(rst.getString(1).split("-")[1]);
            tempId = tempId + 1;
            if (tempId <=9){
                return "0-00" + tempId;
            } else if (tempId < 99) {
                return "0-0" + tempId;
            } else {
                return "0-" + tempId;
            }
        } else {
            return "0-001";
        }
    }

    public boolean placeOrder(Order order) throws SQLException {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PreparedStatement stm = connection.prepareStatement("INSERT INTO `order` VALUES(?,?,?,?,?)");

            stm.setObject(1, order.getOrderId());
            stm.setObject(2, order.getCustomerId());
            stm.setObject(3, order.getOrderDate());
            stm.setObject(4, order.getOrderTime());
            stm.setObject(5, order.getCost());

            if (stm.executeUpdate() > 0) {
                if (saveOrderDetail(order.getOrderId(), order.getFoods())) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            } else {
                connection.rollback();
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;

    }

    private boolean saveOrderDetail(String orderId, ArrayList<FoodItemDetail> foods) throws SQLException, ClassNotFoundException {
        for (FoodItemDetail temp : foods
        ) {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO orderdetail VALUES(?,?,?,?)");
            stm.setObject(1, temp.getFooditemCode());
            stm.setObject(2, orderId);
            stm.setObject(3, temp.getQtyForSell());
            stm.setObject(4, temp.getUnitPrice());
            if (stm.executeUpdate() > 0) {

                if (updateQty(temp.getFooditemCode(), temp.getQtyForSell())) {

                } else {
                    return false;
                }
            } else {
                return false;
            }
        }


        return true;
    }

    private boolean updateQty(String fooditemCode, int qtyForSell) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection()
                .prepareStatement
                        ("UPDATE foods SET qtyOnHand=(qtyOnHand-" + qtyForSell
                                + ") WHERE Fcode='" + fooditemCode + "'");
        return stm.executeUpdate() > 0;
    }

}

