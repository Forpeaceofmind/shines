package edu.training.web.controller.filter;

import java.io.IOException;

import edu.training.web.bean.User;
import edu.training.web.service.ServiceProvider;
import edu.training.web.service.UserService;
import edu.training.web.service.exception.ServiceException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RememberMeFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	private final UserService userService = ServiceProvider.getInstance().getUserService();

	public RememberMeFilter() {
		super();
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			HttpSession session = ((HttpServletRequest) request).getSession(false);

			if (session == null || session.getAttribute("user") == null) {
				Cookie[] cookies = ((HttpServletRequest) request).getCookies();

				if (cookies != null) {
					for (Cookie c : cookies) {
						if (c.getName().equals("remember-me")) {
							String token = c.getValue();
							User user = userService.rememberMe(token);

							session.setAttribute("user", user);
						}
					}
				}
			}
			chain.doFilter(request, response);
		} catch (ServiceException e) {
			((HttpServletResponse) response)
					.sendRedirect("Controller?command=go_to_index_page&authError=Wrong login or password!");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}