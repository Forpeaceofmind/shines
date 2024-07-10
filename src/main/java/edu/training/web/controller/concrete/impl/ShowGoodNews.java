package edu.training.web.controller.concrete.impl;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.training.web.bean.News;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.NewsService;
import edu.training.web.service.ServiceProvider;
import edu.training.web.service.exception.ServiceException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ShowGoodNews implements Command {
	private static final Logger logger = Logger.getLogger(ShowBusinessNews.class.getName());

	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<News> goodNews = newsService.getNewsByCategory("good"); 
			request.setAttribute("goodNews", goodNews);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/good_news.jsp");
			dispatcher.forward(request, response);
			

		} catch (ServiceException e) {
			logger.log(Level.SEVERE, "Failed to retrieve live news", e);

			throw new ServletException("Failed to retrieve live news", e);
		}
	}
}
