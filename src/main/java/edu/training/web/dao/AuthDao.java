package edu.training.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.training.web.bean.User;

public interface AuthDao {
	User checkToken(String token) throws DaoException;

	User signIn(User authInfo) throws DaoException;

	void closeResources(ResultSet rs, PreparedStatement pstmt, Connection con) throws DaoException;

	void saveToken(long userId, String token) throws DaoException;

}
