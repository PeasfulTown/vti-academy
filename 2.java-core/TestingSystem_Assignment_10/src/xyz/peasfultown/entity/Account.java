package xyz.peasfultown.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
	private int id;
	private String email;
	private String username;
	private String fullname;
	private Gender gender;
	private Department department;
	private Position position;
	private LocalDateTime createDate;
	private List<Group> groups;
	
	public Account(int id, String email, String username, String fullname, Gender gender, Department department, Position position,
			LocalDateTime createDate) {
		this(id, email, username, fullname, gender, department, position, createDate, new ArrayList<>());
	}

	public Account(int id, String email, String username, String fullname, Gender gender, Department department, Position position,
			LocalDateTime createDate, List<Group> groups) {
		this.id = id;
		this.email = email;
		this.username = username;
		this.fullname = fullname;
		this.gender = gender;
		this.department = department;
		this.position = position;
		this.createDate = createDate;
		this.groups = groups;
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
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

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", email=" + email + ", username=" + username + ", fullname=" + fullname
				+ ", department=" + (this.department != null ? department.getName() : "null") + ", position=" + (this.position != null ? position.getName() : "null") + "]";
	}
}
