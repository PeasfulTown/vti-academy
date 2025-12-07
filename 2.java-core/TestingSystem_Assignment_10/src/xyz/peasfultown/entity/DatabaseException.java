package xyz.peasfultown.entity;

public class DatabaseException extends Exception {
	private static final long serialVersionUID = 9L;
	public DatabaseException() {
		super();
	}
	
	public DatabaseException(String msg) {
		super(msg);
	}
	
	public DatabaseException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
