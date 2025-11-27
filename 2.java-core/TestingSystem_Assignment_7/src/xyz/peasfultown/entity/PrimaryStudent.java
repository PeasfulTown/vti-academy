package xyz.peasfultown.entity;

public final class PrimaryStudent extends Student {
	private static int count = 0;
	public PrimaryStudent(String name) throws Exception {
		super(count++, name);
	}
	
	public static int getCount() {
		return PrimaryStudent.count;
	}
}
