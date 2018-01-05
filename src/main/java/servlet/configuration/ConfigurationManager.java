package servlet.configuration;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;

    public static final String LOGIN_FORM = "LOGIN_FORM";
    public static final String REGISTER_FORM = "REGISTER_FORM";
    public static final String MAIN_FORM = "MAIN_FORM";
    public static final String INDEX_FORM = "INDEX_FORM";
    public static final String ERROR_FORM = "ERROR_FORM";
    public static final String BUCKET_FORM = "BUCKET_FORM";
    public static final String ORDER_FORM = "ORDER_FORM";
    public static final String ADMIN_FORM = "ADMIN_FORM";

    public static ConfigurationManager getInstance(){
        if (instance == null){
            instance = new ConfigurationManager();
            instance.resourceBundle = ResourceBundle.getBundle("configuration");
        }
        return instance;
    }
    public String getProperty(String key){
        return (String)resourceBundle.getObject(key);
    }
}
