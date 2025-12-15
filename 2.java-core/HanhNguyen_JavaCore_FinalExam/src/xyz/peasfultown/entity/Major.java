package xyz.peasfultown.entity;

public class Major {
	private int id;
	private String name;

	public Major(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public void printInfo() {
		System.out.printf(" ID: %d\n", this.id);
		System.out.printf(" Name: %s\n", this.name);
	}
}
