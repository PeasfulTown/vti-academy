package xyz.peasfultown.entity;

public class UserHashException extends AppException {
	public UserHashException(String msg) {
		super(msg);
	}
	
	public UserHashException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
