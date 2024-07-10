package edu.training.web.service.impl;

import edu.training.web.bean.User;
import edu.training.web.bean.UserRegInfo;
import edu.training.web.dao.AuthDao;
import edu.training.web.dao.UserRegDao;
import edu.training.web.dao.DaoException;
import edu.training.web.dao.DaoProvider;
import edu.training.web.service.UserService;
import edu.training.web.service.exception.ServiceAuthException;
import edu.training.web.service.exception.ServiceException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements UserService {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());
	private final AuthDao authDao = DaoProvider.getInstance().getAuthDao();
	private final UserRegDao regDao = DaoProvider.getInstance().getRegDao();

	@Override
	public User signIn(User authInfo) throws ServiceAuthException, ServiceException {
		try {
			logger.info("Starting sign in process for: " + authInfo.getLogin());
			Valid validator = new Valid.Builder(regDao).validateEmail(authInfo.getLogin())
					.validatePassword(authInfo.getPassword()).build();

			if (!validator.isValid()) {
				logger.warning("Invalid login or password format for: " + authInfo.getLogin());
				throw new ServiceAuthException("Invalid login or password format");
			}

			User user = authDao.signIn(authInfo);
			if (user != null) {

				logger.info("User signed in successfully: " + authInfo.getLogin());
				return user;
			} else {
				logger.warning("Wrong login or password for: " + authInfo.getLogin());
				throw new ServiceAuthException("WRONG Login or Password");
			}
		} catch (DaoException e) {
			logger.log(Level.SEVERE, "Failed to sign in user: " + authInfo.getLogin(), e);
			throw new ServiceException("Failed to sign in", e);
		}
	}

	@Override
	public boolean registration(UserRegInfo regInfo) throws ServiceException {
		try {
			logger.info("Starting registration process for: " + regInfo.getLogin());
			Valid validator = new Valid.Builder(regDao).validateEmail(regInfo.getLogin())
					.validatePassword(regInfo.getPassword()).validateName(regInfo.getName())
					.validateBirthDate(regInfo.getBirthDate()).validateCity(regInfo.getResidence())
					.validateLoginUnique(regInfo.getLogin()).build();

			if (!validator.isValid()) {
				logger.warning("Registration data validation failed for: " + regInfo.getLogin());
				throw new ServiceException("Registration data validation failed");
			}

			logger.info("Validation successful for: " + regInfo.getLogin());
			boolean result = regDao.signUp(regInfo);
			if (result) {
				logger.info("User registered successfully: " + regInfo.getLogin());
			} else {
				logger.warning("Failed to register user: " + regInfo.getLogin());
			}
			return result;
		} catch (DaoException e) {
			logger.log(Level.SEVERE, "Failed to register user: " + regInfo.getLogin(), e);
			throw new ServiceException("Failed to register user", e);
		}
	}

	@Override
    public User rememberMe(String token) throws ServiceException {
        try {
            logger.info("Checking token: " + token);
            return authDao.checkToken(token); 
        } catch (DaoException e) {
            logger.log(Level.SEVERE, "Failed to remember user by token: " + token, e);
            throw new ServiceException("Failed to remember user by token", e);
        }
    }

	@Override
	public boolean blockUser(int id) throws ServiceException {
		throw new UnsupportedOperationException("Method not implemented yet");
	}
}