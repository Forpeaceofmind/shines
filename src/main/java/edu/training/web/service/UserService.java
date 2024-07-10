package edu.training.web.service;

import edu.training.web.bean.User;
import edu.training.web.bean.UserRegInfo;
import edu.training.web.service.exception.ServiceAuthException;
import edu.training.web.service.exception.ServiceException;

public interface UserService {

	User signIn(User user) throws ServiceAuthException, ServiceException;

	boolean registration(UserRegInfo regInfo) throws ServiceException;

	boolean blockUser(int id) throws ServiceException;

	User rememberMe(String token) throws ServiceException;

}
