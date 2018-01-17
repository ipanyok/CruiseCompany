package servlet;

import servlet.commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class RequestHelper {

    private static RequestHelper instance = null;

    HashMap<String, Command> commands = new HashMap<>();

    private RequestHelper() {
        commands.put("login", new WelcomePageCommand());        // button LOG IN, index.jsp
        commands.put("register", new WelcomePageCommand());     // button REGISTER, index.jsp
        commands.put("loginButton", new LoginCommand());        // button LOGIN, login.jsp
        commands.put("registerButton", new RegisterCommand());  // button REGISTER, register.jsp
        commands.put("findButton", new MainCommand());          // button FIND, main.jsp
        commands.put("addButton", new MainCommand());           // button ADD, main.jsp
        commands.put("bucketButton", new MainCommand());        // button BUCKET, main.jsp
        commands.put("homeButton", new MainCommand());          // button HOME, main.jsp
        commands.put("ordButton", new BucketCommand());         // button BUY NOW, bucket.jsp
        commands.put("orderButton", new MainCommand());         // button ORDER, main.jsp
        commands.put("homeButtonAdmin", new AdminCommand());    // button HOME, admin.jsp
        commands.put("addBonus", new AdminCommand());           // button ADD BONUS, admin.jsp
        commands.put("logoutButton", new WelcomePageCommand()); // button LOG OUT, main.jsp
        commands.put("enLocation", new WelcomePageCommand());
        commands.put("uaLocation", new WelcomePageCommand());
        commands.put("enMainLocation", new MainCommand());
        commands.put("uaMainLocation", new MainCommand());
        commands.put("enAdminLocation", new AdminCommand());
        commands.put("uaAdminLocation", new AdminCommand());
        commands.put("page", new MainCommand());                // pages on main.jsp (pagination)
    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        Command command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    public static RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
    }
}
