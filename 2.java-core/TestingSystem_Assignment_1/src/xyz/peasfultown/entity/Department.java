package xyz.peasfultown.entity;

public enum Department {
	SALES(0, "Sales"),
	MARKETING(1, "Marketing"),
	HUMAN_RESOURCES(2, "Human Resources"),
	FINANCE(3, "Finance"),
	INFORMATION_TECHNOLOGY(4, "Information Technology"),
	CUSTOMER_SERVICE(5, "Customer Service"),
	MAINTENANCE(6, "Maintenance"),
	SECURITY(7, "Security"),
	OPERATIONS(8, "Operations"),
	ADMINISTRATION(9, "Administration"),
	WAITING_ROOM(10, "Waiting Room");
	
	private final int id;
	private final String name;
	Department(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}	
}
