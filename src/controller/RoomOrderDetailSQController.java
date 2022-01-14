package controller;

import db.DbConnection;
import model.RoomOrderDetails;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomOrderDetailSQController implements RoomOrderDetailService {

    public List<String>getRoomOrderDetails() throws SQLException,ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM  orderdetailo").executeQuery();
        List<String> ids = new ArrayList<>();
        while (rst.next()) {
            ids.add(rst.getString(1)
            );
        }
        return ids;
    }

    @Override
    public ArrayList<RoomOrderDetails> getAllRoomOrderDetail() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT  * FROM orderdetailo");
        ResultSet rst = stm.executeQuery();
        ArrayList<RoomOrderDetails> roomOrderDetails = new ArrayList<>();
        while(rst.next()){
            roomOrderDetails.add(new RoomOrderDetails(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)
            ));
        }
       return roomOrderDetails;
    }
}

