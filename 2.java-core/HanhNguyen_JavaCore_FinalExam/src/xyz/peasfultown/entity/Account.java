package xyz.peasfultown.entity;

import java.time.LocalDate;

public class Account {
	private int id;
	private String fullname;
	private String email;
	private String password;
	private LocalDate birthDate;
	private Major major;

	public Account(int id, String fullname, String email, String password, LocalDate birthDate, Major major) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.major = major;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	public void printInfo() {
		System.out.printf("ID: %d\n", this.id);
		System.out.printf("Fullname: %s\n", this.fullname);
		System.out.printf("Email: %s\n", this.email);
		System.out.printf("Date of birth: %s\n", this.birthDate.toString());
		System.out.println("Major:");
		this.major.printInfo();
	}

}
