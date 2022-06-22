package lk.ijse.Hostel_Management_System.dao;

import lk.ijse.Hostel_Management_System.dao.custom.impl.LoginDAOImpl;
import lk.ijse.Hostel_Management_System.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        LOGIN,STUDENT
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case LOGIN:
                return new LoginDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            default:
                return null;
        }
    }
}
