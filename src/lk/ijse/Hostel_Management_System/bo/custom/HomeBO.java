package lk.ijse.Hostel_Management_System.bo.custom;

import lk.ijse.Hostel_Management_System.bo.SuperBO;
import lk.ijse.Hostel_Management_System.dto.ReservationDTO;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.dto.StudentDTO;
import lk.ijse.Hostel_Management_System.entity.Reservation;
import lk.ijse.Hostel_Management_System.entity.Student;

import java.util.List;

public interface HomeBO extends SuperBO {
    List<StudentDTO> getAllStudents();

    List<RoomDTO> getAllRooms();

    List<ReservationDTO> getAllReservations();
}
