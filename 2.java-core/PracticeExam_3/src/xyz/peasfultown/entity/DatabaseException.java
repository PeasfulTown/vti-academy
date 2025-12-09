package xyz.peasfultown.entity;

public class DatabaseException extends AppException {
	public DatabaseException(String msg) {
		super(msg);
	}
	
	public DatabaseException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
