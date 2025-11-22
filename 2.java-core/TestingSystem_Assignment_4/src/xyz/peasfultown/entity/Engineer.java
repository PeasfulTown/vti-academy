package xyz.peasfultown.entity;

public class Engineer extends Personnel {
	private String specialization;

	public Engineer(String fullname, int age, Gender gender, String address, String specialization) {
		super(fullname, age, gender, address);
		this.specialization = specialization;
	}

	public Engineer(String fullname, int age, Gender gender, String address) {
		super(fullname, age, gender, address);
		this.specialization = "Undefined";
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public void showInfo() {
		super.showInfo();
		System.out.printf("Engineer specialization: %s\n", this.specialization);
	}

	@Override
	public String toString() {
		return String.join(", ", super.toString(), String.format("specialization: %s", this.specialization));
	}
}
