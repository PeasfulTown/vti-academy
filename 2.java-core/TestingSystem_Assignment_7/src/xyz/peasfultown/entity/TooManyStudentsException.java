package xyz.peasfultown.entity;

public class TooManyStudentsException extends Exception {
	private static final String TOO_MANY_STUDENT_INSTANCE_EXMSG = "Too many Student instances";
	
	public TooManyStudentsException() {
		super(TOO_MANY_STUDENT_INSTANCE_EXMSG);
	}
}
