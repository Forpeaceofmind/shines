package edu.training.web.controller.concrete.impl;

import java.io.IOException;

import edu.training.web.controller.concrete.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoLogout implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = (HttpSession) request.getSession(false);

		if (session != null && session.getAttribute("user") != null) {
			session.removeAttribute("user");
		}
		response.sendRedirect("Controller?command=go_to_index_page&authError=You've been unlogged!");
	}
}
