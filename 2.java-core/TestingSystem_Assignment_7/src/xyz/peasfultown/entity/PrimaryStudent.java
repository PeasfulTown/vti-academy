package xyz.peasfultown.entity;

public class PrimaryStudent extends Student {
	private static int count = 0;
	public PrimaryStudent(String name) throws Exception {	
		super(name);
		super.setId(count++);
	}
	
	public static int getCount() {
		return PrimaryStudent.count;
	}
}
