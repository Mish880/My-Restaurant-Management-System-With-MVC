package model;

public class OrderDetails {

         private String FOODSitemCode;
         private String orderId;
         private int qty;
         private double price;

    public OrderDetails() {
    }

    public OrderDetails(String FOODSitemCode, String orderId, int qty, double price) {
        this.setFOODSitemCode(FOODSitemCode);
        this.setOrderId(orderId);
        this.setQty(qty);
        this.setPrice(price);
    }

    public String getFOODSitemCode() {
        return FOODSitemCode;
    }

    public void setFOODSitemCode(String FOODSitemCode) {
        this.FOODSitemCode = FOODSitemCode;
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
