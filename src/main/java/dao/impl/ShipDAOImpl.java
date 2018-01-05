package dao.impl;

import connection.MyDataSource;
import dao.daofactory.Utils;
import dao.daofactory.IShipDAO;
import entity.Ship;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShipDAOImpl extends Utils implements IShipDAO {

    private static final Logger logger = Logger.getLogger(ShipDAOImpl.class.getName());
    private static final String FIND_ALL_SHIP_QUERY = "SELECT * FROM CRUISE.SHIP";
    private static final String FIND_SHIP_BY_ID_QUERY = "SELECT * FROM CRUISE.SHIP WHERE ID = ?";
    private static final String FIND_SHIP_BY_NAME_QUERY = "SELECT * FROM CRUISE.SHIP WHERE NAME = ?";
    private static final String DELETE_SHIP_BY_ID_QUERY = "DELETE FROM CRUISE.SHIP WHERE ID = ?";
    private static final String DELETE_SHIP_BY_NAME_QUERY = "DELETE FROM CRUISE.SHIP WHERE NAME = ?";
    private static final String MAX_SHIP_ID_QUERY = "SELECT max(id) FROM CRUISE.SHIP";
    private static final String CREATE_SHIP_QUERY = "INSERT INTO CRUISE.SHIP VALUES(?, ?, ?)";

    @Override
    public List<Ship> findAll() {
        List<Ship> list = new ArrayList<>();
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SHIP_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    Ship ship = new Ship();
                    ship.setId(resultSet.getInt("id"));
                    ship.setName(resultSet.getString("name"));
                    ship.setCountPassengers(resultSet.getInt("count_passengers"));
                    list.add(ship);
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
    public Ship findById(int id) {
        Ship ship = null;
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_SHIP_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    ship = new Ship();
                    ship.setId(resultSet.getInt("id"));
                    ship.setName(resultSet.getString("name"));
                    ship.setCountPassengers(resultSet.getInt("count_passengers"));
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
        return ship;
    }

    @Override
    public Ship findByName(String name) {
        Ship ship = null;
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_SHIP_BY_NAME_QUERY);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    ship = new Ship();
                    ship.setId(resultSet.getInt("id"));
                    ship.setName(resultSet.getString("name"));
                    ship.setCountPassengers(resultSet.getInt("count_passengers"));
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
        return ship;
    }

    @Override
    public void deleteShipById(int id) {
        try (Connection connection = MyDataSource.getConnection()) {
            String shipName = findById(id).getName();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SHIP_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Ship " + shipName + " was deleted!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    @Override
    public void deleteShip(String name) {
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SHIP_BY_NAME_QUERY);
            preparedStatement.setString(1, name);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Ship " + name + " was deleted!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    @Override
    public void createShip(Ship ship) {
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SHIP_QUERY);
            preparedStatement.setInt(1, getMaxId(MAX_SHIP_ID_QUERY) + 1);
            preparedStatement.setString(2, ship.getName());
            preparedStatement.setInt(3, ship.getCountPassengers());
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Ship " + ship.getName() + " was created!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
    }
}
