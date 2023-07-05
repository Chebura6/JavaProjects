package is.technologies.Lab2.MyBatis.Models;
import is.technologies.Lab2.MyBatis.Mappers.CatMapper;
import is.technologies.Lab2.MyBatis.Mappers.CatOwnerMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;

public class MyBatisConnectionManager {
    private SqlSessionFactory SessionFactory;
    public MyBatisConnectionManager(String driver, String url, String username, String password) {
            DataSource dataSource = new PooledDataSource(driver, url, username, password);
            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment environment = new Environment("development", transactionFactory, dataSource);
            Configuration configuration = new Configuration(environment);
            configuration.addMapper(CatMapper.class);
            configuration.addMapper(CatOwnerMapper.class);
            SessionFactory = new SqlSessionFactoryBuilder().build(configuration);


        }
    public SqlSession getSession() throws IOException {
        return SessionFactory.openSession();
    }
}
