package edu.training.web.jdbc.listener;

import edu.training.web.jdbc.dao.ConnectionPool;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {

			Class.forName("org.postgresql.Driver");

			ConnectionPool.getInstance();
			System.out.println("JDBC Driver loaded and Connection Pool initialized.");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Failed to load JDBC driver.", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ConnectionPool.getInstance().dispose();
		System.out.println("Connection Pool disposed.");
	}
}