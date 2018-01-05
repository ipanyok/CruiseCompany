package dao.daofactory;

import dao.impl.*;

public interface AbstractFactory {
    UserDAOImpl createUserDAO();
    StaffDAOImpl createStaffDAO();
    ShipDAOImpl createShipDAO();
    ExcursionDAOImpl createExcursionDAO();
    CruiseDAOImpl createCruiseDAO();
    CountryDAOImpl createCountryDAO();
    CardDAOImpl createCardDAO();
    BucketDAOImpl createBucketDAO();
    OrderDAOImpl createOrderDAO();
}
