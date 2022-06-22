package lk.ijse.Hostel_Management_System.bo.custom;

import lk.ijse.Hostel_Management_System.bo.SuperBO;
import lk.ijse.Hostel_Management_System.entity.User;

import java.util.List;

public interface LoginBO extends SuperBO {
    List<User> getAllUsers();
}
