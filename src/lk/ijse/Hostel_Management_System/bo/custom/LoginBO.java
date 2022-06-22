package lk.ijse.Hostel_Management_System.bo.custom;

import lk.ijse.Hostel_Management_System.bo.SuperBO;

public interface LoginBO extends SuperBO {
    boolean loginToSystem(String userName, String password);
}
