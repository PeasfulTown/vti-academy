package xyz.peasfultown.entity;

public class Worker extends Personnel {
	private int level;
	
	public Worker(String fullname, int age, Gender gender, String address, int level) {
		super(fullname, age, gender, address);
		this.level = level;
	}
	
	public Worker(String fullname, int age, Gender gender, String address) {
		super(fullname, age, gender, address);
		this.level = 0;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		if (level < 1 || level > 10) {
			System.out.println("Level cannot be lower than 1 or higher than 10");
			return;
		}
		
		this.level = level;
	}
	
	public void showInfo() {
		super.showInfo();
		System.out.printf("Worker level: %d\n", this.level);
	}
	
	@Override
	public String toString() {
		return String.join(", ", super.toString(), String.format("level: %d", this.level));
	}
}
