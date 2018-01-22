package services;

import dao.daofactory.*;
import entity.Cruise;
import entity.Excursion;
import entity.Order;
import services.beans.BucketInfoView;
import services.beans.PagesBean;
import servlet.configuration.LocationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderService {

    private static final Logger logger = Logger.getLogger(OrderService.class.getName());
    private IUserDAO userDAO;
    private ICruiseDAO cruiseDAO;
    private IBucketDAO bucketDAO;
    private ICountryDAO countryDAO;
    private IShipDAO shipDAO;
    private IOrderDAO orderDAO;

    public OrderService() {
        AbstractFactory factory = new DAOFactory();
        userDAO = factory.createUserDAO();
        cruiseDAO = factory.createCruiseDAO();
        bucketDAO = factory.createBucketDAO();
        countryDAO = factory.createCountryDAO();
        shipDAO = factory.createShipDAO();
        orderDAO = factory.createOrderDAO();
    }

    /**
     * Create new order
     * */
    public String makeOrder(String userLogin, int cruiseID, int bucketID, double priceExcursion, String excursionNote) {
        List<BucketInfoView> ordersOfUser = findOrdersOfUser(userLogin);
        if (ordersOfUser != null) {
            for (BucketInfoView elem : ordersOfUser) {
                if (elem.getCruise().getId() == cruiseID) {
                    logger.log(Level.INFO, "Can't buy! This cruise already added in orders");
                    return LocationManager.ORDER_ALREADY_ADDED;
                }
            }
        }
        Order order = new Order();
        order.setUserID(userDAO.findByLogin(userLogin).getId());
        order.setCruiseID(cruiseID);
        order.setTicket(bucketDAO.findById(bucketID).getTicket());
        order.setPrice(bucketDAO.findById(bucketID).getPrice() + priceExcursion);
        order.setExcursion(excursionNote);
        String orderResult = orderDAO.createOrder(order);
        if (orderResult != null) {
            logger.log(Level.INFO, orderResult);
            bucketDAO.deleteBucketById(bucketID);
            orderResult = LocationManager.CREATE_ORDER;
        } else {
            logger.log(Level.INFO, "ERROR! Order not created!");
        }
        return orderResult;
    }

    /**
     * Find all orders of user
     * */
    public List<BucketInfoView> findOrdersOfUser(String userLogin) {
        List<Order> orderList = orderDAO.findByUserId(userDAO.findByLogin(userLogin).getId());
        List<BucketInfoView> bucketInfoViews = new ArrayList<>();
        if (orderList != null) {
            for (Order elem : orderList) {
                Cruise cruise = cruiseDAO.findById(elem.getCruiseID());
                bucketInfoViews.add(new BucketInfoView(null, cruise, elem.getTicket(), elem.getPrice(), countryDAO.findById(cruise.getCityFromID()).getName(), countryDAO.findById(cruise.getCityToID()).getName(), countryDAO.findById(cruise.getCityFromID()).getCity(), countryDAO.findById(cruise.getCityToID()).getCity(), shipDAO.findById(cruise.getShipID()).getName(), null, elem.getExcursion(), elem.getBonus()));
            }
        } else {
            bucketInfoViews = null;
        }
        return bucketInfoViews;
    }
}
