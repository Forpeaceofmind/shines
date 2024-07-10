package edu.training.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import edu.training.web.bean.News;

public interface NewsDao {
	boolean addNews(News news) throws DaoException;

	public List<News> getNewsByCategory(String category) throws DaoException;

	boolean editNews(News news) throws DaoException;

	boolean deleteNews(long idNews) throws DaoException;

	void closeResources(ResultSet rs, PreparedStatement pstmt, Connection con) throws DaoException;

	List<News> getAllNews() throws DaoException;
}