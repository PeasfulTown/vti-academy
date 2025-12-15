package xyz.peasfultown.backend.service;

public class ServiceAccountNotExistsException extends ServiceException{
	public ServiceAccountNotExistsException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ServiceAccountNotExistsException(String msg) {
		super(msg);
	}
}
