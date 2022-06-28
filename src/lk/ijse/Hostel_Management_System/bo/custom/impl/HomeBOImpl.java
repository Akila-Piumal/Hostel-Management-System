package lk.ijse.Hostel_Management_System.bo.custom.impl;

import lk.ijse.Hostel_Management_System.bo.custom.HomeBO;
import lk.ijse.Hostel_Management_System.dao.DAOFactory;
import lk.ijse.Hostel_Management_System.dao.SuperDAO;
import lk.ijse.Hostel_Management_System.dao.custom.ReservationDAO;
import lk.ijse.Hostel_Management_System.dao.custom.RoomDAO;
import lk.ijse.Hostel_Management_System.dao.custom.StudentDAO;
import lk.ijse.Hostel_Management_System.dto.ReservationDTO;
import lk.ijse.Hostel_Management_System.dto.RoomDTO;
import lk.ijse.Hostel_Management_System.dto.StudentDTO;
import lk.ijse.Hostel_Management_System.entity.Reservation;
import lk.ijse.Hostel_Management_System.entity.Room;
import lk.ijse.Hostel_Management_System.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class HomeBOImpl implements HomeBO {

    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);
    private final ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> all = studentDAO.getAll();

        List<StudentDTO> studentDTOList=new ArrayList<>();
        for (Student student : all) {
            studentDTOList.add(new StudentDTO(student.getStudentId(), student.getName(), student.getAddress(), student.getContactNo(), student.getDob(), student.getGender()));
        }
        return studentDTOList;
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        List<Room> all = roomDAO.getAll();
        List<RoomDTO> allRooms=new ArrayList<>();
        for (Room room : all) {
            allRooms.add(new RoomDTO(room.getRoomTypeId(),room.getType(),room.getKeyMoney(),room.getQty()));
        }
        return allRooms;
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        List<Reservation> all = reservationDAO.getAll();
        List<ReservationDTO> allReservations=new ArrayList<>();
        for (Reservation reservation : all) {
            allReservations.add(new ReservationDTO(reservation.getRes_id(),reservation.getDate(),reservation.getStatus(),reservation.getStudent(),reservation.getRoom()));
        }
        return allReservations;
    }
}
