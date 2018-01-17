package connection;

import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyDataSource {

    private static final Logger logger = Logger.getLogger(MyDataSource.class.getName());

    public static Connection getConnection() {
        Connection connection = null;
        DataSource ds;
        try {
            InitialContext ic = new InitialContext();
            ds = (DataSource) ic.lookup("java:comp/env/jdbc/cruise");
            connection = ds.getConnection();
            connection.setAutoCommit(false);
        } catch (NamingException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
        return connection;
    }
}
