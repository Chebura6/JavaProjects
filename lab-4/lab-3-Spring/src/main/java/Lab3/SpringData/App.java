package Lab3.SpringData;//package is.technologies.Lab3.SpringData;
//
//import Hibernate.Entities.HibernateCat;
//import Hibernate.Entities.HibernateCatOwner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class App {
    public static void main(String[] args) throws Exception {


        SpringApplication.run(App.class, args);
    }
}
