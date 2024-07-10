package edu.training.web.controller.concrete.impl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import edu.training.web.bean.News;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.NewsService;
import edu.training.web.service.exception.ServiceException;
import edu.training.web.service.ServiceProvider;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddNews implements Command {

	private static final Logger logger = Logger.getLogger(AddNews.class.getName());
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		String title = request.getParameter("title");
		String brief = request.getParameter("brief");
		String category = request.getParameter("category");
		String image = request.getParameter("image");
		String author = request.getParameter("author");
		String video = request.getParameter("video");

		News news = new News();
		news.setDate(date);
		news.setTitle(title);
		news.setBrief(brief);
		news.setCategory(category);
		news.setImage(image);
		news.setAuthorName(author);
		news.setVideo(video);

		try {
			newsService.addNews(news);
			logger.log(Level.INFO, "News added successfully, redirecting to main page.");
			response.sendRedirect("Controller?command=go_to_main_page&Info1=Successful addition");
		} catch (ServiceException e) {
			logger.log(Level.SEVERE, "Failed to add news", e);
			response.sendRedirect("Controller?command=go_to_main_page&Info1=Unsuccessful addition");
		}
	}
}