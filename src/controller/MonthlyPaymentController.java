package controller;

import db.DbConnection;
import javafx.scene.control.Label;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MonthlyPaymentController {
    public Label lblFoodIncome;
    public Label lblFood;
    public double monthlyIncome;
    public Label lblRoomIncome;
    public Label lblRoom;
    public double roommonthlyIncome;

    public  void initialize(){

      try {
          monthlyIncome();
          RoommonthlyIncome();

      } catch (SQLException throwables) {
          throwables.printStackTrace();
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      }

  }

    private void RoommonthlyIncome() throws SQLException, ClassNotFoundException {

         String rmonth;
         Date date = new Date();
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
         rmonth = simpleDateFormat.format(date);
         roommonthlyIncome = 0;

         PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT  * FROM   rorder WHERE orderDate LIKE'" + rmonth + "%'");
         ResultSet resultSet = preparedStatement.executeQuery();
         while (resultSet.next()){
             roommonthlyIncome += Double.parseDouble(resultSet.getString("cost"));
         }
         lblRoom.setText(""+roommonthlyIncome);
    }

    private void monthlyIncome() throws SQLException, ClassNotFoundException {

        String month;
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        month = simpleDateFormat.format(date);
        monthlyIncome = 0;

        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM  `order` WHERE orderDate LIKE '" + month + "%'");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            monthlyIncome += Double.parseDouble(resultSet.getString("cost"));
        }
       lblFood.setText(""+monthlyIncome);

  }



}
