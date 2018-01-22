package dao.impl;

import connection.DataSourceConnection;
import dao.daofactory.Utils;
import dao.daofactory.IStaffDAO;
import entity.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaffDAOImpl extends Utils implements IStaffDAO {

    private static final Logger logger = Logger.getLogger(StaffDAOImpl.class.getName());
    private static final String FIND_ALL_STAFF_QUERY = "SELECT * FROM CRUISE.STAFF";
    private static final String FIND_STAFF_BY_SHIP_ID_QUERY = "SELECT * FROM CRUISE.STAFF WHERE ID_SHIP = ?";
    private static final String FIND_STAFF_BY_ID_QUERY = "SELECT * FROM CRUISE.STAFF WHERE ID = ?";
    private static final String FIND_STAFF_BY_NAME_QUERY = "SELECT * FROM CRUISE.STAFF WHERE NAME = ?";
    private static final String DELETE_STAFF_BY_ID_QUERY = "DELETE FROM CRUISE.STAFF WHERE ID = ?";
    private static final String DELETE_STAFF_BY_NAME_QUERY = "DELETE FROM CRUISE.STAFF WHERE NAME = ?";
    private static final String MAX_STAFF_ID_QUERY = "SELECT max(id) FROM CRUISE.STAFF";
    private static final String CREATE_STAFF_QUERY = "INSERT INTO CRUISE.STAFF VALUES(?, ?, ?, ?)";

    @Override
    public List<Staff> findAll() {
        List<Staff> list = new ArrayList<>();
        try (Connection connection = DataSourceConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_STAFF_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    Staff staff = new Staff();
                    staff.setId(resultSet.getInt("id"));
                    staff.setShipID(resultSet.getInt("id_ship"));
                    staff.setName(resultSet.getString("name"));
                    staff.setSpeciality(resultSet.getString("speciality"));
                    list.add(staff);
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
    public List<Staff> findByShipId(int idShip) {
        List<Staff> staffList = new ArrayList<>();
        try (Connection connection = DataSourceConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_STAFF_BY_SHIP_ID_QUERY);
            preparedStatement.setInt(1, idShip);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    Staff staff = new Staff();
                    staff.setId(resultSet.getInt("id"));
                    staff.setShipID(resultSet.getInt("id_ship"));
                    staff.setName(resultSet.getString("name"));
                    staff.setSpeciality(resultSet.getString("speciality"));
                    staffList.add(staff);
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return staffList;
    }

    @Override
    public Staff findByName(String name) {
        Staff staff = null;
        try (Connection connection = DataSourceConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_STAFF_BY_NAME_QUERY);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    staff = new Staff();
                    staff.setId(resultSet.getInt("id"));
                    staff.setShipID(resultSet.getInt("id_ship"));
                    staff.setName(resultSet.getString("name"));
                    staff.setSpeciality(resultSet.getString("speciality"));
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return staff;
    }

    @Override
    public Staff findById(int id) {
        Staff staff = null;
        try (Connection connection = DataSourceConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_STAFF_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    staff = new Staff();
                    staff.setId(resultSet.getInt("id"));
                    staff.setShipID(resultSet.getInt("id_ship"));
                    staff.setName(resultSet.getString("name"));
                    staff.setSpeciality(resultSet.getString("speciality"));
                } while (resultSet.next());
            } else {
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
        return staff;
    }

    @Override
    public void deleteStaffById(int id) {
        try (Connection connection = DataSourceConnection.getConnection()) {
            String staffName = findById(id).getName();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STAFF_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Staff " + staffName + " was deleted!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }

    @Override
    public void deleteStaff(String name) {
        try (Connection connection = DataSourceConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STAFF_BY_NAME_QUERY);
            preparedStatement.setString(1, name);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Staff " + name + " was deleted!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }

    @Override
    public void createStaff(Staff staff) {
        try (Connection connection = DataSourceConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_STAFF_QUERY);
            preparedStatement.setInt(1, getMaxId(MAX_STAFF_ID_QUERY) + 1);
            preparedStatement.setInt(2, staff.getShipID());
            preparedStatement.setString(3, staff.getName());
            preparedStatement.setString(4, staff.getSpeciality());
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Staff " + staff.getName() + " was created!");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.toString());
        }
    }
}
