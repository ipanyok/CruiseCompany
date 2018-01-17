package dao.impl;

import connection.MyDataSource;
import dao.daofactory.IOrderDAO;
import dao.daofactory.Utils;
import entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAOImpl extends Utils implements IOrderDAO {

    private static final Logger logger = Logger.getLogger(OrderDAOImpl.class.getName());
    private static final String FIND_ALL_ORDER_QUERY = "SELECT * FROM CRUISE.ORDER";
    private static final String FIND_ORDER_BY_ID_QUERY = "SELECT * FROM CRUISE.ORDER WHERE ID = ?";
    private static final String FIND_ORDER_BY_USER_ID_QUERY = "SELECT * FROM CRUISE.ORDER WHERE ID_USER = ?";
    private static final String DELETE_ORDER_BY_ID_QUERY = "DELETE FROM CRUISE.ORDER WHERE ID = ?";
    private static final String MAX_ORDER_ID_QUERY = "SELECT max(id) FROM CRUISE.ORDER";
    private static final String CREATE_ORDER_QUERY = "INSERT INTO CRUISE.ORDER VALUES(?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_BONUS_QUERY = "UPDATE CRUISE.ORDER SET BONUS = ? WHERE ID = ?";


    @Override
    public List<Order> findAll() {
        List<Order> list = new ArrayList<>();
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ORDER_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    Order order = new Order();
                    order.setId(resultSet.getInt("id"));
                    order.setUserID(resultSet.getInt("id_user"));
                    order.setCruiseID(resultSet.getInt("id_cruise"));
                    order.setTicket(resultSet.getString("ticket"));
                    order.setPrice(resultSet.getDouble("price"));
                    order.setBonus(resultSet.getString("bonus"));
                    order.setExcursion(resultSet.getString("excursion"));
                    list.add(order);
                } while (resultSet.next());
            } else {
                list = null;
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return list;
    }

    @Override
    public Order findById(int id) {
        Order order = null;
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    order = new Order();
                    order.setId(resultSet.getInt("id"));
                    order.setUserID(resultSet.getInt("id_user"));
                    order.setCruiseID(resultSet.getInt("id_cruise"));
                    order.setTicket(resultSet.getString("ticket"));
                    order.setPrice(resultSet.getDouble("price"));
                    order.setBonus(resultSet.getString("bonus"));
                    order.setExcursion(resultSet.getString("excursion"));
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return order;
    }

    @Override
    public List<Order> findByUserId(int idUser) {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDER_BY_USER_ID_QUERY);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    Order order = new Order();
                    order.setId(resultSet.getInt("id"));
                    order.setUserID(resultSet.getInt("id_user"));
                    order.setCruiseID(resultSet.getInt("id_cruise"));
                    order.setTicket(resultSet.getString("ticket"));
                    order.setPrice(resultSet.getDouble("price"));
                    order.setBonus(resultSet.getString("bonus"));
                    order.setExcursion(resultSet.getString("excursion"));
                    orderList.add(order);
                } while (resultSet.next());
            } else {
                orderList = null;
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return orderList;
    }

    @Override
    public void deleteOrderById(int id) {
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Order was deleted!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }

    @Override
    public String createOrder(Order order) {
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER_QUERY);
            preparedStatement.setInt(1, getMaxId(MAX_ORDER_ID_QUERY) + 1);
            preparedStatement.setInt(2, order.getUserID());
            preparedStatement.setInt(3, order.getCruiseID());
            preparedStatement.setString(4, order.getTicket());
            preparedStatement.setDouble(5, order.getPrice());
            preparedStatement.setString(6, order.getBonus());
            preparedStatement.setString(7, order.getExcursion());
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Order was created!");
                return "Order was created!";
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
            return "ERROR in Database Access!";
        }
        return null;
    }

    @Override
    public String updateBonus(int orderID, String value) {
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BONUS_QUERY);
            preparedStatement.setString(1, value);
            preparedStatement.setInt(2, orderID);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Bonus was updated!");
                return "Bonus was updated!";
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
            return "ERROR in Database Access!";
        }
        return null;
    }
}
