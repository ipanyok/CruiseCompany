package servlet.commands;

import services.BucketService;
import services.MainInfoService;
import services.OrderService;
import services.beans.BucketInfoView;
import services.beans.CruiseInfoView;
import services.beans.PagesBean;
import servlet.Localization;
import servlet.configuration.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainCommand implements Command {

    private static final String FIND_BUTTON = "findBtn";
    private static final String ADD_BUTTON = "addBtn";
    private static final String BUCKET_BUTTON = "bucketBtn";
    private static final String HOME_BUTTON = "homeBtn";
    private static final String ORDER_BUTTON = "orderBtn";
    private static final String COUNTRY_FROM_GET = "countryFromList";
    private static final String COUNTRY_TO_GET = "countryToList";
    private static final String CATEGORY_GET = "category";
    private static final String DATE_GET = "date";
    private static final String USER_NAME_SET = "user";
    private static final String CRUISES_SET = "cruises";
    private static final String DATE_SET = "dateValue";
    private static final String HIDDEN_SET = "isHidden";
    private static final String MESSAGE_SET = "message";
    private static final String BUCKETS_SET = "buckets";
    private static final String ORDERS_SET = "orders";
    private static final String LOCALE = "LOCALE";

    private final int paginationTotal = 5;

    /**
     * Actions in main page
     * */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        MainInfoService mainInfoService = new MainInfoService();
        BucketService bucketService = new BucketService();
        OrderService orderService = new OrderService();
        HttpSession session = request.getSession();
        if (request.getParameter(FIND_BUTTON) != null && request.getParameter(FIND_BUTTON).equals(Localization.getAttribute(request, "findLoc"))) {
            String countryFrom = request.getParameter(COUNTRY_FROM_GET);
            String countryTo = request.getParameter(COUNTRY_TO_GET);
            String category = request.getParameter(CATEGORY_GET);
            String date = request.getParameter(DATE_GET);
            List<CruiseInfoView> cruises = mainInfoService.findAllCruises(countryFrom, countryTo, category, date);
            session.setAttribute("findAllCruises", cruises);
            request.setAttribute(DATE_SET, request.getParameter(DATE_GET));
            request.setAttribute(HIDDEN_SET, "visible");

            // pagination
            List<PagesBean> pagesList = pagesCount((List<CruiseInfoView>) session.getAttribute("findAllCruises"));
            session.setAttribute("countPages", pagesList);
            List<CruiseInfoView> cruiseInfoViewsList = mainInfoService.printResult((List<CruiseInfoView>) session.getAttribute("findAllCruises"), 0, paginationTotal);
            request.setAttribute(CRUISES_SET, cruiseInfoViewsList);
            //

            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_FORM);
        }

        if (request.getParameter("page") != null) {
            int pageNumber = Integer.parseInt(request.getParameter("page"));
            List<CruiseInfoView> cruiseInfoViewsList = mainInfoService.printResult((List<CruiseInfoView>) session.getAttribute("findAllCruises"), pageNumber*paginationTotal - paginationTotal , paginationTotal);
            request.setAttribute(CRUISES_SET, cruiseInfoViewsList);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_FORM);
        }

        if (request.getParameter(ADD_BUTTON) != null && request.getParameter(ADD_BUTTON).startsWith("ADD")) {
            int cruiseID = Integer.parseInt(request.getParameter(ADD_BUTTON).substring(3));
            String addingResult = bucketService.addBucketToUser((String) session.getAttribute(USER_NAME_SET), cruiseID);
            request.setAttribute(HIDDEN_SET, "hidden");
            if (addingResult != null) {
                request.setAttribute(MESSAGE_SET, addingResult);
            } else {
                request.setAttribute(MESSAGE_SET, "Something wrong!!!");
            }
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_FORM);
        }
        if (request.getParameter(BUCKET_BUTTON) != null && request.getParameter(BUCKET_BUTTON).equals("bucket")) {
            List<BucketInfoView> bucket = bucketService.findBucketsOfUser((String) session.getAttribute(USER_NAME_SET));
            request.setAttribute(BUCKETS_SET, bucket);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.BUCKET_FORM);
        }

        if (request.getParameter(HOME_BUTTON) != null && request.getParameter(HOME_BUTTON).equals("home")) {
            request.setAttribute(DATE_SET, request.getParameter(DATE_GET));
            request.setAttribute(HIDDEN_SET, "hidden");
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_FORM);
        }
        if (request.getParameter(ORDER_BUTTON) != null && request.getParameter(ORDER_BUTTON).equals("order")) {
            List<BucketInfoView> orders = orderService.findOrdersOfUser((String) session.getAttribute(USER_NAME_SET));
            request.setAttribute(ORDERS_SET, orders);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ORDER_FORM);
        }

        if (request.getParameter("enMainLocBtn") != null && request.getParameter("enMainLocBtn").equals("english")) {
            session.setAttribute(LOCALE, "EN");
            Localization.setLocation(session);
            request.setAttribute(HIDDEN_SET, "hidden");
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_FORM);
        }
        if (request.getParameter("uaMainLocBtn") != null && request.getParameter("uaMainLocBtn").equals("ukraine")) {
            session.setAttribute(LOCALE, "UA");
            Localization.setLocation(session);
            request.setAttribute(HIDDEN_SET, "hidden");
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_FORM);
        }
        return page;
    }

    private List<PagesBean> pagesCount(List<CruiseInfoView> cruises) {
        int count = 0;
        int result;
        for(CruiseInfoView elem : cruises) {
            count++;
        }
        if (count % paginationTotal > 0) {
            result = count/paginationTotal + 1;
        }else {
            result = count/paginationTotal;
        }
        List<PagesBean> pagesList = new ArrayList<>();
        for (int i = 1; i <= result; i++) {
            pagesList.add(new PagesBean(i));
        }
        return pagesList;
    }
}
