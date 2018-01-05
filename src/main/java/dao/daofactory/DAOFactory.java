package dao.daofactory;

import dao.impl.*;

public class DAOFactory implements AbstractFactory{

    @Override
    public UserDAOImpl createUserDAO() {
        return new UserDAOImpl();
    }

    @Override
    public StaffDAOImpl createStaffDAO() {
        return new StaffDAOImpl();
    }

    @Override
    public ShipDAOImpl createShipDAO() {
        return new ShipDAOImpl();
    }

    @Override
    public ExcursionDAOImpl createExcursionDAO() {
        return new ExcursionDAOImpl();
    }

    @Override
    public CruiseDAOImpl createCruiseDAO() {
        return new CruiseDAOImpl();
    }

    @Override
    public CountryDAOImpl createCountryDAO() {
        return new CountryDAOImpl();
    }

    @Override
    public CardDAOImpl createCardDAO() {
        return new CardDAOImpl();
    }

    @Override
    public BucketDAOImpl createBucketDAO() {
        return new BucketDAOImpl();
    }

    @Override
    public OrderDAOImpl createOrderDAO() {
        return new OrderDAOImpl();
    }
}
