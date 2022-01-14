package controller;

import db.DbConnection;
import model.Login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUserController {

     public String login(Login login) throws SQLException, ClassNotFoundException {
         PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM  Login WHERE id=? AND password= ?");
         stm.setObject(1,login.getId());
         stm.setObject(2,login.getPassword());
         ResultSet rst = stm.executeQuery();
         if (rst.next()){
             String role = rst.getString(3);
             return role;
         }else{
             return"";
         }
     }

}
