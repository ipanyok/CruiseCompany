package servlet.commands;

import entity.Country;
import entity.Cruise;
import services.CheckService;
import services.MainInfoService;
import servlet.Localization;
import servlet.configuration.ConfigurationManager;
import servlet.configuration.LocationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RegisterCommand implements Command {

    private static final String REGISTER_BUTTON = "registerBtn";
    private static final String USER_NAME_SET = "user";
    private static final String COUNTRY_FROM_SET = "countryFrom";
    private static final String COUNTRY_TO_SET = "countryTo";
    private static final String HIDDEN_SET = "isHidden";
    private static final String MESSAGE_SET = "messageRegister";
    private static final String LOGIN_SET = "login";
    private static final String PASS_SET = "pass";
    private static final String FIRST_SET = "first";
    private static final String MIDDLE_SET = "middle";
    private static final String LAST_SET = "last";
    private static final String LOGIN_GET = "loginRegister";
    private static final String PASSWORD_GET = "passwordRegister";
    private static final String FIRST_GET = "firstNameRegister";
    private static final String MIDDLE_GET = "middleNameRegister";
    private static final String LAST_GET = "lastNameRegister";

    /**
     * Actions in register page
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CheckService checkService = new CheckService();
        MainInfoService mainInfoService = new MainInfoService();
        String page = null;
        HttpSession session = request.getSession();
        if (request.getParameter(REGISTER_BUTTON) != null && request.getParameter(REGISTER_BUTTON).equals(Localization.getAttribute(request, "registerLoc"))) {
            String getRegisterResult = checkService.registerUser(request);
            if (getRegisterResult == null) {
                List<Country> country = mainInfoService.findAllCountries();
                session.setAttribute(USER_NAME_SET, request.getParameter(LOGIN_GET));
                session.setAttribute(COUNTRY_FROM_SET, country);
                session.setAttribute(COUNTRY_TO_SET, country);
                request.setAttribute(HIDDEN_SET, "hidden");
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_FORM);
            } else {
                session.setAttribute(MESSAGE_SET, LocationManager.getLocation(session).getProperty(getRegisterResult));
                request.setAttribute(LOGIN_SET, request.getParameter(LOGIN_GET));
                request.setAttribute(PASS_SET, request.getParameter(PASSWORD_GET));
                request.setAttribute(FIRST_SET, request.getParameter(FIRST_GET));
                request.setAttribute(MIDDLE_SET, request.getParameter(MIDDLE_GET));
                request.setAttribute(LAST_SET, request.getParameter(LAST_GET));
                page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.REGISTER_FORM);
            }
        }
        return page;
    }
}
