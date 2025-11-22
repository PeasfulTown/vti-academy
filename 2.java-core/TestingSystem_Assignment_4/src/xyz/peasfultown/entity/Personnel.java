package xyz.peasfultown.entity;

public abstract class Personnel {
	public enum Gender {
		MALE, FEMALE, OTHER
	}
	private String fullname;
	private int age;
	private Gender gender;
	private String address;
	
	public Personnel() {
		this("Default Staff", 0, Gender.OTHER, "Default Address");
	}
	
	public Personnel(String fullname, int age, Gender gender, String address) {
		this.fullname = fullname;
		this.age = age;
		this.gender = gender;
		this.address = address;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public void showInfo() {
		System.out.printf("Fullname: %s\n", this.fullname);
		System.out.printf("Age: %d\n", this.age);
		System.out.printf("Gender: %s\n", this.gender.name());
		System.out.printf("Address: %s\n", this.address);
	}
	
	@Override
	public String toString() {
		return String.format("Fullname: %s, age: %d, gender: %s, address: %s", this.fullname, this.age, this.gender.toString(), this.address);
	}
}
