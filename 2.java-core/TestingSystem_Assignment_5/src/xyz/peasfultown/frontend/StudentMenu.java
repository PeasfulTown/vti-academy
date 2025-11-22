package xyz.peasfultown.frontend;

import java.util.Scanner;

import xyz.peasfultown.backend.StudentController;
import xyz.peasfultown.entity.Student;
import xyz.peasfultown.utils.PromptUtils;

public class StudentMenu {
	private Scanner scanner;
	private StudentController sc;

	public StudentMenu(Scanner scanner, StudentController sc) {
		this.scanner = scanner;
		this.sc = sc;
	}

	public void run() {
		while (true) {
			System.out.println("Student management menu");
			System.out.printf("%4s%s\n", "", "(1) Add student");
			System.out.printf("%4s%s\n", "", "(2) List students");
			System.out.printf("%4s%s\n", "", "(3) Take attendance");
			System.out.printf("%4s%s\n", "", "(4) Get group 1 to go study");
			System.out.printf("%4s%s\n", "", "(5) Get group 2 to go clean");

			System.out.printf("%4s%s\n", "", "(0) Back");

			int usrop = PromptUtils.getIntegerUserInput(this.scanner, "Enter option: ");
			switch (usrop) {
			case 0:
				System.out.println("Exitting program");
				return;
			case 1:
				this.insertStudent(this.scanner);
				break;
			case 2:
				this.printAllStudents();
				break;
			case 3:
				this.takeAttendance();
				break;
			case 4: 
				this.makeGroup1Study();
				break;
			case 5:
				this.makeGroup2Clean();
				break;
			default: 
				System.out.println("Invalid option, try again");
				break;
			}
			PromptUtils.continuePrompt(this.scanner);
		}
	}
	
	private void insertStudent(Scanner scanner) {
		String name = PromptUtils.getStringUserInput(scanner, "Enter student name: ");
		int group = PromptUtils.getIntegerUserInput(scanner, "Enter this student's group: ");
		this.sc.insert(name, group);
		System.out.println("Student added");
	}
	
	private void printAllStudents() {
		this.sc.listStudents();
	}
	
	private void takeAttendance() {
		this.sc.takeAllAttendance();
	}
	
	private void makeGroup1Study() {
		this.sc.makeGroupStudy(1);
	}
	
	private void makeGroup2Clean() {
		this.sc.makeGroupClean(2);
	}
	
	public void insert10Students() {
		this.sc.insert(
			new Student("Alicia", 1),
			new Student("Cariane", 1),
			new Student("Eugene", 1),
			new Student("Adam", 1),
			new Student("Alvin", 2),
			new Student("Evee", 2),
			new Student("Shannon", 2),
			new Student("Clayton", 3),
			new Student("Kadija", 3),
			new Student("Allison", 3)
		);
	}
}
