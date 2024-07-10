package edu.training.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import edu.training.web.bean.User;
import edu.training.web.dao.AuthDao;
import edu.training.web.dao.DaoException;
import edu.training.web.jdbc.dao.ConnectionPool;
import edu.training.web.jdbc.dao.ConnectionPoolException;

public class SQLAuthDao implements AuthDao {
	private final ConnectionPool conPool = ConnectionPool.getInstance();
	private static final String CHECK_USER = "SELECT login, password, role FROM user_access_data WHERE login = ? AND password = ?";
	private static final String SAVE_TOKEN_SQL = "INSERT INTO tokens (user_id, token, expiration_date) VALUES (?, ?, ?)";
	private static final String CHECK_TOKEN_SQL = "SELECT u.* FROM users u JOIN tokens t ON u.id = t.user_id WHERE t.token = ? AND t.expiration_date >= NOW()";
	ResultSet rs = null;
	Connection con = null;
	PreparedStatement pstmt = null;

	@Override
	public User signIn(User info) throws DaoException {

		String login = info.getLogin();
		String password = info.getPassword();

		try {
			con = conPool.takeConnection();
			pstmt = con.prepareStatement(CHECK_USER);
			pstmt.setString(1, login);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				String role = rs.getString("role");
				return new User(login, password, role);
			} else {
				throw new DaoException("Wrong login or password");
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		} finally {
			closeResources(rs, pstmt, con);
		}
	}

	public void saveToken(long userId, String token) throws DaoException { 

		try {
			con = conPool.takeConnection();
			pstmt = con.prepareStatement(SAVE_TOKEN_SQL);
			pstmt.setLong(1, userId);
			pstmt.setString(2, token);
			pstmt.setObject(3, LocalDateTime.now().plusDays(30));
			pstmt.executeUpdate();
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException("Failed to save token", e);
		} finally {
			closeResources(null, pstmt, con);
		}
	}

	@Override
	public User checkToken(String token) throws DaoException {
		try {
			con = conPool.takeConnection();
			pstmt = con.prepareStatement(CHECK_TOKEN_SQL);
			pstmt.setString(1, token);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String login = rs.getString("login");
				String password = rs.getString("password");
				String role = rs.getString("role");
				return new User(login, password, role);
			} else {
				throw new DaoException("token expired");
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException("Failed to check token", e);
		} finally {
			closeResources(rs, pstmt, con);
		}
	}

	public void closeResources(ResultSet rs, PreparedStatement pstmt, Connection con) throws DaoException {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}
}