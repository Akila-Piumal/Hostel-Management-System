package lk.ijse.Hostel_Management_System.dao.custom.impl;

import lk.ijse.Hostel_Management_System.dao.custom.ReservationDAO;
import lk.ijse.Hostel_Management_System.entity.Reservation;
import lk.ijse.Hostel_Management_System.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public List<Reservation> getAll() {
        return null;
    }

    @Override
    public boolean save(Reservation entity) {
        return false;
    }

    @Override
    public boolean update(Reservation entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean exist(String s) {
        return false;
    }

    @Override
    public Reservation search(String s) {
        return null;
    }

    @Override
    public String generateNewID() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="SELECT res_id FROM Reservation ORDER BY res_id DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        List<String> list = query.list();

        transaction.commit();
        session.close();

        return list.isEmpty() ? "R-001" : String.format("R-%03d", (Integer.parseInt(list.get(0).replace("R-", "")) + 1));
    }
}
