import lk.ijse.Hostel_Management_System.entity.Student;
import lk.ijse.Hostel_Management_System.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class AppInitializer {
    public static void main(String[] args) {

        Student student = new Student();
        student.setStudentId("S001");
        student.setName("Akila");
        student.setAddress("Mathugama");
        student.setContactNo("783223485");
        student.setGender("Male");
        student.setDob(LocalDate.parse("1999-04-13"));

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

//        session.save(student);

        transaction.commit();
        session.close();

    }
}
