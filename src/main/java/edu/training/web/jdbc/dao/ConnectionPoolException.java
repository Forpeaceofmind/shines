package edu.training.web.jdbc.dao;

public class ConnectionPoolException extends Exception {

	private static final long serialVersionUID = 1L;

	public ConnectionPoolException(String massage, Exception e) {
		super(massage, e);
	}
}
