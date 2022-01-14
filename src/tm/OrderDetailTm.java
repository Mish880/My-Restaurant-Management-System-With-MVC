package tm;

public class OrderDetailTm {

     private String FoodsItemCode;
     private String Orderid;
     private int qty;
     private double price;

    public OrderDetailTm() {
    }

    public OrderDetailTm(String foodsItemCode, String orderid, int qty, double price) {
        setFoodsItemCode(foodsItemCode);
        setOrderid(orderid);
        this.setQty(qty);
        this.setPrice(price);
    }

    public String getFoodsItemCode() {
        return FoodsItemCode;
    }

    public void setFoodsItemCode(String foodsItemCode) {
        FoodsItemCode = foodsItemCode;
    }

    public String getOrderid() {
        return Orderid;
    }

    public void setOrderid(String orderid) {
        Orderid = orderid;
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
