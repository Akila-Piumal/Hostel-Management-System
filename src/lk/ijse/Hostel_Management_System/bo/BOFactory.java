package lk.ijse.Hostel_Management_System.bo;

import lk.ijse.Hostel_Management_System.bo.custom.impl.LoginBOImpl;
import lk.ijse.Hostel_Management_System.bo.custom.impl.MakeReservationBOImpl;
import lk.ijse.Hostel_Management_System.bo.custom.impl.ManageRoomBOImpl;
import lk.ijse.Hostel_Management_System.bo.custom.impl.ManageStudentsBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case LOGIN:
                return new LoginBOImpl();
            case MANAGESTUDENTS:
                return new ManageStudentsBOImpl();
            case MANAGEROOMS:
                return new ManageRoomBOImpl();
            case MAKERESERVATION:
                return new MakeReservationBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes {
        LOGIN, MANAGESTUDENTS, MANAGEROOMS, MAKERESERVATION
    }
}
