package xyz.peasfultown.backend.service;

public class ServiceInvalidCredentialsException extends ServiceException {
	public ServiceInvalidCredentialsException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ServiceInvalidCredentialsException(String msg) {
		super(msg);
	}
}
