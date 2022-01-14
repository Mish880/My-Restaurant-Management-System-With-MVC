package model;

import java.util.ArrayList;

public class OrderO {

      private String orderId;
      private String customerId;
      private String orderDate;
      private String orderTime;
      private double cost;
      private ArrayList<RoomTypeDetail> rooms;

    public OrderO() { }

    public OrderO(String orderId, String customerId, String orderDate, String orderTime, double cost, ArrayList<RoomTypeDetail> rooms) {
        this.setOrderId(orderId);
        this.setCustomerId(customerId);
        this.setOrderDate(orderDate);
        this.setOrderTime(orderTime);
        this.setCost(cost);
        this.setRooms(rooms);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public ArrayList<RoomTypeDetail> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<RoomTypeDetail> rooms) {
        this.rooms = rooms;
    }
}
