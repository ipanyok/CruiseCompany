package services;

import dao.daofactory.*;
import entity.User;
import servlet.configuration.LocationManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckService {

    private static final Logger logger = Logger.getLogger(CheckService.class.getName());
    private IUserDAO userDAO;


    public CheckService() {
        AbstractFactory factory = new DAOFactory();
        userDAO = factory.createUserDAO();
    }

    /**
     * Make a validation of user data in login page
     * */
    public String checkUser(String login, String password) {
        if (login == null || login.equals("")) {
            logger.log(Level.INFO, "Login is empty!");
            return LocationManager.EMPTY_LOGIN;
        } else {
            User user = userDAO.findByLogin(login);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return null;
                } else {
                    logger.log(Level.INFO, "Wrong password!");
                    return LocationManager.WRONG_PASSWORD;
                }
            } else {
                logger.log(Level.INFO, "User does not exists in System!!!");
                return LocationManager.IS_EXIST_USER;
            }
        }
    }

    /**
     * Make a validation of user data in register page
     * */
    public String registerUser(HttpServletRequest request) {
        AbstractFactory factory = new DAOFactory();
        IUserDAO userDAO = factory.createUserDAO();
        User user = new User();
        user.setLogin(request.getParameter("loginRegister"));
        user.setPassword(request.getParameter("passwordRegister"));
        user.setFirstName(request.getParameter("firstNameRegister"));
        user.setMiddleName(request.getParameter("middleNameRegister"));
        user.setLastName(request.getParameter("lastNameRegister"));
        user.setCreateDate(new Date(new java.util.Date().getTime()));
        if (user.getLogin() == null || user.getLogin().equals("")) {
            logger.log(Level.INFO, "Login is empty");
            return LocationManager.EMPTY_LOGIN;
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            logger.log(Level.INFO, "Password is empty");
            return LocationManager.EMPTY_PASSWORD;
        }
        if (user.getFirstName() == null || user.getFirstName().equals("")) {
            logger.log(Level.INFO, "First Name is empty");
            return LocationManager.EMPTY_FIRST_NAME;
        }
        if (user.getLastName() == null || user.getLastName().equals("")) {
            logger.log(Level.INFO, "Last Name is empty");
            return LocationManager.EMPTY_LAST_NAME;
        }
        if (userDAO.findByLogin(user.getLogin()) == null) {
            boolean result = userDAO.createUser(user);
            if (result) {
                return null;
            } else {
                return LocationManager.ERROR_DAO;
            }
        } else {
            logger.log(Level.INFO, "User with login " + user.getLogin() + " exist!");
            return LocationManager.USER_IS_EXIST;
        }
    }
}
