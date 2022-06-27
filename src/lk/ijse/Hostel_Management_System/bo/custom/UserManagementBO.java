package lk.ijse.Hostel_Management_System.bo.custom;

import lk.ijse.Hostel_Management_System.bo.SuperBO;
import lk.ijse.Hostel_Management_System.dto.UserDTO;

public interface UserManagementBO extends SuperBO {
    UserDTO getUserFromUserNameAndPassword(String userName,String password);

    boolean updateUser(UserDTO dto);
}
