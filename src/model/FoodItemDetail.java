package model;

public class FoodItemDetail {

    private String FooditemCode;
    private double unitPrice;
    private int qtyForSell;

    public FoodItemDetail() {
    }

    public FoodItemDetail(String fooditemCode, double unitPrice, int qtyForSell) {
        setFooditemCode(fooditemCode);
        this.setUnitPrice(unitPrice);
        this.setQtyForSell(qtyForSell);
    }

    public String getFooditemCode() {
        return FooditemCode;
    }

    public void setFooditemCode(String fooditemCode) {
        FooditemCode = fooditemCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQtyForSell() {
        return qtyForSell;
    }

    public void setQtyForSell(int qtyForSell) {
        this.qtyForSell = qtyForSell;
    }
    @Override
    public String toString() {
        return "FoodItemDetail{" +
                "itemCode='" + FooditemCode + '\'' +
                ", unitPrice=" + unitPrice +
                ", qtyForSell=" + qtyForSell +
                '}';
    }




}
