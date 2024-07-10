package edu.training.web.controller.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import edu.training.web.bean.User;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleFilter implements Filter {

    private static final Logger logger = Logger.getLogger(RoleFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String command = httpRequest.getParameter("command");

        if (isProtectedCommand(command) && (session == null || session.getAttribute("user") == null)) {
            logger.log(Level.INFO, "No user session found, redirecting to login page.");
            httpResponse.sendRedirect("Controller?command=go_to_index_page&authError=Please log in!");
            return;
        }

        if (isProtectedCommand(command)) {
            User user = (User) session.getAttribute("user");
            String role = user.getRole();

            // Existing checks
            if ("add_news".equals(command) && !("admin".equals(role) || "author".equals(role))) {
                logger.log(Level.INFO, "User does not have permission to add news, redirecting to index page.");
                httpResponse.sendRedirect(
                        "Controller?command=go_to_index_page&authError=You don't have permission to add news!");
                return;
            }

            if ("edit_news".equals(command) && !("admin".equals(role) || "author".equals(role))) {
                logger.log(Level.INFO, "User does not have permission to edit news, redirecting to index page.");
                httpResponse.sendRedirect(
                        "Controller?command=go_to_index_page&authError=You don't have permission to edit news!");
                return;
            }

            if ("delete_news".equals(command) && !("admin".equals(role) || "author".equals(role))) {
                logger.log(Level.INFO, "User does not have permission to delete news, redirecting to index page.");
                httpResponse.sendRedirect(
                        "Controller?command=go_to_index_page&authError=You don't have permission to delete news!");
                return;
            }

            if ("update_news".equals(command) && !("admin".equals(role) || "author".equals(role))) {
                logger.log(Level.INFO, "User does not have permission to update news, redirecting to index page.");
                httpResponse.sendRedirect(
                        "Controller?command=go_to_index_page&authError=You don't have permission to update news!");
                return;
            }

            // Additional checks for do_edit and go_to_adding_news_page
            if (("do_edit".equals(command) || "go_to_adding_news_page".equals(command)) && !"admin".equals(role)) {
                logger.log(Level.INFO, "Only admin users can perform this action, redirecting to index page.");
                httpResponse.sendRedirect(
                        "Controller?command=go_to_index_page&authError=Only admin users can perform this action!");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isProtectedCommand(String command) {
        return "add_news".equals(command) || "edit_news".equals(command) || "delete_news".equals(command) || "update_news".equals(command);
    }

    @Override
    public void destroy() {

    }
}