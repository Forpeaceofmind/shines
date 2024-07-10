package edu.training.web.service.impl;

import edu.training.web.dao.DaoException;
import edu.training.web.dao.UserRegDao;

import java.util.regex.Pattern;

public class Valid {

	private static final Pattern LOGIN_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");

	private final boolean isLoginValid;
	private final boolean isPasswordValid;
	private final boolean isNameValid;
	private final boolean isBirthDateValid;
	private final boolean isCityValid;
	private final boolean isLoginUnique;

	private Valid(Builder builder) {
		this.isLoginValid = builder.isLoginValid;
		this.isPasswordValid = builder.isPasswordValid;
		this.isNameValid = builder.isNameValid;
		this.isBirthDateValid = builder.isBirthDateValid;
		this.isCityValid = builder.isCityValid;
		this.isLoginUnique = builder.isLoginUnique;
	}

	public static class Builder {
		private boolean isLoginValid = true;
		private boolean isPasswordValid = true;
		private boolean isNameValid = true;
		private boolean isBirthDateValid = true;
		private boolean isCityValid = true;
		private boolean isLoginUnique = true;
		private UserRegDao regDao;

		public Builder(UserRegDao regDao) {
			this.regDao = regDao;
		}

		public Builder validateEmail(String login) {
			this.isLoginValid = LOGIN_PATTERN.matcher(login).matches();
			System.out.println("Email validation: " + this.isLoginValid);
			return this;
		}

		public Builder validatePassword(String password) {
			this.isPasswordValid = PASSWORD_PATTERN.matcher(password).matches();
			System.out.println("Password validation: " + this.isPasswordValid);
			return this;
		}

		public Builder validateName(String name) {
			this.isNameValid = (name != null && !name.isEmpty());
			System.out.println("Name validation: " + this.isNameValid);
			return this;
		}

		public Builder validateBirthDate(String birthDate) {
			this.isBirthDateValid = (birthDate != null && !birthDate.isEmpty());
			System.out.println("BirthDate validation: " + this.isBirthDateValid);
			return this;
		}

		public Builder validateCity(String city) {
			this.isCityValid = (city != null && !city.isEmpty());
			System.out.println("City validation: " + this.isCityValid);
			return this;
		}

		public Builder validateLoginUnique(String login) throws DaoException {
			this.isLoginUnique = !regDao.isLoginExists(login);
			System.out.println("Login uniqueness validation: " + this.isLoginUnique);
			return this;
		}

		public Valid build() {
			return new Valid(this);
		}
	}

	public boolean isValid() {
		return isLoginValid && isPasswordValid && isNameValid && isBirthDateValid && isCityValid && isLoginUnique;
	}

	public boolean isLoginValid() {
		return isLoginValid;
	}

	public boolean isPasswordValid() {
		return isPasswordValid;
	}

	public boolean isNameValid() {
		return isNameValid;
	}

	public boolean isBirthDateValid() {
		return isBirthDateValid;
	}

	public boolean isCityValid() {
		return isCityValid;
	}

	public boolean isLoginUnique() {
		return isLoginUnique;
	}
}