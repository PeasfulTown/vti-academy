package xyz.peasfultown.utils;

public class UserHashException extends Exception {
	public UserHashException(String msg) {
		super(msg);
	}
	
	public UserHashException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
