package lk.ijse.Hostel_Management_System.dao;

import lk.ijse.Hostel_Management_System.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        LOGIN,STUDENT,ROOM, RESERVATION, USER, QUERY
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case LOGIN:
                return new LoginDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case USER:
                return new UserDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
