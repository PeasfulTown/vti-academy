package xyz.peasfultown.entity;

public class Student implements IStudent {
	private static int count;
	static {
		count = 0;
	}
	private int id;
	private String name;
	private int group;
	
	public Student(String name) {
		this(name, 0);
	}
	
	public Student(String name, int group) {
		this.id = count++;
		this.name = name;
		this.group = group;
	}

	public static int getCount() {
		return count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}
	
	public void attendance() {
		System.out.printf("%s is present\n", this.name);
	}
	
	public void study() {
		System.out.printf("%s is studying\n", this.name);
	}
	
	public void clean() {
		System.out.printf("%s is cleaning the toilet\n", this.name);
	}
	
	@Override
	public String toString() {
		return String.format("ID: %d, name: %s, group: %d", this.id, this.name, this.group);
	}
}
