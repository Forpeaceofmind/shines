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

public class DoEdit implements Command {

	private static final Logger logger = Logger.getLogger(DoEdit.class.getName());
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String idNews = request.getParameter("idNews"); 
			String date = request.getParameter("date");
			String title = request.getParameter("title");
			String brief = request.getParameter("brief");
			String category = request.getParameter("category");
			String image = request.getParameter("image");
			String author = request.getParameter("author");
			String video = request.getParameter("video");

			News updatedNews = new News();

			updatedNews.setIdNews(Long.parseLong(idNews)); 
			updatedNews.setDate(date);
			updatedNews.setTitle(title);
			updatedNews.setBrief(brief);
			updatedNews.setCategory(category);
			updatedNews.setImage(image);
			updatedNews.setAuthorName(author);
			updatedNews.setVideo(video);

			newsService.editNews(updatedNews);

			response.sendRedirect("Controller?command=go_to_index_page&Info=Success update");

		} catch (NumberFormatException | ServiceException e) {
			logger.log(Level.SEVERE, "Failed to update news", e);
			response.sendRedirect("Controller?command=go_to_error_page");
		}

	}
}