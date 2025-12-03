package xyz.peasfultown.entity;

import java.time.Instant;

public class MyException extends Exception {
	private String message;
	private String reason;
	private StackTraceElement stacktrace;
	private Instant time;
	
	
}
