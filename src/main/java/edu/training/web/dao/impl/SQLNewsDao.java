package edu.training.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.training.web.bean.News;
import edu.training.web.dao.NewsDao;
import edu.training.web.jdbc.dao.ConnectionPool;
import edu.training.web.jdbc.dao.ConnectionPoolException;
import edu.training.web.dao.DaoException;

public class SQLNewsDao implements NewsDao {
	private final ConnectionPool conPool = ConnectionPool.getInstance();
	private static final String ADD_NEWS = "INSERT INTO news (id_news, date, title, brief,category, image,  author_name, video) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String EDIT_NEWS = "UPDATE news SET date = ?, title = ?, brief = ?, category = ?, image = ?, author_name = ?, video = ? WHERE id_news::bigint = ?";
	private static final String DELETE_NEWS = "DELETE FROM news WHERE id_news = ?";
	private static final String GET_ALL_NEWS = "SELECT * FROM news";
	private static final String GET_NEXT_ID_QUERY = "SELECT nextval('news_id_news_seq')";
	private static final Logger logger = Logger.getLogger(SQLNewsDao.class.getName());

	@Override
	public boolean addNews(News news) throws DaoException {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement getIdStmt = null;

		try {
			con = conPool.takeConnection();
			con.setAutoCommit(false);

			getIdStmt = con.prepareStatement(GET_NEXT_ID_QUERY);
			ResultSet rs = getIdStmt.executeQuery();
			if (rs.next()) {
				long nextId = rs.getLong(1);
				news.setIdNews(nextId);
			} else {
				throw new DaoException("Failed to generate news ID");
			}

			pstmt = con.prepareStatement(ADD_NEWS);
			
			pstmt.setString(2, news.getDate());
			pstmt.setString(3, news.getTitle());
			pstmt.setString(4, news.getBrief());
			pstmt.setString(5, news.getCategory());
			pstmt.setString(6, news.getImage());
			pstmt.setString(7, news.getAuthorName());
			pstmt.setString(8, news.getVideo());

			int affectedRows = pstmt.executeUpdate();
			con.commit();

			if (affectedRows > 0) {
				logger.log(Level.INFO, "News added successfully: {0}", news.getTitle());
				return true;
			} else {
				logger.log(Level.WARNING, "No rows affected, news not added: {0}", news.getTitle());
				return false;
			}
		} catch (SQLException | ConnectionPoolException e) {
			try {
				if (con != null) {
					con.rollback();
					logger.log(Level.WARNING, "Transaction rolled back due to an error", e);
				}
			} catch (SQLException ex) {
				logger.log(Level.SEVERE, "Failed to rollback transaction", ex);
				throw new DaoException("Failed to rollback transaction", ex);
			}
			logger.log(Level.SEVERE, "Failed to add news", e);
			throw new DaoException("Failed to add news", e);
		} finally {
			closeResources(null, getIdStmt, null);
			closeResources(null, pstmt, con);
		}
	}

	@Override
	public List<News> getNewsByCategory(String category) throws DaoException {
		List<News> newsList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = conPool.takeConnection();
			pstmt = con.prepareStatement("SELECT * FROM news WHERE category = ?");
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				News news = new News();
				news.setIdNews(rs.getLong("id_news"));
				news.setDate(rs.getString("date"));
				news.setTitle(rs.getString("title"));
				news.setBrief(rs.getString("brief"));
				news.setCategory(rs.getString("category"));
				news.setImage(rs.getString("image"));
				news.setAuthorName(rs.getString("author_name"));
				news.setVideo(rs.getString("video"));
				newsList.add(news);
			}
		} catch (SQLException | ConnectionPoolException e) {
			logger.log(Level.SEVERE, "Failed to fetch news by category: " + category, e);
			throw new DaoException("Failed to fetch news by category", e);
		} finally {
			closeResources(rs, pstmt, con);
		}

		return newsList;
	}

	@Override
	public boolean editNews(News news) throws DaoException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = conPool.takeConnection();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(EDIT_NEWS);

			pstmt.setString(1, news.getDate());
			pstmt.setString(2, news.getTitle());
            pstmt.setString(3, news.getBrief());
            pstmt.setString(4, news.getCategory());
            pstmt.setString(5, news.getImage());
            pstmt.setString(6, news.getAuthorName());
            pstmt.setString(7, news.getVideo());
            pstmt.setLong(8, news.getIdNews());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new DaoException("Editing news failed, no rows affected.");
            }

			con.commit();

			if (affectedRows > 0) {
				logger.log(Level.INFO, "News edited successfully: {0}", news.getTitle());
				return true;
			} else {
				logger.log(Level.WARNING, "No rows affected, news not edited: {0}", news.getTitle());
				return false;
			}
		} catch (SQLException | ConnectionPoolException e) {
			try {
				if (con != null) {
					con.rollback();
					logger.log(Level.WARNING, "Transaction rolled back due to an error", e);
				}
			} catch (SQLException ex) {
				logger.log(Level.SEVERE, "Failed to rollback transaction", ex);
				throw new DaoException("Failed to rollback transaction", ex);
			}
			logger.log(Level.SEVERE, "Failed to edit news", e);
			throw new DaoException("Failed to edit news", e);
		} finally {
			closeResources(null, pstmt, con);
		}
	}

	@Override
	public boolean deleteNews(long idNews) throws DaoException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = conPool.takeConnection();
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(DELETE_NEWS);
			pstmt.setLong(1, idNews);

			int affectedRows = pstmt.executeUpdate();
			con.commit();

			if (affectedRows > 0) {
				logger.log(Level.INFO, "News deleted successfully: {0}", idNews);
				return true;
			} else {
				logger.log(Level.WARNING, "No rows affected, news not deleted: {0}", idNews);
				return false;
			}
		} catch (SQLException | ConnectionPoolException e) {
			try {
				if (con != null) {
					con.rollback();
					logger.log(Level.WARNING, "Transaction rolled back due to an error", e);
				}
			} catch (SQLException ex) {
				logger.log(Level.SEVERE, "Failed to rollback transaction", ex);
				throw new DaoException("Failed to rollback transaction", ex);
			}
			logger.log(Level.SEVERE, "Failed to delete news", e);
			throw new DaoException("Failed to delete news", e);
		} finally {
			closeResources(null, pstmt, con);
		}
	}

	@Override
	public List<News> getAllNews() throws DaoException {
		List<News> newsList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = conPool.takeConnection();
			pstmt = con.prepareStatement(GET_ALL_NEWS);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				News news = new News();
				news.setIdNews(rs.getLong("id_news"));
				news.setDate(rs.getString("date"));
				news.setTitle(rs.getString("title"));
				news.setBrief(rs.getString("brief"));
				news.setCategory(rs.getString("category"));
				news.setImage(rs.getString("image"));
				news.setAuthorName(rs.getString("author_name"));
				news.setVideo(rs.getString("video"));
				newsList.add(news);
			}
		} catch (SQLException | ConnectionPoolException e) {
			logger.log(Level.SEVERE, "Failed to fetch all news", e);
			throw new DaoException("Failed to fetch all news", e);
		} finally {
			closeResources(rs, pstmt, con);
		}

		return newsList;
	}

	@Override
	public void closeResources(ResultSet rs, PreparedStatement pstmt, Connection con) throws DaoException {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Failed to close ResultSet", e);
				throw new DaoException(e);
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Failed to close PreparedStatement", e);
				throw new DaoException(e);
			}
		}
		if (con != null) {
			try {
				con.setAutoCommit(true);
				con.close();
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Failed to close Connection", e);
				throw new DaoException(e);
			}
		}
	}
}
