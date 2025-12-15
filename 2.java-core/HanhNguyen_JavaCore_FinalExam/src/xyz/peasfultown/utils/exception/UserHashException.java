package xyz.peasfultown.utils.exception;

public class UserHashException extends Exception {
	public UserHashException(String msg) {
		super(msg);
	}
	
	public UserHashException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
