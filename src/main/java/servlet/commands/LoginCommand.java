package servlet.commands;

import entity.Country;
import entity.Order;
import services.AdminService;
import services.CheckService;
import services.MainInfoService;
import services.beans.OrdersInfoView;
import servlet.Localization;
import servlet.configuration.ConfigurationManager;
import servlet.configuration.LocationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LoginCommand implements Command {

    private static final String LOGIN_BUTTON = "loginBtn";
    private static final String LOGIN = "loginUser";
    private static final String PASSWORD = "passwordUser";
    private static final String USER_NAME_SET = "user";
    private static final String COUNTRY_FROM_SET = "countryFrom";
    private static final String COUNTRY_TO_SET = "countryTo";
    private static final String HIDDEN_SET = "isHidden";
    private static final String ORDERS_SET = "orders";
    private static final String MESSAGE_SET = "message";
    private static final String LOGIN_SET = "login";
    private static final String PASS_SET = "pass";

    /**
     * Actions in login page
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckService checkService = new CheckService();
        MainInfoService mainInfoService = new MainInfoService();
        AdminService adminService = new AdminService();
        String page = null;
        HttpSession session = request.getSession();
        if (request.getParameter(LOGIN_BUTTON) != null && request.getParameter(LOGIN_BUTTON).equals(Localization.getAttribute(request, "lLoc"))) {
            String getCheckMessage = checkService.checkUser(request.getParameter(LOGIN), request.getParameter(PASSWORD));
            if (getCheckMessage == null) {
                List<Country> country = mainInfoService.findAllCountries();
                session.setAttribute(USER_NAME_SET, request.getParameter(LOGIN));
                session.setAttribute(COUNTRY_FROM_SET, country);
                session.setAttribute(COUNTRY_TO_SET, country);
                request.setAttribute(HIDDEN_SET, "hidden");
                if (request.getParameter(LOGIN).equals("Admin")) {
                    List<OrdersInfoView> listOrders = adminService.findAllOrders();
                    request.setAttribute(ORDERS_SET, listOrders);
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_FORM);
                } else {
                    page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_FORM);
                }
            } else {
                session.setAttribute(MESSAGE_SET, LocationManager.getLocation(session).getProperty(getCheckMessage));
                request.setAttribute(LOGIN_SET, request.getParameter(LOGIN));
                request.setAttribute(PASS_SET, request.getParameter(PASSWORD));
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_FORM);
            }
        }
        return page;
    }
}
