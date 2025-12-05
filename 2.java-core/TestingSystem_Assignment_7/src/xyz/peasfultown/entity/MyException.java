package xyz.peasfultown.entity;

import java.time.Instant;

public class MyException extends Exception {
	private static final long serialVersionUID = 89247927492L; 
	private Instant timestamp;
	
	public MyException(String msg) {
		super(msg);
		this.timestamp = Instant.now();
	}
	
	public MyException(String msg, Throwable cause) {
		super(msg, cause);
		this.timestamp = Instant.now();
	}
	
	public MyException(String msg, Throwable cause, StackTraceElement[] stacktrace, Instant timestamp) {
		super(msg, cause);
		super.setStackTrace(stacktrace);
		this.timestamp = timestamp;
	}
	
	public Instant getTimeStamp() {
		return this.timestamp;
	}
}
