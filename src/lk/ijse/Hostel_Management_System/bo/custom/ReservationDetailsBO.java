package lk.ijse.Hostel_Management_System.bo.custom;

import lk.ijse.Hostel_Management_System.bo.SuperBO;
import lk.ijse.Hostel_Management_System.dto.ReservationDTO;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.dto.StudentDTO;

import java.util.List;

public interface ReservationDetailsBO extends SuperBO {
    List<ReservationDTO> getAllReservations();

    boolean removeReservation(String id);

    boolean updateRoomQty(String roomTypeID, int qty);

    RoomDTO getRoom(String roomTypeID);

    boolean updateReservationStatus(String res_Id ,String status);

    StudentDTO getStudent(String studentID);
}
