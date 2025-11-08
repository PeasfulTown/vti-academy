package xyz.peasfultown.entity;

public class Department {
	private int id;
	private String name;
	public Department(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getDepartmentId() {
		return this.id;
	}
	public void setDepartmentId(int id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return this.name;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
}
