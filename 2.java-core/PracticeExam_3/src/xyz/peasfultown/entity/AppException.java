package xyz.peasfultown.entity;


public class AppException extends Exception {
	private static final long serialVersionUID = 9L;
	public AppException() {
		super();
	}
	
	public AppException(String msg) {
		super(msg);
	}
	
	public AppException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
