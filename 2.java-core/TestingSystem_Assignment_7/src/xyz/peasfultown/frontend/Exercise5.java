package xyz.peasfultown.frontend;

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
			ScannerUtils.printOptions("Question 1 - Write student to file");
			
			int usrop = ScannerUtils.inputInt(this.scanner, "Enter option: ");
			
			switch(usrop) {
			case 0:
				return;
			case 1:
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
		Student s1 = null, s2 = null, s3 = null;
		try {
			s1 = new Student(name1);
			s2 = new Student(name1);
			s3 = new Student(name1);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		String path = ScannerUtils.inputString(scanner, "Enter destination file: ");
		IOManager.writeObject(s1, path);
		IOManager.writeObject(s2, path);
		IOManager.writeObject(s3, path);
	}
	
	private void question1b(Scanner scanner) {
		String path = ScannerUtils.inputString(scanner, "Enter file path: ");
		Student[] students = new Student[3];
		int i = 0;
		while (i < students.length) {
			
		}
	}
}
