package edu.training.web.controller.concrete.impl;

import java.io.IOException;

import edu.training.web.bean.UserRegInfo;
import edu.training.web.controller.concrete.Command;
import edu.training.web.service.ServiceProvider;
import edu.training.web.service.UserService;
import edu.training.web.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String login = request.getParameter("login");
	    String password = request.getParameter("password");
	    String name = request.getParameter("name");
	    String birthDate = request.getParameter("birthDate");
	    String residence = request.getParameter("residence");

	    UserRegInfo userRegInfo = new UserRegInfo();
	    userRegInfo.setLogin(login);
	    userRegInfo.setPassword(password);
	    userRegInfo.setName(name);
	    userRegInfo.setBirthDate(birthDate);
	    userRegInfo.setResidence(residence);

	    try {
	        if (userService.registration(userRegInfo)) {
	            response.sendRedirect("Controller?command=go_to_index_page&Info=Success registration");
	        } else {
	            response.sendRedirect("Controller?command=go_to_index_page&authError=User already exists or another error occurred!");
	        }
	    } catch (ServiceException e) {
	        response.sendRedirect("Controller?command=go_to_index_page&authError=Registration failed: " + e.getMessage());
	    }
	}

}