package lk.ijse.Hostel_Management_System.util;
import lk.ijse.Hostel_Management_System.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private static SessionFactory sessionFactory;

    private FactoryConfiguration() {
        try {
            Configuration configuration = new Configuration();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            configuration.addAnnotatedClass(Student.class);
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("There is issue in hibernate util");
        }

    }

    public static FactoryConfiguration getInstance() {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

}
