package servlet.commands;

import servlet.configuration.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCommand implements Command{
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_FORM);
    }

}