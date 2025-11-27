package xyz.peasfultown.entity;

public final class SecondaryStudent extends Student {
	private static int count = 0;
	public SecondaryStudent(String name) throws Exception {
		super(count++, name);
	}
	
	public static int getCount() {
		return SecondaryStudent.count;
	}
}
