import lk.ijse.Hostel_Management_System.entity.Reservation;
import lk.ijse.Hostel_Management_System.entity.Room;
import lk.ijse.Hostel_Management_System.entity.Student;
import lk.ijse.Hostel_Management_System.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.List;

public class AppInitializer {
    public static void main(String[] args) {

        Student s1 = new Student();
        s1.setStudentId("S001");
        s1.setName("Akila");
        s1.setAddress("Mathugama");
        s1.setContactNo("783223485");
        s1.setGender("Male");
        s1.setDob(LocalDate.parse("1999-04-13"));

        Student s2 = new Student();
        s2.setStudentId("S002");
        s2.setName("Kamal");
        s2.setAddress("Kaluthara");
        s2.setContactNo("773923485");
        s2.setGender("Male");
        s2.setDob(LocalDate.parse("2000-04-13"));

        Room r1=new Room();
        r1.setRoomTypeId("RM-1324");
        r1.setType("Non-AC");
        r1.setKeyMoney("3100.00");
        r1.setQty(35);

        Room r2=new Room();
        r2.setRoomTypeId("RM-5467");
        r2.setType("Non-AC / Food");
        r2.setKeyMoney("6500.00");
        r2.setQty(20);

        Reservation reservation=new Reservation();
        reservation.setRes_id("R001");
        reservation.setStatus("Paid");
        reservation.setStudent(s1);
        reservation.setRoom(r1);

        Reservation reservation2=new Reservation();
        reservation2.setRes_id("R002");
        reservation2.setStatus("Paid");
        reservation2.setStudent(s1);
        reservation2.setRoom(r2);

        r1.getReservationList().add(reservation);
        r2.getReservationList().add(reservation2);

        s1.getReservationList().add(reservation);
        s1.getReservationList().add(reservation2);


        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

//        session.save(student);
        session.save(s1);
        session.save(s2);
        session.save(r1);
        session.save(r2);
        session.save(reservation);
        session.save(reservation2);

        transaction.commit();
        session.close();

    }
}
