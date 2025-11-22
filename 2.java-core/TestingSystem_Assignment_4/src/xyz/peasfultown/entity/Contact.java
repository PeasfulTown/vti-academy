package xyz.peasfultown.entity;

public class Contact {
	private String name;
	private String number;
	
	public Contact(String name, String number) {
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
		
	public void showInfo() {
		System.out.printf("Name: %s\n", this.name);
		System.out.printf("Phone number: %s\n", this.number);
	}
	
	@Override
	public String toString() {
		return String.format("%s: %s", this.name, this.number);
	}
}
