package xyz.peasfultown.backend.repository.exception;

import xyz.peasfultown.entity.AppException;

public class DatabaseException extends Exception {
	public DatabaseException(String msg) {
		super(msg);
	}
	
	public DatabaseException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
