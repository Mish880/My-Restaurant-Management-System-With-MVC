package controller;

import db.DbConnection;
import model.Food;
import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodController  implements FoodService{

    public List<String> getAllFoodIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM foods").executeQuery();
        List<String> ids= new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

    public Food getFood(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM foods WHERE Fcode=?");
        preparedStatement.setObject(1,id);
        ResultSet rst = preparedStatement.executeQuery();
        if (rst.next()){
            return new Food(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            );
        }else{
            return null;
        }
    }
    @Override
    public boolean saveFood(Food f) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO foods VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);

        stm.setObject(1,f.getCode());
        stm.setObject(2,f.getDescription());
        stm.setObject(3,f.getQtyOnHand());
        stm.setObject(4,f.getUnitPrice());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean updateFood(Food f) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE foods SET Fcode =?, FOODTYPE=?, qtyOnHand=?,unitPrice=? WHERE Fcode=?");
        stm.setObject(1,f.getCode());
        stm.setObject(2,f.getDescription());
        stm.setObject(3,f.getQtyOnHand());
        stm.setObject(4,f.getUnitPrice());
        stm.setObject(5,f.getCode());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteFood(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM foods WHERE Fcode='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<Food> getAllFoods() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM foods");
        ResultSet rst = stm.executeQuery();
        ArrayList<Food> foods = new ArrayList<>();
        while (rst.next()) {
            foods.add(new Food(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)

            ));
        }
       return foods;
    }
}
