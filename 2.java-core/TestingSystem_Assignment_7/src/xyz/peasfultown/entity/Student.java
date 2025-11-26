package xyz.peasfultown.entity;

public class Student {
	private static int count = 0;
	private static String college;
	private static int money;
	
	private int id;
	private String name;
	
	public Student(String name) throws Exception {
		if (count < 7) {
			this.id = count++;
			this.name = name;			
		} else {
			throw new Exception("Not allowed to create more than 7 Students");
		}
	}

	public static int getCount() {
		return Student.count;
	}

	public static String getCollege() {
		return Student.college;
	}
	
	public static void setCollege(String college) {
		Student.college = college;
	}
	
	public static int getMoney() {
		return Student.money;
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
	
	public void contribute(int amount) {
		System.out.printf("Student %s contributed %,dVND\n", this.name, amount);
		money += amount;
	}
	
	public void spend(int amount) {
		System.out.printf("Student %s spent %,dVND\n", this.name, amount);
		money -= amount;
	}
	
	public void spend(int amount, String... items) {
		System.out.printf("Student %s spent %,dVND to buy %s\n", this.name, amount, String.join(", ", items));
		money -= amount;
	}
	
	public void showInfo() {
		System.out.printf("ID: %d\n", this.id);
		System.out.printf("Name: %s\n", this.name);
		System.out.printf("College: %s\n", Student.getCollege());
	}
	
	@Override
	public String toString() {
		return String.format("Student = ID: %d, name: %s, college: %s", this.id, this.name, Student.getCollege());
	}
}
