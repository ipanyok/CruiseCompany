package servlet.commands;

import services.BucketService;
import servlet.Localization;
import servlet.configuration.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class WelcomePageCommand implements Command {

    private static final String LOGIN_BUTTON = "loginBtn";
    private static final String REGISTER_BUTTON = "registerBtn";
    private static final String LOGOUT_BUTTON = "logoutBtn";
    private static final String LOCALE = "LOCALE";

    /**
     * Actions in welcome page
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        BucketService bucketService = new BucketService();
        HttpSession session = request.getSession();
        if (request.getParameter(LOGIN_BUTTON) != null && request.getParameter(LOGIN_BUTTON).equals("LOG IN")) {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_FORM);
        }
        if (request.getParameter(REGISTER_BUTTON) != null && request.getParameter(REGISTER_BUTTON).equals("REGISTER")) {
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.REGISTER_FORM);
        }
        if (request.getParameter(LOGOUT_BUTTON) != null && request.getParameter(LOGOUT_BUTTON).equals("logout")) {
            if (!session.getAttribute("user").equals("Admin")) {
                bucketService.deleteBucketOfUser((String) session.getAttribute("user"));
            }
            session.invalidate();
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_FORM);
        }
        if (request.getParameter("enLocBtn") != null && request.getParameter("enLocBtn").equals("english")) {
            session.setAttribute(LOCALE, "EN");
            Localization.setLocation(session);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_FORM);
        }
        if (request.getParameter("uaLocBtn") != null && request.getParameter("uaLocBtn").equals("ukraine")) {
            session.setAttribute(LOCALE, "UA");
            Localization.setLocation(session);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.INDEX_FORM);
        }
        return page;
    }
}
