package edu.training.web.service.impl;

import java.util.List;

import edu.training.web.bean.News;
import edu.training.web.dao.NewsDao;
import edu.training.web.dao.DaoException;
import edu.training.web.dao.impl.SQLNewsDao;
import edu.training.web.service.NewsService;
import edu.training.web.service.exception.ServiceException;

public class NewsServiceImpl implements NewsService {

    private NewsDao newsDao = new SQLNewsDao(); 

    @Override
    public void addNews(News news) throws ServiceException {
        try {
            newsDao.addNews(news);
        } catch (DaoException e) {
            throw new ServiceException("Failed to add news", e);
        }
    }

    @Override
    public List<News> getNewsByCategory(String category) throws ServiceException {
        try {
            return newsDao.getNewsByCategory(category);
        } catch (DaoException e) {
            throw new ServiceException("Failed to get news by category", e);
        }
    }

    @Override
    public void editNews(News news) throws ServiceException {
        try {
            newsDao.editNews(news);
        } catch (DaoException e) {
            throw new ServiceException("Failed to edit news", e);
        }
    }

    @Override
    public void deleteNews(long idNews) throws ServiceException {
        try {
            newsDao.deleteNews(idNews);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete news", e);
        }
    }

    @Override
    public List<News> getAllNews() throws ServiceException {
        try {
            return newsDao.getAllNews();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get all news", e);
        }
    }
}