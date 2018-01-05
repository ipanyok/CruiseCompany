package servlet;

import servlet.commands.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AppServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AppServlet.class.getName());
    private RequestHelper requestHelper = RequestHelper.getInstance();

    public AppServlet() {
        super();
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession();
        if (session.getAttribute("LOCALE") == null) {
            session.setAttribute("LOCALE", "EN"); // by default
        }
        Localization.setLocation(session);
        try {
            Command command = requestHelper.getCommand(request);
            page = command.execute(request, response);
        } catch (ServletException e) {
            logger.log(Level.SEVERE, "Servlet Exception");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException");
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}


