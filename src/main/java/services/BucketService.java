package services;

import dao.daofactory.*;
import entity.Bucket;
import entity.Cruise;
import entity.Excursion;
import services.beans.BucketInfoView;
import servlet.configuration.LocationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BucketService {

    private static final Logger logger = Logger.getLogger(BucketService.class.getName());
    private IUserDAO userDAO;
    private ICruiseDAO cruiseDAO;
    private IBucketDAO bucketDAO;
    private ICountryDAO countryDAO;
    private IShipDAO shipDAO;
    private IExcursionDAO excursionDAO;

    public BucketService() {
        AbstractFactory factory = new DAOFactory();
        userDAO = factory.createUserDAO();
        cruiseDAO = factory.createCruiseDAO();
        bucketDAO = factory.createBucketDAO();
        countryDAO = factory.createCountryDAO();
        shipDAO = factory.createShipDAO();
        excursionDAO = factory.createExcursionDAO();
    }

    /**
     * Add an order in user's bucket
     * */
    public String addBucketToUser(String userName, int cruiseId) {
        List<BucketInfoView> bucketsOfUser = findBucketsOfUser(userName);
        if (bucketsOfUser != null) {
            for (BucketInfoView elem : bucketsOfUser) {
                if (elem.getCruise().getId() == cruiseId) {
                    logger.log(Level.INFO, "This cruise is already added in bucket!!!");
                    return LocationManager.CRUISE_ALREADY_ADDED;
                }
            }
        }
        Bucket bucket = new Bucket();
        bucket.setUserID(userDAO.findByLogin(userName).getId());
        bucket.setCruiseID(cruiseId);
        bucket.setTicket(userName + "_ticket №" + new Random().nextInt(1000));
        bucket.setPrice(cruiseDAO.findById(cruiseId).getPrice());
        String resOfAdd = bucketDAO.createBucket(bucket);
        if (resOfAdd != null) {
            logger.log(Level.INFO, resOfAdd);
            resOfAdd = LocationManager.CREATE_BUCKET;
        } else {
            logger.log(Level.INFO, "ERROR! Not created!");
        }
        return resOfAdd;
    }

    /**
     * Find all orders in bucket of user
     * */
    public List<BucketInfoView> findBucketsOfUser(String userName) {
        List<Bucket> buckets = bucketDAO.findByUserId(userDAO.findByLogin(userName).getId());
        List<BucketInfoView> bucketInfoViews = new ArrayList<>();
        if (buckets != null) {
            for (Bucket elem : buckets) {
                Cruise cruise = cruiseDAO.findById(elem.getCruiseID());
                List<Excursion> listExcursions = excursionDAO.findByCruiseId(cruise.getId());
                bucketInfoViews.add(new BucketInfoView(elem, cruise, elem.getTicket(), elem.getPrice(), countryDAO.findById(cruise.getCityFromID()).getName(), countryDAO.findById(cruise.getCityToID()).getName(), countryDAO.findById(cruise.getCityFromID()).getCity(), countryDAO.findById(cruise.getCityToID()).getCity(), shipDAO.findById(cruise.getShipID()).getName(), listExcursions, null, null));
            }
        } else {
            bucketInfoViews = null;
        }
        return bucketInfoViews;
    }

    /**
     * Delete all orders in user's bucket.
     * Used on "log out" button click
     * */
    public void deleteBucketOfUser(String userLogin) {
        List<Bucket> buckets = bucketDAO.findByUserId(userDAO.findByLogin(userLogin).getId());
        if (buckets != null) {
            for (Bucket elem : buckets) {
                bucketDAO.deleteBucketById(elem.getId());
            }
            logger.log(Level.INFO, "Bucket of user " + userLogin + " was deleted!");
        } else {
            logger.log(Level.INFO, "No buckets to delete...");
        }
    }
}
