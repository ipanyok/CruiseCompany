package servlet.configuration;

import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class LocationManager {

    private static LocationManager instance;
    private ResourceBundle resourceBundle;

    public static final String LOGIN_BUTTON = "LOGIN";
    public static final String REGISTER_BUTTON = "REGISTER";
    public static final String SIGN_UP = "SIGN_UP";
    public static final String LOG = "LOG";
    public static final String PASS = "PASS";
    public static final String LLOC = "LLOC";
    public static final String USER_LOGIN = "USER_LOGIN";
    public static final String FNAME = "FNAME";
    public static final String LNAME = "LNAME";
    public static final String MNAME = "MNAME";
    public static final String HOME = "HOME";
    public static final String BUCKET = "BUCKET";
    public static final String ORDER = "ORDER";
    public static final String LOGOUT = "LOG_OUT";
    public static final String COUNTRY_FROM = "COUNTRY_FROM";
    public static final String COUNTRY_TO = "COUNTRY_TO";
    public static final String CATEGORY = "CATEGORY";
    public static final String DATE = "DATE";
    public static final String FIND = "FIND";
    public static final String CRUISE_NAME = "CRUISE_NAME";
    public static final String SHIP = "SHIP";
    public static final String C_FROM = "C_FROM";
    public static final String C_TO = "C_TO";
    public static final String START_DATE = "START_DATE";
    public static final String FINISH_DATE = "FINISH_DATE";
    public static final String PORTS = "PORTS";
    public static final String PRICE = "PRICE";
    public static final String ADD = "ADD";
    public static final String ADD_BONUS = "ADD_BONUS";
    public static final String USER = "USER";
    public static final String EXCURSION = "EXCURSION";
    public static final String BONUS = "BONUS";
    public static final String TICKET = "TICKET";
    public static final String BUY_NOW = "BUY_NOW";

    public static LocationManager getLocation(HttpSession session) {
        if (instance == null) {
            instance = new LocationManager();
        }
        if (session.getAttribute("LOCALE").equals("EN")) {
            instance.resourceBundle = ResourceBundle.getBundle("EN_Location");
        }
        if (session.getAttribute("LOCALE").equals("UA")) {
            instance.resourceBundle = ResourceBundle.getBundle("UA_Location");
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }
}
