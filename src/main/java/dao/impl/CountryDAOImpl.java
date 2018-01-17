package dao.impl;

import connection.MyDataSource;
import dao.daofactory.Utils;
import dao.daofactory.ICountryDAO;
import entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CountryDAOImpl extends Utils implements ICountryDAO {

    private static final Logger logger = Logger.getLogger(CountryDAOImpl.class.getName());
    private static final String FIND_ALL_COUNTRIES_QUERY = "SELECT * FROM CRUISE.COUNTRY";
    private static final String FIND_COUNTRIES_BY_ID_QUERY = "SELECT * FROM CRUISE.COUNTRY WHERE ID = ?";
    private static final String FIND_COUNTRIES_BY_NAME_QUERY = "SELECT * FROM CRUISE.COUNTRY WHERE NAME = ?";
    private static final String DELETE_COUNTRIES_BY_ID_QUERY = "DELETE FROM CRUISE.COUNTRY WHERE ID = ?";
    private static final String DELETE_COUNTRIES_BY_NAME_QUERY = "DELETE FROM CRUISE.COUNTRY WHERE NAME = ?";
    private static final String MAX_COUNTRIES_ID_QUERY = "SELECT max(id) FROM CRUISE.COUNTRY";
    private static final String CREATE_COUNTRIES_QUERY = "INSERT INTO CRUISE.COUNTRY VALUES(?, ?, ?)";

    @Override
    public List<Country> findAll() {
        List<Country> list = new ArrayList<>();
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_COUNTRIES_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    Country country = new Country();
                    country.setId(resultSet.getInt("id"));
                    country.setName(resultSet.getString("name"));
                    country.setCity(resultSet.getString("city"));
                    list.add(country);
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return list;
    }

    @Override
    public Country findById(int id) {
        Country country = null;
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_COUNTRIES_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    country = new Country();
                    country.setId(resultSet.getInt("id"));
                    country.setName(resultSet.getString("name"));
                    country.setCity(resultSet.getString("city"));
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return country;
    }

    @Override
    public Country findByName(String name) {
        Country country = null;
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_COUNTRIES_BY_NAME_QUERY);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    country = new Country();
                    country.setId(resultSet.getInt("id"));
                    country.setName(resultSet.getString("name"));
                    country.setCity(resultSet.getString("city"));
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return country;
    }

    @Override
    public void deleteCountryById(int id) {
        try (Connection connection = MyDataSource.getConnection()) {
            String countryName = findById(id).getName();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COUNTRIES_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Country " + countryName + " was deleted!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }

    @Override
    public void deleteCountry(String name) {
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COUNTRIES_BY_NAME_QUERY);
            preparedStatement.setString(1, name);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Country " + name + " was deleted!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }

    @Override
    public void createCountry(Country country) {
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_COUNTRIES_QUERY);
            preparedStatement.setInt(1, getMaxId(MAX_COUNTRIES_ID_QUERY) + 1);
            preparedStatement.setString(2, country.getName());
            preparedStatement.setString(3, country.getCity());
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Country " + country.getName() + " was created!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }
}
