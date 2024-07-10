package edu.training.web.dao;



import edu.training.web.bean.UserRegInfo;

public interface UserRegDao {

	boolean isLoginExists(String login) throws DaoException;

	boolean signUp(UserRegInfo info) throws DaoException;

}
