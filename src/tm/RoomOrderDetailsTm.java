package tm;

public class RoomOrderDetailsTm {

       private String ROOMSitemCode;
       private String OrderId;
       private int qty;
       private double price;

    public RoomOrderDetailsTm() {
    }

    public RoomOrderDetailsTm(String ROOMSitemCode, String orderId, int qty, double price) {
        this.setROOMSitemCode(ROOMSitemCode);
        setOrderId(orderId);
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
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
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
