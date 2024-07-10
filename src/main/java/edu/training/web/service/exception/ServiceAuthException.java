package edu.training.web.service.exception;

public class ServiceAuthException extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceAuthException() {
		super();
	}

	public ServiceAuthException(String message) {
		super(message);
	}

	public ServiceAuthException(Exception e) {
		super(e);
	}

	public ServiceAuthException(String message, Exception e) {
		super(message, e);
	}

}
