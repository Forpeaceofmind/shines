package edu.training.web.controller.concrete.impl;

import java.io.IOException;
import java.util.Locale;

import edu.training.web.controller.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Localizations implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		 String lang = request.getParameter("lang");
	        HttpSession session = request.getSession();
	        if ("ru".equals(lang)) {
	            session.setAttribute("locale", new Locale("ru", "RU"));
	        } else {
	            session.setAttribute("locale", new Locale("en", "US"));
	        }

		

		String referer = request.getHeader("referer");

		if (referer != null && !referer.isEmpty()) {

			response.sendRedirect(referer);
		} else {

			response.sendRedirect("Controller?command=go_to_index_page&locError=Localization failed");
		}
	}
}