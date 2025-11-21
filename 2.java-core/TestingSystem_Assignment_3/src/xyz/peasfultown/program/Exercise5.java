package xyz.peasfultown.program;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import xyz.peasfultown.entity.Department;
import xyz.peasfultown.entity.Group;
import xyz.peasfultown.utils.Prompt;

public class Exercise5 {
	private Scanner scanner;
	private Data data;

	public Exercise5(Scanner scanner, Data data) {
		this.scanner = scanner;
		this.data = data;
	}

	public void run() {
		while (true) {
			System.out.println("Exercise 5 Options:");
			System.out.printf("%4s%s\n", " ", "(1) Question 1");
			System.out.printf("%4s%s\n", " ", "(2) Question 2");
			System.out.printf("%4s%s\n", " ", "(3) Question 3");
			System.out.printf("%4s%s\n", " ", "(4) Question 4");
			System.out.printf("%4s%s\n", " ", "(5) Question 5");
			System.out.printf("%4s%s\n", " ", "(6) Question 6");
			System.out.printf("%4s%s\n", " ", "(7) Question 7");
			System.out.printf("%4s%s\n", " ", "(8) Question 8");
			System.out.printf("%4s%s\n", " ", "(9) Question 9");

			System.out.printf("%4s%s\n", " ", "(0) Exit Exercise 5");

			int usrOp = Prompt.getIntegerUserInput(scanner, "\nEnter option: ");
			System.out.println();
			switch (usrOp) {
			case 0:
				System.out.println("\nExitting Exercise 5\n");
				return;
			case 1:
				System.out.println("Selected Question 1");
				question1(data.getGroups()[0]);
				break;
			case 2:
				System.out.println("Selected Question 2");
				question2(data.getGroups());
				break;
			case 3:
				System.out.println("Selected Question 3");
				question3(data.getDepartments()[1]);
				break;
			case 4:
				System.out.println("Selected Question 4");
				question4(data.getDepartments()[1]);
				break;
			case 5:
				System.out.println("Selected Question 5");
				question5(data.getDepartments()[1], data.getDepartments()[2]);
				break;
			case 6:
				System.out.println("Selected Question 6");
				question6(data.getDepartments());
				break;
			case 7:
				System.out.println("Selected Question 7");
				question7(data.getDepartments());
				break;
			default:
				System.out.println("Invalid option, try again.");
				break;
			}
			Prompt.continuePrompt(scanner);
		}
	}
	
	public void question1(Group grp) {
		System.out.println(grp);
	}
	
	public void question2(Group[] groups) {
		for (Group grp : groups) {
			System.out.println(grp);
		}
	}
	
	public void question3(Department dep) {
		System.out.printf("Department 1's address: %s\n", dep.getAddress());
	}

	public void question4(Department dep) {
		System.out.printf("Department 1's name is \"Department A\": %s\n", dep.getName().equals("Department A") ? "Yes" : "No");
	}
	
	public void question5(Department dep1, Department dep2) {
		System.out.printf("Department 1 (%s) is equal to department 2 (%s): %s\n", dep1.getName(), dep2.getName(), dep1.equals(dep2) ? "Yes" : "No");
	}
	
	public void question6(Department[] departments) {	
		List<Department> depls = Arrays.asList(departments);
		depls.sort( (x, y) -> x.getName().compareToIgnoreCase(y.getName()));
		for (Department dep : depls) {
			System.out.printf("%s\n", dep.getName());
		}
	}
	
	public void question7(Department[] departments) {
		List<Department> depls = Arrays.asList(departments);
		depls.sort( (x, y) -> {
			String[] spltstr1 = x.getName().split("\s");
			String[] spltstr2 = y.getName().split("\s");
			return spltstr1[spltstr1.length - 1].compareTo(spltstr2[spltstr2.length - 1]);
		});
		for (Department dep : depls) {
			System.out.printf("%s\n", dep.getName());
		}
	}
}
