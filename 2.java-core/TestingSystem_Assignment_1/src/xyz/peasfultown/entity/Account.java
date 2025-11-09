package xyz.peasfultown.entity;

public class Account {
	private static int numberOfAccounts;
	static {
		numberOfAccounts = 0;
	}
	private int id;
	private String email;
	private String username;
	private String fullname;
	private Department department;
	private Position position;
	{
		this.id = numberOfAccounts++;
	}

	public Account(String email, String username, String fullname, Department department, Position position) {
		this.email = email;
		this.username = username;
		this.fullname = fullname;
		this.department = department;
		this.position = position;
	}

	public static int getNumberOfAccounts() {
		return numberOfAccounts;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", email=" + email + ", username=" + username + ", fullname=" + fullname
				+ ", department=" + department.getName() + ", position=" + position.getName() + "]";
	}
}
