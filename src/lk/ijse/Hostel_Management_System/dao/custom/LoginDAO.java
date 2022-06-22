package lk.ijse.Hostel_Management_System.dao.custom;

import lk.ijse.Hostel_Management_System.dao.SuperDAO;
import lk.ijse.Hostel_Management_System.entity.User;

import java.util.List;

public interface LoginDAO extends SuperDAO {
    List<User> getAllUsers();
}
