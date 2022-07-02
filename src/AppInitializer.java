import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.Hostel_Management_System.bo.BOFactory;
import lk.ijse.Hostel_Management_System.bo.custom.LoginBO;
import lk.ijse.Hostel_Management_System.entity.Student;
import lk.ijse.Hostel_Management_System.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;


public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("lk/ijse/Hostel_Management_System/view/LoginForm.fxml"))));
        primaryStage.show();

        // Checking the Second Level Cache
        /*Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.get(Student.class, "S001");
        System.out.println(student.getName());

        transaction.commit();
        session.close();

        Session session2 = FactoryConfiguration.getInstance().getSession();
        Transaction transaction1 = session2.beginTransaction();

        Student student1 = session2.get(Student.class, "S001");
        System.out.println(student1.getName());

        transaction1.commit();
        session2.close();*/
    }
}
