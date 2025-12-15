package xyz.peasfultown.entity;

import java.time.LocalDate;

public class Student extends Account {

	public Student(int id, String fullname, String email, String password, LocalDate birthDate, Major major) {
		super(id, fullname, email, password, birthDate, major);
	}

	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("Role: Student");
	}

	@Override
	public String toString() {
		return String.format("Student ID: %d, fullname: %s, email: %s, birthdate: %s, major: %s", super.getId(),
				super.getFullname(), super.getEmail(), super.getBirthDate().toString(), super.getMajor().getName());
	}
}
