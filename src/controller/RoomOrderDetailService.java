package controller;

import model.RoomOrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomOrderDetailService {

public ArrayList<RoomOrderDetails>getAllRoomOrderDetail() throws SQLException,ClassNotFoundException;

}
