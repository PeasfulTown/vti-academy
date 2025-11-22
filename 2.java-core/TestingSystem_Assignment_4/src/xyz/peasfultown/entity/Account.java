package xyz.peasfultown.entity;

import java.time.LocalDateTime;

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
	private LocalDateTime createDate;
	private Group[] groups;
	{
		this.id = numberOfAccounts++;
		this.createDate = LocalDateTime.now();
		this.groups = new Group[0];
	}
	
	public Account() {
		this("", "", "", null, null);
		this.id = 0;
	}
	
	public Account(String username) {
		this(0, "", username, "", "");
	}
	
	public Account(int id, String email, String username, String firstname, String lastname) {
		this(email, username, firstname + " " + lastname, null, null);
		this.id = id;
	}
	
	public Account(int id, String email, String username, String firstname, String lastname, Position position) {
		this(id, email, username, firstname, lastname);
		this.position = position;
	}
	
	public Account(int id, String email, String username, String firstname, String lastname, Position position, LocalDateTime createDate) {
		this(id, email, username, firstname, lastname, position);
		this.createDate = createDate;
	}
	
	public Account(String email, String username, String fullname, Department department, Position position) {
		this.email = email;
		this.username = username;
		this.fullname = fullname;
		this.department = department;
		this.position = position;
	}
	
	public Account(String email, String username, String fullname, Department department, Position position, Group[] groups) {
		this(email, username, fullname, department, position);
		this.groups = groups;
	}

	public static int getNumberOfAccounts() {
		return numberOfAccounts;
	}

	public int getId() {
		return this.id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public LocalDateTime getCreateDate() {
		return this.createDate;
	}
	
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Group[] getGroups() {
		return this.groups;
	}

	public void setGroups(Group[] groups) {
		this.groups = groups;
	}

	public void addGroup(Group newGroup) {
		Group[] newGroups = new Group[this.groups.length + 1];
		for (int i = 0; i < this.groups.length; i++)
			newGroups[i] = this.groups[i];
		newGroups[newGroups.length - 1] = newGroup;
		this.groups = newGroups;
	}
	
	public void addGroup(Group... groups) {
		int newGroupSize = this.groups.length + groups.length;
		Group[] newGroups = new Group[newGroupSize];
		for (int i = 0; i < this.groups.length; i++)
			newGroups[i] = this.groups[i];
		for (int i = this.groups.length, j = 0; i < newGroupSize; i++, j++)
			newGroups[i] = groups[j];
		this.groups = newGroups;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", email=" + email + ", username=" + username + ", fullname=" + fullname
				+ ", department=" + (this.department != null ? department.getName() : "null") + ", position=" + (this.position != null ? position.getName() : "null") + "]";
	}
}
