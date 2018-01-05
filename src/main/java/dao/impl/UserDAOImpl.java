package dao.impl;

import connection.MyDataSource;
import dao.daofactory.Utils;
import dao.daofactory.IUserDAO;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAOImpl extends Utils implements IUserDAO {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
    private static final String FIND_ALL_USER_QUERY = "SELECT * FROM CRUISE.USER";
    private static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM CRUISE.USER USR WHERE USR.ID = ?";
    private static final String FIND_USER_BY_LOGIN_QUERY = "SELECT * FROM CRUISE.USER USR WHERE USR.LOGIN = ?";
    private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM CRUISE.USER WHERE ID = ?";
    private static final String DELETE_USER_BY_LOGIN_QUERY = "DELETE FROM CRUISE.USER WHERE LOGIN = ?";
    private static final String MAX_USER_ID_QUERY = "SELECT max(id) FROM CRUISE.USER";
    private static final String CREATE_USER_QUERY = "INSERT INTO CRUISE.USER VALUES(?, ?, ?, ?, ?, ?, ?)";

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USER_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setMiddleName(resultSet.getString("middle_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setCreateDate(resultSet.getDate("create_date"));
                    list.add(user);
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
        return list;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setMiddleName(resultSet.getString("middle_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setCreateDate(resultSet.getDate("create_date"));
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
        return user;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_QUERY);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setFirstName(resultSet.getString("first_name"));
                    user.setMiddleName(resultSet.getString("middle_name"));
                    user.setLastName(resultSet.getString("last_name"));
                    user.setCreateDate(resultSet.getDate("create_date"));
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
        return user;
    }

    @Override
    public void deleteUserById(int id) {
        try (Connection connection = MyDataSource.getConnection()) {
            String userLogin = findById(id) != null ? findById(id).getLogin() : "no user";
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "User " + userLogin + " was deleted!");
            } else {
                logger.log(Level.INFO, "Have no user " + userLogin + " in system!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    @Override
    public void deleteUser(String name) {
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_LOGIN_QUERY);
            preparedStatement.setString(1, name);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "User " + name + " was deleted!");
            } else {
                logger.log(Level.INFO, "Have no user " + name + " in system!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    @Override
    public boolean createUser(User user) {
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_QUERY);
            preparedStatement.setInt(1, getMaxId(MAX_USER_ID_QUERY) + 1);
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getMiddleName());
            preparedStatement.setString(6, user.getLastName());
            preparedStatement.setDate(7, user.getCreateDate());
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "User " + user.getLogin() + " was created!");
                return true;
            } else {
                logger.log(Level.INFO, "User " + user.getLogin() + " does not create!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
        return false;
    }
}
