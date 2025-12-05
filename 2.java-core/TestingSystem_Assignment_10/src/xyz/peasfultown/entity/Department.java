package xyz.peasfultown.entity;

public class Department {
	private int id;
	private String name;
	
	public Department(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}	
	
	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Department department) {
		return this.name.equals(department.getName());
	}

	@Override
	public String toString() {
		return String.format("%d, %s", this.id, this.name);
	}
}
