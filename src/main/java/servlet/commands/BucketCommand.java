package servlet.commands;

import services.OrderService;
import servlet.Localization;
import servlet.configuration.ConfigurationManager;
import servlet.configuration.LocationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BucketCommand implements Command {

    private static final String ORDER_BUTTON = "buyNow";
    private static final String USER_NAME_SET = "user";
    private static final String BUCKET_ID_GET = "idBucketBtn";
    private static final String EXCURSIONS_GET = "excursionList";
    private static final String HIDDEN_SET = "isHidden";
    private static final String MESSAGE_SET_ORDER = "messageOrder";
    private static final String MESSAGE_SET_BUCKET = "messageBucket";

    /**
     * Actions in bucket page
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        OrderService orderService = new OrderService();
        HttpSession session = request.getSession();
        if (request.getParameter(ORDER_BUTTON) != null && request.getParameter(ORDER_BUTTON).startsWith(Localization.getAttribute(request, "buyNowLoc"))) {
            int cruiseID = Integer.parseInt(request.getParameter(ORDER_BUTTON).substring(Localization.getAttribute(request, "buyNowLoc").length()));
            String userLogin = (String) session.getAttribute(USER_NAME_SET);
            int bucketID = Integer.parseInt(request.getParameter(BUCKET_ID_GET + cruiseID));
            String excursion = request.getParameter(EXCURSIONS_GET + cruiseID);
            double priceExcursion = 0.0;
            if (excursion != null && !excursion.equals("")) {
                priceExcursion = Double.parseDouble(excursion.substring(excursion.lastIndexOf(" ")));
            }
            String result = orderService.makeOrder(userLogin, cruiseID, bucketID, priceExcursion, excursion);
            request.setAttribute(HIDDEN_SET, "hidden");
            if (result != null) {
                session.setAttribute(MESSAGE_SET_ORDER, LocationManager.getLocation(session).getProperty(result));
                session.setAttribute(MESSAGE_SET_BUCKET, "");
            } else {
                session.setAttribute(MESSAGE_SET_ORDER, "Something wrong!!!");
            }
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_FORM);
        }
        return page;
    }
}
