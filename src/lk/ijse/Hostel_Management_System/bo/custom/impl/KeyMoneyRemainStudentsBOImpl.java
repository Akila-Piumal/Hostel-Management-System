package lk.ijse.Hostel_Management_System.bo.custom.impl;

import lk.ijse.Hostel_Management_System.bo.custom.KeyMoneyRemainStudentsBO;
import lk.ijse.Hostel_Management_System.dao.DAOFactory;
import lk.ijse.Hostel_Management_System.dao.SuperDAO;
import lk.ijse.Hostel_Management_System.dao.custom.QueryDAO;

import java.util.List;

public class KeyMoneyRemainStudentsBOImpl implements KeyMoneyRemainStudentsBO {

    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public List<Object[]> getKeyMoneyAndStudentDetails() {
        return queryDAO.getKeyMoneyAndStudentDetails();
    }
}
