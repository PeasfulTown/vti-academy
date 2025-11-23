package xyz.peasfultown.entity;

import java.util.Scanner;

import xyz.peasfultown.utils.ScannerUtils;

public class Department {
	private static int numberOfDepartments;
	static {
		numberOfDepartments = 0;
	}
	private int id;
	private String name;
	private String address;
	
	public Department(Scanner scanner) {
		this.id = ScannerUtils.inputInt(scanner, "Enter department ID: ", "Department ID must be a positive number");
		this.name = ScannerUtils.inputString(scanner, "Enter department name: ");
		this.address = ScannerUtils.inputString(scanner, "Enter department address: ");
	}
	
	public Department() {
		this("Undefined Department");
	}
	
	public Department(String name) {
		this(name, "Unknown Address");
	}
	
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
	
	public void showInfo() {
		System.out.printf("ID: %d\n", this.id);
		System.out.printf("Name: %s\n", this.name);
		System.out.printf("Address: %s\n", this.address);
	}
	
	public boolean equals(Department department) {
		return this.name.equals(department.getName());
	}

	@Override
	public String toString() {
		return String.format("ID: %d, name: %s, address: %s", this.id, this.name, this.address);
	}
}
