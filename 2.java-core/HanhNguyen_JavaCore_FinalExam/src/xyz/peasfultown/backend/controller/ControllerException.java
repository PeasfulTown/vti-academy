package xyz.peasfultown.backend.controller;

public class ControllerException extends Exception {
	public ControllerException(String message, Throwable cause) {
		super(message, cause);
	}

	public ControllerException(String message) {
		super(message);
	}
}
