package controller;


import model.Food;


import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodService {

    public boolean saveFood(Food f)throws SQLException,ClassNotFoundException;
    public boolean updateFood(Food f)throws SQLException,ClassNotFoundException;
    public boolean deleteFood(String id)throws  SQLException,ClassNotFoundException;
    public ArrayList<Food> getAllFoods() throws SQLException, ClassNotFoundException;


}
