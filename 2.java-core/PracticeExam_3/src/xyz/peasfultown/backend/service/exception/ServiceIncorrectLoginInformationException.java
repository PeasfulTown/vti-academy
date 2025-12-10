package xyz.peasfultown.backend.service.exception;

public class ServiceIncorrectLoginInformationException extends ServiceException {
	public ServiceIncorrectLoginInformationException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ServiceIncorrectLoginInformationException(String msg) {
		super(msg);

	}
}
