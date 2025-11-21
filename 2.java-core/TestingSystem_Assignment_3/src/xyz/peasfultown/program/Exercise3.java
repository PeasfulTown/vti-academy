package xyz.peasfultown.program;

import java.util.Scanner;

import xyz.peasfultown.utils.Prompt;

public class Exercise3 {
	private Scanner scanner;
	public Exercise3 (Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void run() {
		while (true) {
			System.out.println("Exercise 3 Options:");
			System.out.printf("%4s%s\n", " ", "(1) Question 1");
			System.out.printf("%4s%s\n", " ", "(2) Question 2");
			System.out.printf("%4s%s\n", " ", "(3) Question 3");
			System.out.printf("%4s%s\n", " ", "(0) Exit Exercise 1");

			int usrOp = Prompt.getIntegerUserInput(scanner, "\nEnter option: ");
			System.out.println();
			switch (usrOp) {
			case 0:
				System.out.println("\nExitting Exercise 1\n");
				return;
			case 1:
				System.out.println("Selected Question 1");
				question1();
				break;
			case 2:
				System.out.println("Selected Question 2");
				question2();
				break;
			case 3:
				System.out.println("Selected Question 3");
				question3();
				break;
			default:
				System.out.println("Invalid option, try again.");
				break;
			}
			Prompt.continuePrompt(scanner);
		}
	}
	
	public void question1() {
		int salary = 5000;
		System.out.printf("Salary: %.2f\n", (float) salary);
	}
	
	public void question2() {
		String value = "1234567";
		System.out.printf("Integer from string: %d\n", Integer.parseInt(value));
	}
	
	public void question3() {
		Integer value = 1234567;
		System.out.printf("int value: %d\n", (int) value);
	}
}
