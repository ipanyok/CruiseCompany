package dao.impl;

import connection.DataSourceConnection;
import dao.daofactory.Utils;
import dao.daofactory.ICruiseDAO;
import entity.Cruise;
import services.beans.CruiseInfoView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CruiseDAOImpl extends Utils implements ICruiseDAO {

    private static final Logger logger = Logger.getLogger(CruiseDAOImpl.class.getName());
    private static final String FIND_ALL_CRUISES_QUERY = "SELECT * FROM CRUISE.CRUISE ORDER BY ID";
    private static final String FIND_CRUISES_BY_ID_QUERY = "SELECT * FROM CRUISE.CRUISE WHERE ID = ?";
    private static final String FIND_CRUISES_BY_NAME_QUERY = "SELECT * FROM CRUISE.CRUISE WHERE NAME = ?";
    private static final String FIND_CRUISES_BY_DEST_QUERY =
            "SELECT * FROM CRUISE.CRUISE, CRUISE.COUNTRY C_FROM, CRUISE.COUNTRY C_TO " +
            "WHERE CRUISE.CRUISE.CITY_FROM = C_FROM.ID " +
            "AND CRUISE.CRUISE.CITY_TO = C_TO.ID" +
            "AND C_FROM.NAME = ? AND C_TO.NAME = ?";
    private static final String DELETE_CRUISES_BY_ID_QUERY = "DELETE FROM CRUISE.CRUISE WHERE ID = ?";
    private static final String DELETE_CRUISES_BY_NAME_QUERY = "DELETE FROM CRUISE.CRUISE WHERE NAME = ?";
    private static final String MAX_CRUISES_ID_QUERY = "SELECT max(id) FROM CRUISE.CRUISE";
    private static final String CREATE_CRUISES_QUERY = "INSERT INTO CRUISE.CRUISE VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_CRUISES_WITH_PARAMETERS =
            "select cr.id, cr.name cruise_name, sh.name ship_name, c_from.name c_from, c_from.city c_from_city, c_to.name c_to, c_to.city c_to_city, cr.date_start, cr.date_finish, cr.ports_count, cr.category, cr.price \n" +
            "from cruise.cruise cr\n" +
            "left join cruise.country c_from on cr.city_from = c_from.id\n" +
            "left join cruise.country c_to on cr.city_to = c_to.id\n" +
            "left join cruise.ship sh on cr.id_ship = sh.id" +
            " where ";

    @Override
    public List<Cruise> findAll() {
        List<Cruise> list = new ArrayList<>();
        try (Connection connection = DataSourceConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CRUISES_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    Cruise cruise = new Cruise();
                    cruise.setId(resultSet.getInt("id"));
                    cruise.setShipID(resultSet.getInt("id_ship"));
                    cruise.setName(resultSet.getString("name"));
                    cruise.setCityFromID(resultSet.getInt("city_from"));
                    cruise.setCityToID(resultSet.getInt("city_to"));
                    cruise.setDateStart(resultSet.getDate("date_start"));
                    cruise.setDateFinish(resultSet.getDate("date_finish"));
                    cruise.setPortsCount(resultSet.getInt("ports_count"));
                    cruise.setCategory(resultSet.getString("category"));
                    cruise.setPrice(resultSet.getDouble("price"));
                    list.add(cruise);
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
    public Cruise findById(int id) {
        Cruise cruise = null;
        try (Connection connection = DataSourceConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CRUISES_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    cruise = new Cruise();
                    cruise.setId(resultSet.getInt("id"));
                    cruise.setShipID(resultSet.getInt("id_ship"));
                    cruise.setName(resultSet.getString("name"));
                    cruise.setCityFromID(resultSet.getInt("city_from"));
                    cruise.setCityToID(resultSet.getInt("city_to"));
                    cruise.setDateStart(resultSet.getDate("date_start"));
                    cruise.setDateFinish(resultSet.getDate("date_finish"));
                    cruise.setPortsCount(resultSet.getInt("ports_count"));
                    cruise.setCategory(resultSet.getString("category"));
                    cruise.setPrice(resultSet.getDouble("price"));
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return cruise;
    }

    @Override
    public Cruise findByName(String name) {
        Cruise cruise = null;
        try (Connection connection = DataSourceConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CRUISES_BY_NAME_QUERY);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    cruise = new Cruise();
                    cruise.setId(resultSet.getInt("id"));
                    cruise.setShipID(resultSet.getInt("id_ship"));
                    cruise.setName(resultSet.getString("name"));
                    cruise.setCityFromID(resultSet.getInt("city_from"));
                    cruise.setCityToID(resultSet.getInt("city_to"));
                    cruise.setDateStart(resultSet.getDate("date_start"));
                    cruise.setDateFinish(resultSet.getDate("date_finish"));
                    cruise.setPortsCount(resultSet.getInt("ports_count"));
                    cruise.setCategory(resultSet.getString("category"));
                    cruise.setPrice(resultSet.getDouble("price"));
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return cruise;
    }

    @Override
    public List<Cruise> findByDestination(String city_from, String city_to) {
        List<Cruise> listCruise = new ArrayList<>();
        try (Connection connection = DataSourceConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CRUISES_BY_DEST_QUERY);
            preparedStatement.setString(1, city_from);
            preparedStatement.setString(2, city_to);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    Cruise cruise = new Cruise();
                    cruise.setId(resultSet.getInt("id"));
                    cruise.setShipID(resultSet.getInt("id_ship"));
                    cruise.setName(resultSet.getString("name"));
                    cruise.setCityFromID(resultSet.getInt("city_from"));
                    cruise.setCityToID(resultSet.getInt("city_to"));
                    cruise.setDateStart(resultSet.getDate("date_start"));
                    cruise.setDateFinish(resultSet.getDate("date_finish"));
                    cruise.setPortsCount(resultSet.getInt("ports_count"));
                    cruise.setCategory(resultSet.getString("category"));
                    cruise.setPrice(resultSet.getDouble("price"));
                    listCruise.add(cruise);
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return listCruise;
    }

    @Override
    public void deleteCruiseById(int id) {
        try (Connection connection = DataSourceConnection.getConnection()) {
            String cruiseName = findById(id).getName();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CRUISES_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Cruise " + cruiseName + " was deleted!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }

    @Override
    public void deleteCruise(String name) {
        try (Connection connection = DataSourceConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CRUISES_BY_NAME_QUERY);
            preparedStatement.setString(1, name);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Cruise " + name + " was deleted!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }

    @Override
    public void createCruise(Cruise cruise) {
        try (Connection connection = DataSourceConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_CRUISES_QUERY);
            preparedStatement.setInt(1, getMaxId(MAX_CRUISES_ID_QUERY) + 1);
            preparedStatement.setInt(2, cruise.getShipID());
            preparedStatement.setString(3, cruise.getName());
            preparedStatement.setInt(4, cruise.getCityFromID());
            preparedStatement.setInt(5, cruise.getCityToID());
            preparedStatement.setDate(6, cruise.getDateStart());
            preparedStatement.setDate(7, cruise.getDateFinish());
            preparedStatement.setInt(8, cruise.getPortsCount());
            preparedStatement.setString(9, cruise.getCategory());
            preparedStatement.setDouble(10, cruise.getPrice());
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Cruise " + cruise.getName() + " was created!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }

    @Override
    public List<CruiseInfoView> findWithParameters(HashMap<String, String> map) {
        String str = FIND_CRUISES_WITH_PARAMETERS;
        for (Map.Entry<String, String> m : map.entrySet()) {
            if (m.getValue() != null && !m.getValue().equals("")) {
                if (m.getKey().equals("date")) {
                    str = str + "cr.date_start >= '" + m.getValue() + "' and ";
                }
                if (m.getKey().equals("countryFrom")) {
                    str = str + "c_from.name = '" + m.getValue() + "' and ";
                }
                if (m.getKey().equals("countryTo")) {
                    str = str + "c_to.name = '" + m.getValue() + "' and ";
                }
                if (m.getKey().equals("category")) {
                    str = str + "cr.category = '" + m.getValue() + "' and ";
                }
            }
        }
        str = str.substring(0, str.lastIndexOf("a") - 1) + "ORDER BY CR.ID";
        List<CruiseInfoView> listCruiseInfoResult = new ArrayList<>();
        try (Connection connection = DataSourceConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(str);
            ResultSet resSet = preparedStatement.executeQuery();
            if (resSet.next()) {
                do {
                    listCruiseInfoResult.add(new CruiseInfoView(findById(resSet.getInt("id")), resSet.getString("c_from"), resSet.getString("c_to"), resSet.getString("c_from_city"), resSet.getString("c_to_city"), resSet.getString("ship_name")));
                } while (resSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return listCruiseInfoResult;
    }
}