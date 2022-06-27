package lk.ijse.Hostel_Management_System.dao.custom.impl;

import lk.ijse.Hostel_Management_System.dao.custom.QueryDAO;
import lk.ijse.Hostel_Management_System.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Object[]> getKeyMoneyAndStudentDetails() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="SELECT s.studentId, s.name, s.address, s.contactNo, res.res_id, res.date,res.room.roomTypeId, res.status FROM Reservation res INNER JOIN Student s ON res.student = s.studentId WHERE res.status!=:paidOrNot";
        Query query = session.createQuery(hql);
        query.setParameter("paidOrNot","paid");

        List<Object[]> list = query.list();

        transaction.commit();
        session.close();

        return list;
    }
}
