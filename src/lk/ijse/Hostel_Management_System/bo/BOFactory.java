package lk.ijse.Hostel_Management_System.bo;

import lk.ijse.Hostel_Management_System.bo.custom.impl.LoginBOImpl;

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
            default:
                return null;
        }
    }

    public enum BOTypes {
        LOGIN
    }
}
