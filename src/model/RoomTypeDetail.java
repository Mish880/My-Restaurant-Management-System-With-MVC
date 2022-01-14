package model;

public class RoomTypeDetail {

     private String RoomtypeCode;
     private double UnitPrice;
     private int qtyForSell;

    public RoomTypeDetail() {
    }

    public RoomTypeDetail(String roomtypeCode, double unitPrice, int qtyForSell) {
        setRoomtypeCode(roomtypeCode);
        setUnitPrice(unitPrice);
        this.setQtyForSell(qtyForSell);
    }

    public String getRoomtypeCode() {
        return RoomtypeCode;
    }

    public void setRoomtypeCode(String roomtypeCode) {
        RoomtypeCode = roomtypeCode;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public int getQtyForSell() {
        return qtyForSell;
    }

    public void setQtyForSell(int qtyForSell) {
        this.qtyForSell = qtyForSell;
    }
    @Override
    public String toString() {
        return "RoomTypeDetail{" +
                "itemCode='" + RoomtypeCode+ '\'' +
                ", unitPrice=" + UnitPrice +
                ", qtyForSell=" + qtyForSell +
                '}';
    }
}
