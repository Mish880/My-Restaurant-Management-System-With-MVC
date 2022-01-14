package controller;

import db.DbConnection;

import model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomController implements RoomService {

    public List<String> getAllRoomIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM rooms").executeQuery();
        List<String> ids= new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }
    public Room getRoom(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM rooms WHERE Rcode='" + id + "'").
                executeQuery();
        if (rst.next()){
            return new Room(
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
    public boolean saveRoom(Room r) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO rooms VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);

        stm.setObject(1,r.getCode());
        stm.setObject(2,r.getDescription());
        stm.setObject(3,r.getQtyOnHand());
        stm.setObject(4,r.getUnitPrice());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean updateRoom(Room r) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE rooms SET Rcode =?, ROOMTYPE=?, qtyOnHand=?,unitPrice=? WHERE Rcode=?");
        stm.setObject(1,r.getCode());
        stm.setObject(2,r.getDescription());
        stm.setObject(3,r.getQtyOnHand());
        stm.setObject(4,r.getUnitPrice());
        stm.setObject(5,r.getCode());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM rooms WHERE Rcode='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ArrayList<Room> getAllRooms() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM rooms");
        ResultSet rst = stm.executeQuery();
        ArrayList<Room> rooms = new ArrayList<>();
        while (rst.next()) {
            rooms.add(new Room(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getDouble(4)

            ));
        }
        return rooms;
    }
}
