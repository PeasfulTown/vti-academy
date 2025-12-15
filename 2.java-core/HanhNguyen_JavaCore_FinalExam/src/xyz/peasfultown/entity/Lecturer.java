package xyz.peasfultown.entity;

import java.time.LocalDate;

public class Lecturer extends Account {
	public Lecturer(int id, String fullname, String email, String password, LocalDate birthDate, Major major) {
		super(id, fullname, email, password, birthDate, major);
	}

	@Override
	public void printInfo() {
		super.printInfo();
		System.out.println("Role: Lecturer");
	}

	@Override
	public String toString() {
		return String.format("Lecturer ID: %d, fullname: %s, email: %s, birthdate: %s, major: %s", super.getId(),
				super.getFullname(), super.getEmail(), super.getBirthDate().toString(), super.getMajor().getName());
	}
}
