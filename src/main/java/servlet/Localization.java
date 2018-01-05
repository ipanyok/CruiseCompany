package servlet;

import servlet.configuration.LocationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

public class Localization {

    public static void setLocation(HttpSession session) {
        session.setAttribute("loginLoc", LocationManager.getLocation(session).getProperty(LocationManager.LOGIN_BUTTON));
        session.setAttribute("registerLoc", LocationManager.getLocation(session).getProperty(LocationManager.REGISTER_BUTTON));
        session.setAttribute("signUpLoc", LocationManager.getLocation(session).getProperty(LocationManager.SIGN_UP));
        session.setAttribute("logLoc", LocationManager.getLocation(session).getProperty(LocationManager.LOG));
        session.setAttribute("passLoc", LocationManager.getLocation(session).getProperty(LocationManager.PASS));
        session.setAttribute("lLoc", LocationManager.getLocation(session).getProperty(LocationManager.LLOC));
        session.setAttribute("userLoginRegFormLoc", LocationManager.getLocation(session).getProperty(LocationManager.USER_LOGIN));
        session.setAttribute("fnameRegFormLoc", LocationManager.getLocation(session).getProperty(LocationManager.FNAME));
        session.setAttribute("lnameRegFormLoc", LocationManager.getLocation(session).getProperty(LocationManager.LNAME));
        session.setAttribute("mnameRegFormLoc", LocationManager.getLocation(session).getProperty(LocationManager.MNAME));
        session.setAttribute("homeMainLoc", LocationManager.getLocation(session).getProperty(LocationManager.HOME));
        session.setAttribute("bucketMainLoc", LocationManager.getLocation(session).getProperty(LocationManager.BUCKET));
        session.setAttribute("orderMainLoc", LocationManager.getLocation(session).getProperty(LocationManager.ORDER));
        session.setAttribute("logoutMainLoc", LocationManager.getLocation(session).getProperty(LocationManager.LOGOUT));
        session.setAttribute("countryFromLoc", LocationManager.getLocation(session).getProperty(LocationManager.COUNTRY_FROM));
        session.setAttribute("countryToLoc", LocationManager.getLocation(session).getProperty(LocationManager.COUNTRY_TO));
        session.setAttribute("categoryLoc", LocationManager.getLocation(session).getProperty(LocationManager.CATEGORY));
        session.setAttribute("dateLoc", LocationManager.getLocation(session).getProperty(LocationManager.DATE));
        session.setAttribute("findLoc", LocationManager.getLocation(session).getProperty(LocationManager.FIND));
        session.setAttribute("cruiseNameLoc", LocationManager.getLocation(session).getProperty(LocationManager.CRUISE_NAME));
        session.setAttribute("shipLoc", LocationManager.getLocation(session).getProperty(LocationManager.SHIP));
        session.setAttribute("cFromLoc", LocationManager.getLocation(session).getProperty(LocationManager.C_FROM));
        session.setAttribute("cToLoc", LocationManager.getLocation(session).getProperty(LocationManager.C_TO));
        session.setAttribute("startDateLoc", LocationManager.getLocation(session).getProperty(LocationManager.START_DATE));
        session.setAttribute("finishDateLoc", LocationManager.getLocation(session).getProperty(LocationManager.FINISH_DATE));
        session.setAttribute("portsLoc", LocationManager.getLocation(session).getProperty(LocationManager.PORTS));
        session.setAttribute("priceLoc", LocationManager.getLocation(session).getProperty(LocationManager.PRICE));
        session.setAttribute("addLoc", LocationManager.getLocation(session).getProperty(LocationManager.ADD));
        session.setAttribute("addBonusLoc", LocationManager.getLocation(session).getProperty(LocationManager.ADD_BONUS));
        session.setAttribute("userLoc", LocationManager.getLocation(session).getProperty(LocationManager.USER));
        session.setAttribute("excLoc", LocationManager.getLocation(session).getProperty(LocationManager.EXCURSION));
        session.setAttribute("bonusLoc", LocationManager.getLocation(session).getProperty(LocationManager.BONUS));
        session.setAttribute("ticketLoc", LocationManager.getLocation(session).getProperty(LocationManager.TICKET));
        session.setAttribute("buyNowLoc", LocationManager.getLocation(session).getProperty(LocationManager.BUY_NOW));
    }

    public static String getAttribute(HttpServletRequest request, String attribute) {
        return (String) request.getSession().getAttribute(attribute);
    }
}
