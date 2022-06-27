package lk.ijse.Hostel_Management_System.dao.custom;

import lk.ijse.Hostel_Management_System.dao.CrudDAO;
import lk.ijse.Hostel_Management_System.entity.User;

public interface UserDAO extends CrudDAO<User,String> {
    User getFromUserNameAndPassword(String userName,String password);
}
