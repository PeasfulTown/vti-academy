package xyz.peasfultown.entity;

public class Employee extends Personnel {
	private String responsibility;

	public Employee(String fullname, int age, Gender gender, String address, String responsibility) {
		super(fullname, age, gender, address);
		this.responsibility = responsibility;
	}

	public Employee(String fullname, int age, Gender gender, String address) {
		super(fullname, age, gender, address);
		this.responsibility = "Undefined";
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	
	public void showInfo() {
		super.showInfo();
		System.out.printf("Employee responsibility: %s\n", this.responsibility);
	}
	
	@Override
	public String toString() {
		return String.join(", ", super.toString(), String.format("responsibility: %s", this.responsibility));
	}
}
