package xyz.peasfultown.entity;

public class DatabaseConfigException extends DatabaseException {
	public DatabaseConfigException(String msg) {
		super(msg);
	}
	
	public DatabaseConfigException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
