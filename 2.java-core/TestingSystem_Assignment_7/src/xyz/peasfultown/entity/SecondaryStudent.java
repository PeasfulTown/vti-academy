package xyz.peasfultown.entity;

public class SecondaryStudent extends Student {
	private static int count = 0;
	public SecondaryStudent(String name) throws Exception {
		super(name);
		super.setId(count++);
	}
	
	public static int getCount() {
		return SecondaryStudent.count;
	}
}
