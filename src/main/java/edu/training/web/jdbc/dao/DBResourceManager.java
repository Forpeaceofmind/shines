
package edu.training.web.jdbc.dao;

import java.util.ResourceBundle;
import java.util.MissingResourceException;

public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();

    ResourceBundle jdbcProperties;

    private DBResourceManager() {
        try {
            jdbcProperties = ResourceBundle.getBundle("db");
        } catch (MissingResourceException e) {
            throw new RuntimeException("Cannot find resource bundle: resources.db", e);
        }
    }

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return jdbcProperties.getString(key);
    }
}
