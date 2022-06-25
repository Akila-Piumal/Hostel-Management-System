package lk.ijse.Hostel_Management_System.dao.custom.impl;

import lk.ijse.Hostel_Management_System.dao.custom.RoomDAO;
import lk.ijse.Hostel_Management_System.entity.Room;
import lk.ijse.Hostel_Management_System.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public List<Room> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM Room";
        Query query = session.createQuery(hql);
        List<Room> roomList = query.list();

        transaction.commit();
        session.close();

        return roomList;
    }

    @Override
    public boolean save(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Room entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();

        return true;
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
    public Room search(String s) {
        return null;
    }

    @Override
    public boolean updateQty(String roomTypeID, int qty) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="UPDATE Room SET qty=: qty_on_hand WHERE roomTypeId=: id";
        Query query = session.createQuery(hql);
        query.setParameter("qty_on_hand",qty);
        query.setParameter("id",roomTypeID);
        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        if (i>0){
            return true;
        }
        return false;
    }
}
