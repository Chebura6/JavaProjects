package is.technologies.Lab2;
import is.technologies.Lab2.Hibernate.HibernateService;
import is.technologies.Lab2.Hibernate.Models.HibernateCat;
import is.technologies.Lab2.Hibernate.Models.HibernateCatOwner;
import is.technologies.Lab2.Interfaces.CatOwner;
import is.technologies.Lab2.JDBC.ConnectionManager;
import is.technologies.Lab2.JDBC.JDBCService;
import is.technologies.Lab2.JDBC.Models.JDBCCatOwner;
import is.technologies.Lab2.MyBatis.Models.MyBatisCat;
import is.technologies.Lab2.MyBatis.Models.MyBatisCatOwner;
import is.technologies.Lab2.MyBatis.Models.MyBatisConnectionManager;
import is.technologies.Lab2.MyBatis.MyBatisService;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws Exception{
        ConnectionManager connectionManager = new ConnectionManager();
        connectionManager.createConnection("artemparfenov","TestPassword12345", "jdbc:postgresql://localhost:5432/postgres");
        JDBCService JDBCservice = new JDBCService(connectionManager);

        HibernateService Hibernateservice = new HibernateService();
        MyBatisConnectionManager myBatisConnectionManager = new MyBatisConnectionManager("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/postgres", "artemparfenov", "TestPassword12345");
        MyBatisService MyBatisservice = new MyBatisService(myBatisConnectionManager);
        DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
        Date catBD = new Date(df.parse("02-04-2023").getTime());
        Date artemBD = new Date(df.parse("09-26-2003").getTime());
        Date andrewBD = new Date(df.parse("09-26-2008").getTime());

        HibernateCatOwner artem = new HibernateCatOwner("Artem", artemBD);
        HibernateCatOwner andrew = new HibernateCatOwner("Andrew", andrewBD);
        HibernateCatOwner newOwner = new HibernateCatOwner("New", artemBD);


        HibernateCat sheila = new HibernateCat("Sheila", catBD, "Scotish", "Grey", artem);
        HibernateCat barsik = new HibernateCat("Barsik", catBD, "british", "white", artem);
        HibernateCat ball = new HibernateCat("Sharik", catBD, "british", "white", andrew);
        HibernateCat newCat = new HibernateCat("NewCat", catBD, "newBreed", "newColor", andrew);

//        Hibernateservice.deleteAllCats();
//        Hibernateservice.deleteAllCatOwners();
//        Hibernateservice.saveCatOwner(artem);
//        Hibernateservice.saveCatOwner(andrew);
//        Hibernateservice.saveCat(sheila);
//        Hibernateservice.saveCat(barsik);
//        Hibernateservice.saveCat(ball);
//        var result = Hibernateservice.getAllByCatOwnerId(sheila.getId());




        JDBCCatOwner testCatOwnerJDBC = new JDBCCatOwner("TestOwner", artemBD);
        JDBCservice.deleteAllCatOwners();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; ++i) {
            JDBCservice.saveCatOwner(testCatOwnerJDBC);
        }
        long finishTime = System.currentTimeMillis();
        String answer = new String("JDBC pushing time: " + (finishTime - startTime));
        System.out.println(answer);

        JDBCservice.deleteAllCatOwners();



        HibernateCatOwner testCatOwnerHiber = new HibernateCatOwner("TestOwner", artemBD);
        Hibernateservice.deleteAllCatOwners();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; ++i) {
            Hibernateservice.saveCatOwner(testCatOwnerHiber);
            testCatOwnerHiber = new HibernateCatOwner("TestOwner", artemBD);

        }
        finishTime = System.currentTimeMillis();
        answer = new String("Hibernate pushing time: " + (finishTime - startTime));
        System.out.println(answer);

        Hibernateservice.deleteAllCatOwners();



        MyBatisCatOwner testCatOwnerMyBatis = new MyBatisCatOwner("TestOwner", artemBD);
        MyBatisservice.deleteAllCatOwners();
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; ++i) {
            MyBatisservice.saveCatOwner(testCatOwnerMyBatis);
        }
        finishTime = System.currentTimeMillis();
        answer = new String("MyBatis pushing time: " + (finishTime - startTime));
        System.out.println(answer);

        MyBatisservice.deleteAllCatOwners();


        List<CatOwner> CatOwners = new ArrayList<>();
        long startGettingTime = System.currentTimeMillis();
        CatOwners = JDBCservice.getAllCatOwners();
        long finishGettingTime = System.currentTimeMillis();
        answer = new String("JDBC removal time: " + (finishGettingTime - startGettingTime));
        System.out.println(answer);


        List<CatOwner> hibernateCatOwners = new ArrayList<>();
        startGettingTime = System.currentTimeMillis();
        CatOwners = Hibernateservice.getAllCatOwners();
        finishGettingTime = System.currentTimeMillis();
        answer = new String("Hibernate removal time: " + (finishGettingTime - startGettingTime));
        System.out.println(answer);


        List<CatOwner> myBatisCatOwners = new ArrayList<>();
        startGettingTime = System.currentTimeMillis();
        CatOwners = MyBatisservice.getAllCatOwners();
        finishGettingTime = System.currentTimeMillis();
        answer = new String("MyBatis removal time: " + (finishGettingTime - startGettingTime));
        System.out.println(answer);
    }
}
