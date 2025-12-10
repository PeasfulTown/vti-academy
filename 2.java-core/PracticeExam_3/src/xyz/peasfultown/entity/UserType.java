package xyz.peasfultown.entity;

public enum UserType {
	ADMIN("admin"), EMPLOYEE("employee");

	private String typeName;

	private UserType(String typeName) {
		this.typeName = typeName;
	}

	public String toString() {
		return this.typeName;
	}
}
