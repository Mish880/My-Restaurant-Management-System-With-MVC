package model;

public class RoomOrderDetails {

           private String ROOMSitemCode;
           private String orderId;
           private int qty;
           private double price;

    public RoomOrderDetails() {
    }

    public RoomOrderDetails(String ROOMSitemCode, String orderId, int qty, double price) {
        this.setROOMSitemCode(ROOMSitemCode);
        this.setOrderId(orderId);
        this.setQty(qty);
        this.setPrice(price);
    }

    public String getROOMSitemCode() {
        return ROOMSitemCode;
    }

    public void setROOMSitemCode(String ROOMSitemCode) {
        this.ROOMSitemCode = ROOMSitemCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
