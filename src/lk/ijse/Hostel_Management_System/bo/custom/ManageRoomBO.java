package lk.ijse.Hostel_Management_System.bo.custom;

import lk.ijse.Hostel_Management_System.bo.SuperBO;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;

import java.util.List;

public interface ManageRoomBO extends SuperBO {
    List<RoomDTO> getAllRooms();
}
