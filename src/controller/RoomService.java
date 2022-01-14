package controller;


import model.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomService {

    public boolean saveRoom(Room r)throws SQLException,ClassNotFoundException;
    public boolean updateRoom(Room r)throws SQLException,ClassNotFoundException;
    public boolean deleteRoom(String id)throws  SQLException,ClassNotFoundException;
    public ArrayList<Room> getAllRooms() throws SQLException, ClassNotFoundException;
}
