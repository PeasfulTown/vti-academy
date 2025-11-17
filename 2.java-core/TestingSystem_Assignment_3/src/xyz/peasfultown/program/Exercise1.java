package xyz.peasfultown.program;

import java.util.Random;
import java.util.Scanner;
import xyz.peasfultown.utils.Prompt;

public class Exercise1 {
	private Scanner scanner;
	public Exercise1(Scanner scanner) {
		System.out.println("Initializing Exercise 1");
		this.scanner = scanner;
	}
	
	public void run() {
		while (true) {
			System.out.println("Exercise 1 Options:");
			System.out.printf("%4s%s\n", " ", "(1) Question 1");
			System.out.printf("%4s%s\n", " ", "(2) Question 2");
			System.out.printf("%4s%s\n", " ", "(3) Question 3");
			System.out.printf("%4s%s\n", " ", "(4) Question 4");
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
			case 4:
				System.out.println("Selected Question 4");
				question4(scanner);
				break;
			default:
				System.out.println("Invalid option, try again.");
				break;
			}
			Prompt.continuePrompt(scanner);
		}
	}

	private void question1() {
		float salary1 = 5240.5f;
		float salary2 = 10970.055f;
		System.out.printf("Rounded Salary 1: %d\n", (int) salary1);
		System.out.printf("Rounded Salary 2: %d\n", (int) salary2);
	}

	private int question2() {
		Random rand = new Random();
		int num = rand.nextInt(100000);
		rand = null;
		System.out.printf("Randomized number: %05d\n", num);
		return num;
	}

	private void question3() {
		String numStr = String.valueOf(question2());
		System.out.printf("Last 2 digit of number: %s\n", numStr.substring(numStr.length() - 2));
	}

	private void question4(Scanner scanner) {		
		int inp1 = Prompt.getIntegerUserInput(scanner, "\nEnter first number: ");
		int inp2 = Prompt.getIntegerUserInput(scanner, "\nEnter second number: ");
		System.out.printf("%d + %d = %d\n", inp1, inp2, inp1 + inp2);
	}
}
