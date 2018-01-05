package dao.impl;

import connection.MyDataSource;
import dao.daofactory.IBucketDAO;
import dao.daofactory.Utils;
import entity.Bucket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BucketDAOImpl extends Utils implements IBucketDAO {

    private static final Logger logger = Logger.getLogger(BucketDAOImpl.class.getName());
    private static final String FIND_BUCKET_BY_ID_QUERY = "SELECT * FROM CRUISE.BUCKET WHERE ID = ?";
    private static final String FIND_BUCKET_BY_USER_ID_QUERY = "SELECT * FROM CRUISE.BUCKET WHERE ID_USER = ?";
    private static final String DELETE_BUCKET_BY_ID_QUERY = "DELETE FROM CRUISE.BUCKET WHERE ID = ?";
    private static final String MAX_BUCKET_ID_QUERY = "SELECT max(id) FROM CRUISE.BUCKET";
    private static final String CREATE_BUCKET_QUERY = "INSERT INTO CRUISE.BUCKET VALUES(?, ?, ?, ?, ?)";

    @Override
    public Bucket findById(int id) {
        Bucket bucket = null;
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BUCKET_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                logger.log(Level.INFO, "Result Set is empty!");
            } else {
                do {
                    bucket = new Bucket();
                    bucket.setId(resultSet.getInt("id"));
                    bucket.setUserID(resultSet.getInt("id_user"));
                    bucket.setCruiseID(resultSet.getInt("id_cruise"));
                    bucket.setTicket(resultSet.getString("ticket"));
                    bucket.setPrice(resultSet.getDouble("price"));
                } while (resultSet.next());
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
        return bucket;
    }

    @Override
    public List<Bucket> findByUserId(int idUser) {
        List<Bucket> bucketList = new ArrayList<>();
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BUCKET_BY_USER_ID_QUERY);
            preparedStatement.setInt(1, idUser);
            ResultSet resSet = preparedStatement.executeQuery();
            if (resSet.next()) {
                do {
                    Bucket bucket = new Bucket();
                    bucket.setId(resSet.getInt("id"));
                    bucket.setUserID(resSet.getInt("id_user"));
                    bucket.setCruiseID(resSet.getInt("id_cruise"));
                    bucket.setTicket(resSet.getString("ticket"));
                    bucket.setPrice(resSet.getDouble("price"));
                    bucketList.add(bucket);
                } while (resSet.next());
            } else {
                bucketList = null;
                logger.log(Level.INFO, "Result Set is empty!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
        return bucketList;
    }

    @Override
    public void deleteBucketById(int id) {
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BUCKET_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Bucket was deleted!");
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    @Override
    public String createBucket(Bucket bucket) {
        try (Connection connection = MyDataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_BUCKET_QUERY);
            preparedStatement.setInt(1, getMaxId(MAX_BUCKET_ID_QUERY) + 1);
            preparedStatement.setInt(2, bucket.getUserID());
            preparedStatement.setInt(3, bucket.getCruiseID());
            preparedStatement.setString(4, bucket.getTicket());
            preparedStatement.setDouble(5, bucket.getPrice());
            int result = preparedStatement.executeUpdate();
            if (result != 0) {
                connection.commit();
                logger.log(Level.INFO, "Bucket was created!");
                return "Bucket was created";
            }
        } catch (SQLException e) {
            logger.log(Level.INFO, e.toString());
            return null;
        }
        return null;
    }
}
