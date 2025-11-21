package xyz.peasfultown.program;

import xyz.peasfultown.entity.Account;
import xyz.peasfultown.utils.Prompt;

import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class Exercise4 {
	private Scanner scanner;
	private Random rand;
	private Data data;
	
	public Exercise4(Scanner scanner, Random rand, Data data) {
		this.scanner = scanner;
		this.rand = rand;
		this.data = data;
	}
	
	public void run() {
		while (true) {
			System.out.println("Exercise 4 Options:");
			System.out.printf("%4s%s\n", " ", "(1) Question 1");
			System.out.printf("%4s%s\n", " ", "(2) Question 2");
			System.out.printf("%4s%s\n", " ", "(3) Question 3");
			System.out.printf("%4s%s\n", " ", "(4) Question 4");
			System.out.printf("%4s%s\n", " ", "(5) Question 5");

			System.out.printf("%4s%s\n", " ", "(0) Exit Exercise 4");

			int usrOp = Prompt.getIntegerUserInput(scanner, "\nEnter option: ");
			System.out.println();
			switch (usrOp) {
			case 0:
				System.out.println("\nExitting Exercise 4\n");
				return;
			case 1:
				System.out.println("Selected Question 1");
				question1(rand);
				break;
			case 2:
				System.out.println("Selected Question 2");
				question2(rand);
				break;
			case 3:
				System.out.println("Selected Question 3");
				question3(rand, data.getAccounts());
				break;
			case 4:
				System.out.println("Selected Question 4");
				question4(rand);
				break;
			case 5:
				System.out.println("Selected Question 5");
				question5(rand);
				break;
			default:
				System.out.println("Invalid option, try again.");
				break;
			}
			Prompt.continuePrompt(scanner);
		}
	}
	
	public static void question1(Random rand) {
		System.out.println("Question 1");
		System.out.println(rand.nextInt());
	}
	
	public static void question2(Random rand) {
		System.out.println("Question 2");
		System.out.println(rand.nextFloat());
	}
	
	public static void question3(Random rand, Account[] accounts) {
		System.out.println("Question 3");
		int randId = rand.nextInt(accounts.length);
		System.out.println(accounts[randId].getFullname());
	}
	
	public static void question4(Random rand) {
		System.out.println("Question 4");
		long orig = LocalDate.of(1995, 7, 24).toEpochDay();
		long bound = LocalDate.of(1995, 12, 20).toEpochDay();
		long selection = rand.nextLong(orig, bound);
		System.out.printf("Randomly selected time: %tF%n", LocalDate.ofEpochDay(selection));
	}

	public static void question5(Random rand) {
		long now = LocalDate.now().toEpochDay();
		long past = LocalDate.now().minusYears(1).toEpochDay();
		
		System.out.printf("Selected day: %tF%n", LocalDate.ofEpochDay(rand.nextLong(past, now)));
	}
	
	public static void question6(Random rand) {
		long now = LocalDate.now().toEpochDay();
		System.out.printf("Selected day: %tF%n", LocalDate.ofEpochDay(rand.nextLong(now)));
	}
	
	public static void question7(Random rand) {
		System.out.printf("Random 3 digit number: %d%n", rand.nextInt(100, 1000)); 
	}
}
