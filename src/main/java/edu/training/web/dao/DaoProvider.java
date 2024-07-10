package edu.training.web.dao;

import edu.training.web.dao.impl.SQLAuthDao;
import edu.training.web.dao.impl.SQLNewsDao;
import edu.training.web.dao.impl.SQLRegDao;

public class DaoProvider {

	private static final DaoProvider instance = new DaoProvider();

	private AuthDao authDao = new SQLAuthDao();
	private UserRegDao regDao = new SQLRegDao();
	private NewsDao newsDao = new SQLNewsDao();

	private DaoProvider() {
	}

	public AuthDao getAuthDao() {
		return authDao;
	}

	public UserRegDao getRegDao() {
		return regDao;
	}
	public NewsDao getNewsDao() {
		return  newsDao;
	}

	public static DaoProvider getInstance() {
		return instance;
	}
	

}
