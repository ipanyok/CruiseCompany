package dao.daofactory;

import connection.DataSourceConnection;
import dao.impl.UserDAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Utils {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());

    public static int getMaxId(String query) {
        int result = 0;
        try (Connection connection = DataSourceConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return result;
    }
}
