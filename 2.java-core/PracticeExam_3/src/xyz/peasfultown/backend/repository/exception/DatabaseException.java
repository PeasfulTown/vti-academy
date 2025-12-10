package xyz.peasfultown.backend.repository.exception;

import xyz.peasfultown.entity.AppException;

public class DatabaseException extends AppException {
	public DatabaseException(String msg) {
		super(msg);
	}
	
	public DatabaseException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
