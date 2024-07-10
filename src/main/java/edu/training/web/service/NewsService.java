
package edu.training.web.service;

import edu.training.web.bean.News;
import edu.training.web.service.exception.ServiceException;
import java.util.List;

public interface NewsService {
	void addNews(News news) throws ServiceException;

	List<News> getNewsByCategory(String category) throws ServiceException;

	void editNews(News news) throws ServiceException;

	void deleteNews(long idNews) throws ServiceException;

	List<News> getAllNews() throws ServiceException; 
}