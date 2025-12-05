package xyz.peasfultown.frontend;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import xyz.peasfultown.entity.Student;
import xyz.peasfultown.utils.IOManager;
import xyz.peasfultown.utils.ScannerUtils;

public class Exercise5 {
	private Scanner scanner;

	public Exercise5(Scanner scanner) {
		this.scanner = scanner;
	}

	public void run() {
		while (true) {
			System.out.println("Testing System 7 - Exercise 5 Demo");
			ScannerUtils.printOptions("Question 1a - Write student to file", "Question 1b - Read students from file");

			int usrop = ScannerUtils.inputInt(this.scanner, "Enter option: ");

			switch (usrop) {
			case 0:
				return;
			case 1:
				question1a(this.scanner);
				break;
			case 2:
				question1b(this.scanner);
				break;
			default:
				System.err.println("Invalid option, try again");
				break;
			}
		}
	}

	private void question1a(Scanner scanner) {
		String name1 = ScannerUtils.inputString(scanner, "Enter student 1 name: ");
		String name2 = ScannerUtils.inputString(scanner, "Enter student 2 name: ");
		String name3 = ScannerUtils.inputString(scanner, "Enter student 3 name: ");

		String path = ScannerUtils.inputString(scanner, "Enter destination file: ");
		List<Object> students = new ArrayList<>();
		students.add(new Student(name1));
		students.add(new Student(name2));
		students.add(new Student(name3));
		IOManager.writeObjects(students, path);
	}

	private void question1b(Scanner scanner) {
		String path = ScannerUtils.inputString(scanner, "Enter objects file path: ");
		List<Object> students = IOManager.readObjects(path);
		System.out.println("Students read from objects file");
		for (Object obj : students) {
			System.out.println((Student) obj);
		}
	}
}
