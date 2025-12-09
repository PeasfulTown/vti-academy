package xyz.peasfultown.entity;

public class DatabaseLoginException extends DatabaseException {
	public DatabaseLoginException(String msg) {
		super(msg);
	}
	
	public DatabaseLoginException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
