package lk.ijse.Hostel_Management_System.dao.custom.impl;

import lk.ijse.Hostel_Management_System.dao.custom.StudentDAO;
import lk.ijse.Hostel_Management_System.entity.Student;
import lk.ijse.Hostel_Management_System.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM Student";
        List<Student> studentList = session.createQuery(hql).list();

        transaction.commit();
        session.close();

        return studentList;
    }

    @Override
    public boolean save(Student entity) {
        return false;
    }

    @Override
    public boolean update(Student entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.load(Student.class, id);

        session.delete(student);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean exist(String id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="From Student WHERE studentId= :student_Id";
        Query query = session.createQuery(hql);
        query.setParameter("student_Id",id);
        List<Student> studentList = query.list();

        transaction.commit();
        session.close();

        if (studentList.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public Student search(String s) {
        return null;
    }
}
