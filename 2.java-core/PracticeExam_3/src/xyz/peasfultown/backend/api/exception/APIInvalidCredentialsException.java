package xyz.peasfultown.backend.api.exception;

public class APIInvalidCredentialsException extends APIException {

	public APIInvalidCredentialsException(String msg) {
		super(msg);
	}

	public APIInvalidCredentialsException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
