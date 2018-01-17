package services;

import dao.daofactory.*;
import entity.Cruise;
import entity.Excursion;
import entity.Order;
import services.beans.BucketInfoView;
import services.beans.OrdersInfoView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminService {

    private static final Logger logger = Logger.getLogger(AdminService.class.getName());
    private IUserDAO userDAO;
    private ICruiseDAO cruiseDAO;
    private ICountryDAO countryDAO;
    private IShipDAO shipDAO;
    private IOrderDAO orderDAO;

    public AdminService() {
        AbstractFactory factory = new DAOFactory();
        userDAO = factory.createUserDAO();
        cruiseDAO = factory.createCruiseDAO();
        countryDAO = factory.createCountryDAO();
        shipDAO = factory.createShipDAO();
        orderDAO = factory.createOrderDAO();
    }

    /**
     * Find all orders of all users in admin page
     * */
    public List<OrdersInfoView> findAllOrders() {
        List<Order> orderList = orderDAO.findAll();
        List<OrdersInfoView> ordersInfoViewsList = new ArrayList<>();
        if (orderList != null) {
            for (Order elem : orderList) {
                Cruise cruise = cruiseDAO.findById(elem.getCruiseID());
                OrdersInfoView ordersInfoView = new OrdersInfoView(elem,
                        userDAO.findById(elem.getUserID()),
                        cruise, countryDAO.findById(cruise.getCityFromID()), countryDAO.findById(cruise.getCityToID()), shipDAO.findById(cruise.getShipID()));
                ordersInfoViewsList.add(ordersInfoView);
            }
        } else {
            ordersInfoViewsList = null;
        }
        return ordersInfoViewsList;
    }

    /**
     * Change bonus of user
     * */
    public String changeBonus(int orderID, String bonus) {
        String resultMessage = orderDAO.updateBonus(orderID, bonus);
        if (resultMessage != null) {
            logger.log(Level.INFO, resultMessage);
        } else {
            logger.log(Level.INFO, "ERROR! Not created!");
        }
        return resultMessage;
    }
}
