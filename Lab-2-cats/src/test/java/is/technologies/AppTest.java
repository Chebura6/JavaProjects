package is.technologies;

import is.technologies.Lab2.Hibernate.HibernateService;
import is.technologies.Lab2.Hibernate.Models.HibernateCat;
import is.technologies.Lab2.Hibernate.Models.HibernateCatOwner;
import is.technologies.Lab2.Interfaces.Cat;
import is.technologies.Lab2.Interfaces.CatOwner;
import is.technologies.Lab2.JDBC.ConnectionManager;
import is.technologies.Lab2.JDBC.JDBCService;
import is.technologies.Lab2.JDBC.Models.JDBCCat;
import is.technologies.Lab2.JDBC.Models.JDBCCatOwner;
import is.technologies.Lab2.MyBatis.Models.MyBatisCat;
import is.technologies.Lab2.MyBatis.Models.MyBatisCatOwner;
import is.technologies.Lab2.MyBatis.Models.MyBatisConnectionManager;
import is.technologies.Lab2.MyBatis.MyBatisService;
import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    private DateFormat df;
    private Date catBD;
    private Date artemBD;
    private Date andrewBD;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName ) throws Exception
    {
        super( testName );
        df = new SimpleDateFormat("MM-dd-yyyy");
        catBD = new Date(df.parse("02-04-2023").getTime());
        artemBD = new Date(df.parse("09-26-2003").getTime());
        andrewBD = new Date(df.parse("09-26-2008").getTime());
    }

    @Test
    public void jdbcSaveTest() throws SQLException
    {
        ConnectionManager connectionManager = new ConnectionManager();
        connectionManager.createConnection("artemparfenov","TestPassword12345", "jdbc:postgresql://localhost:5432/postgres");
        JDBCService JDBCservice = new JDBCService(connectionManager);

        JDBCCatOwner artem = new JDBCCatOwner("Artem", artemBD);
        JDBCCatOwner andrew = new JDBCCatOwner("Andrew", andrewBD);

        JDBCCat sheila = new JDBCCat("Sheila", catBD, "Scotish", "Grey", artem);
        JDBCCat barsik = new JDBCCat("Barsik", catBD, "british", "white", artem);
        JDBCCat ball = new JDBCCat("Sharik", catBD, "british", "white", andrew);

        JDBCservice.saveCatOwner(artem);
        JDBCservice.saveCatOwner(andrew);
        JDBCservice.saveCat(sheila);
        JDBCservice.saveCat(barsik);
        JDBCservice.saveCat(ball);
        assertTrue( true );
    }

    @Test
    public void hibernateSaveTest() throws SQLException
    {
        HibernateService Hibernateservice = new HibernateService();

        HibernateCatOwner artem = new HibernateCatOwner("Artem", artemBD);
        HibernateCatOwner andrew = new HibernateCatOwner("Andrew", andrewBD);

        HibernateCat sheila = new HibernateCat("Sheila", catBD, "Scotish", "Grey", artem);
        HibernateCat barsik = new HibernateCat("Barsik", catBD, "british", "white", artem);
        HibernateCat ball = new HibernateCat("Sharik", catBD, "british", "white", andrew);

        Hibernateservice.saveCatOwner(artem);
        Hibernateservice.saveCatOwner(andrew);
        Hibernateservice.saveCat(sheila);
        Hibernateservice.saveCat(barsik);
        Hibernateservice.saveCat(ball);

        assertTrue( true );
    }
    @Test
    public void myBatisSaveTest() throws SQLException
    {
        MyBatisConnectionManager myBatisConnectionManager = new MyBatisConnectionManager("org.postgresql.Driver", "jdbc:postgresql://localhost:5432/postgres", "artemparfenov", "TestPassword12345");
        MyBatisService MyBatisservice = new MyBatisService(myBatisConnectionManager);

        MyBatisCatOwner artem = new MyBatisCatOwner("Artem", artemBD);
        MyBatisCatOwner andrew = new MyBatisCatOwner("Andrew", andrewBD);

        MyBatisCat sheila = new MyBatisCat("Sheila", catBD, "Scotish", "Grey", artem);
        MyBatisCat barsik = new MyBatisCat("Barsik", catBD, "british", "white", artem);
        MyBatisCat ball = new MyBatisCat("Sharik", catBD, "british", "white", andrew);

        MyBatisservice.saveCatOwner(artem);
        MyBatisservice.saveCatOwner(andrew);
        MyBatisservice.saveCat(sheila);
        MyBatisservice.saveCat(barsik);
        MyBatisservice.saveCat(ball);

        assertTrue( true );
    }
}
