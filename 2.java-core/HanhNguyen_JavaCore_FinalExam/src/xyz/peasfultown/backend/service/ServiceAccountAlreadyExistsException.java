package xyz.peasfultown.backend.service;

public class ServiceAccountAlreadyExistsException extends ServiceException {
	public ServiceAccountAlreadyExistsException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ServiceAccountAlreadyExistsException(String msg) {
		super(msg);
	}
}
