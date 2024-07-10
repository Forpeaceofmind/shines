package edu.training.web.controller.concrete.impl;

import java.io.IOException;

import edu.training.web.controller.concrete.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToMainPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = (HttpSession) request.getSession(false);

		if (session != null && session.getAttribute("user") != null) {
			request.setAttribute("invitationMessage", "Hello, user!");

			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		} else {

			response.sendRedirect(
					"Controller?command=go_to_index_page&authError=You cannot perform this action. Please log in!");
		}

	}

}
