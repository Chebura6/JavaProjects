package is.technologies.Lab2.Hibernate.Models;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryManager {

    private static SessionFactory sessionFactory;

    private SessionFactoryManager() {}

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure("Lab2/hibernate.cfg.xml");
                configuration.addAnnotatedClass(HibernateCat.class);
                configuration.addAnnotatedClass(HibernateCatOwner.class);

                StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(standardRegistry);
            } catch (Throwable e) {
                System.err.println("Failed to create sessionFactory object." + e);
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }
}