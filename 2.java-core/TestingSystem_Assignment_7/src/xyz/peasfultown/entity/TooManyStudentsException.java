package xyz.peasfultown.entity;

public class TooManyStudentsException extends RuntimeException {
	private static final long serialVersionUID = 121938917313L;
	private static final String TOO_MANY_STUDENT_INSTANCE_EXMSG = "Too many Student instances";
	
	public TooManyStudentsException() {
		super(TOO_MANY_STUDENT_INSTANCE_EXMSG);
	}
}
