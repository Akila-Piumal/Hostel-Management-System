package lk.ijse.Hostel_Management_System.bo.custom.impl;

import lk.ijse.Hostel_Management_System.bo.custom.UserManagementBO;
import lk.ijse.Hostel_Management_System.dao.DAOFactory;
import lk.ijse.Hostel_Management_System.dao.SuperDAO;
import lk.ijse.Hostel_Management_System.dao.custom.UserDAO;
import lk.ijse.Hostel_Management_System.dto.UserDTO;
import lk.ijse.Hostel_Management_System.entity.User;

public class UserManagementBOImpl implements UserManagementBO {

    private final UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public UserDTO getUserFromUserNameAndPassword(String userName, String password) {
        User user = userDAO.getFromUserNameAndPassword(userName, password);
        return new UserDTO(user.getUserId(),user.getUserName(),user.getPassword());
    }

    @Override
    public boolean updateUser(UserDTO dto) {
        return userDAO.update(new User(dto.getUserId(),dto.getUserName(),dto.getPassword()));
    }
}
