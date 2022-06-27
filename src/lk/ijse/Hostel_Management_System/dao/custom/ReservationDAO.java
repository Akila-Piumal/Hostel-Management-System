package lk.ijse.Hostel_Management_System.dao.custom;

import lk.ijse.Hostel_Management_System.dao.CrudDAO;
import lk.ijse.Hostel_Management_System.dao.SuperDAO;
import lk.ijse.Hostel_Management_System.entity.Reservation;

public interface ReservationDAO extends CrudDAO<Reservation,String> {
    String generateNewID();

    boolean updateStatus(String res_id, String status);
}
