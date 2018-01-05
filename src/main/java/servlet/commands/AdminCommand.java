package servlet.commands;

import services.AdminService;
import services.beans.OrdersInfoView;
import servlet.Localization;
import servlet.configuration.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminCommand implements Command {

    private static final String HOME_BUTTON = "homeBtnAdmin";
    private static final String ADD_BONUS_BUTTON = "addBonusBtn";
    private static final String ORDERS_SET = "orders";
    private static final String BONUS_GET = "bonus";
    private static final String MESSAGE_SET = "messageUpdate";
    private static final String LOCALE = "LOCALE";

    /**
     * Actions in admin page
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession();
        if (request.getParameter(HOME_BUTTON) != null && request.getParameter(HOME_BUTTON).equals("homeAdmin")) {
            page = redirect(request);
        }
        if (request.getParameter(ADD_BONUS_BUTTON) != null && request.getParameter(ADD_BONUS_BUTTON).startsWith(Localization.getAttribute(request, "addBonusLoc"))) {
            int orderID = Integer.parseInt(request.getParameter(ADD_BONUS_BUTTON).substring(request.getParameter(ADD_BONUS_BUTTON).lastIndexOf(":") + 1));
            String value = request.getParameter(BONUS_GET + orderID);
            AdminService adminService = new AdminService();
            String resultMessage = adminService.changeBonus(orderID, value);
            if (resultMessage != null) {
                request.setAttribute(MESSAGE_SET, resultMessage);
            } else {
                request.setAttribute(MESSAGE_SET, "ERROR! NO UPDATE!!!");
            }
            page = redirect(request);
        }

        if (request.getParameter("enAdminLocBtn") != null && request.getParameter("enAdminLocBtn").equals("english")) {
            session.setAttribute(LOCALE, "EN");
            Localization.setLocation(session);
            page = redirect(request);
        }
        if (request.getParameter("uaAdminLocBtn") != null && request.getParameter("uaAdminLocBtn").equals("ukraine")) {
            session.setAttribute(LOCALE, "UA");
            Localization.setLocation(session);
            page = redirect(request);
        }
        return page;
    }

    private String redirect(HttpServletRequest request) {
        AdminService adminService = new AdminService();
        List<OrdersInfoView> listOrders = adminService.findAllOrders();
        request.setAttribute(ORDERS_SET, listOrders);
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ADMIN_FORM);
    }
}
