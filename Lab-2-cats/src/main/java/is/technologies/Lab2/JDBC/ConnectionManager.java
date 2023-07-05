package is.technologies.Lab2.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    private Connection Connection;
    public ConnectionManager() {
    }
    public java.sql.Connection getConnection() {
        return Connection;
    }
    public Connection createConnection(String user, String pwd, String url) {
        try {
            Connection con = DriverManager.getConnection(url, user, pwd);
            Connection = con;
            return con;
        }

        catch(Exception ex) {
            return null;
        }
    }
}
