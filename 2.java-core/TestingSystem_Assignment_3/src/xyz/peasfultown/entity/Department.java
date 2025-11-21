package xyz.peasfultown.entity;

public class Department {
	private static int numberOfDepartments;
	static {
		numberOfDepartments = 0;
	}
	private int id;
	private String name;
	private String address;
	
	public Department(String name, String address) {
		this.id = numberOfDepartments++;
		this.name = name;
		this.address = address;
	}
	
	public int getNumberOfDepartments() {
		return numberOfDepartments;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean equals(Department department) {
		return this.name.equals(department.getName());
	}

	@Override
	public String toString() {
		return String.format("%d, %s", this.id, this.name);
	}
}
