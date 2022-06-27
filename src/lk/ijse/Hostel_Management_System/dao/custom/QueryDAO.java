package lk.ijse.Hostel_Management_System.dao.custom;

import lk.ijse.Hostel_Management_System.dao.SuperDAO;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Object[]> getKeyMoneyAndStudentDetails();
}
