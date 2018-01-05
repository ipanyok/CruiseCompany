package dao.impl;

import connection.MyDataSource;
import dao.daofactory.Utils;
import dao.daofactory.IExcursionDAO;
import entity.Excursion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExcursionDAOImpl extends Utils implements IExcursionDAO {

    private static final Logger logger = Logger.getLogger(ExcursionDAOImpl.class.getName());
    private static final String FIND_ALL_EXCURSIONS_QUERY = "SELECT * FROM CRUISE.EXCURSION";
    private static final String FIND_EXCURSIONS_BY_CRUISE_ID_QUERY = "SELECT * FROM CRUISE.EXCURSION WHERE ID_CRUISE = ?";
    private static final String FIND_EXCURSIONS_BY_ID_QUERY = "SELECT * FROM CRUISE.EXCURSION WHERE ID = ?";
    private static final String FIND_EXCURSIONS_BY_NAME_QUERY = "SELECT * FROM CRUISE.EXCURSION WHERE NAME = ?";
    private static final String DELETE_EXCURSIONS_BY_ID_QUERY = "DELETE FROM CRUISE.EXCURSION WHERE ID = ?";
    private static final String DELETE_EXCURSIONS_BY_NAME_QUERY = "DELETE FROM CRUISE.EXCURSION WHERE NAME = ?";
    private static final String MAX_EXCURSIONS_ID_QUERY = "SELECT max(id) FROM CRUISE.EXCURSION";
    private static final String CREATE_EXCURSIONS_QUERY = "INSERT INTO CRUISE.EXCURSION VALUES(?, ?, ?, ?)";

    @Override
    public List<Excursion> findAll() {
        List<Excursion> list = new ArrayList<>();
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_EXCURSIONS_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    Excursion excursion = new Excursion();
                    excursion.setId(resultSet.getInt("id"));
                    excursion.setCruiseID(resultSet.getInt("id_cruise"));
                    excursion.setName(resultSet.getString("name"));
                    excursion.setPrice(resultSet.getInt("price"));
                    list.add(excursion);
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
    public List<Excursion> findByCruiseId(int idCruise) {
        List<Excursion> excursionList = new ArrayList<>();
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_EXCURSIONS_BY_CRUISE_ID_QUERY);
            preparedStatement.setInt(1, idCruise);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    Excursion excursion = new Excursion();
                    excursion.setId(resultSet.getInt("id"));
                    excursion.setCruiseID(resultSet.getInt("id_cruise"));
                    excursion.setName(resultSet.getString("name"));
                    excursion.setPrice(resultSet.getInt("price"));
                    excursionList.add(excursion);
                } while (resultSet.next());
            } else {
                excursionList = null;
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
        return excursionList;
    }

    @Override
    public Excursion findByName(String name) {
        Excursion excursion = null;
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_EXCURSIONS_BY_NAME_QUERY);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    excursion = new Excursion();
                    excursion.setId(resultSet.getInt("id"));
                    excursion.setCruiseID(resultSet.getInt("id_cruise"));
                    excursion.setName(resultSet.getString("name"));
                    excursion.setPrice(resultSet.getInt("price"));
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
        return excursion;
    }

    @Override
    public Excursion findById(int id) {
        Excursion excursion = null;
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_EXCURSIONS_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    excursion = new Excursion();
                    excursion.setId(resultSet.getInt("id"));
                    excursion.setCruiseID(resultSet.getInt("id_cruise"));
                    excursion.setName(resultSet.getString("name"));
                    excursion.setPrice(resultSet.getInt("price"));
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
        return excursion;
    }

    @Override
    public void deleteExcursionById(int id) {
        try (Connection connection = MyDataSource.getConnection()) {
            String excursionName = findById(id).getName();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EXCURSIONS_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Excursion " + excursionName + " was deleted!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    @Override
    public void deleteExcursion(String name) {
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EXCURSIONS_BY_NAME_QUERY);
            preparedStatement.setString(1, name);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Excursion " + name + " was deleted!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    @Override
    public void createExcursion(Excursion excursion) {
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_EXCURSIONS_QUERY);
            preparedStatement.setInt(1, getMaxId(MAX_EXCURSIONS_ID_QUERY) + 1);
            preparedStatement.setInt(2, excursion.getCruiseID());
            preparedStatement.setString(3, excursion.getName());
            preparedStatement.setDouble(4, excursion.getPrice());
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Excursion " + excursion.getName() + " was created!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
    }
}
