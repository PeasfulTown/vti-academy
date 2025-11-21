package xyz.peasfultown.program;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;

import xyz.peasfultown.entity.Account;
import xyz.peasfultown.utils.Prompt;

public class Exercise2 {
	private Scanner scanner;
	private Data data;
	
	public Exercise2(Scanner scanner, Data data) {
		this.scanner = scanner;
		this.data = data;
	}
	
	public void run() {
		while (true) {
			System.out.println("Exercise 2 Options:");
			System.out.printf("%4s%s\n", " ", "(1) Question 1");
			System.out.printf("%4s%s\n", " ", "(2) Question 2");
			System.out.printf("%4s%s\n", " ", "(3) Question 3");
			System.out.printf("%4s%s\n", " ", "(4) Question 4");
			System.out.printf("%4s%s\n", " ", "(5) Question 5");
			System.out.printf("%4s%s\n", " ", "(6) Question 6");

			System.out.printf("%4s%s\n", " ", "(0) Exit Exercise 2");

			int usrOp = Prompt.getIntegerUserInput(scanner, "\nEnter option: ");
			System.out.println();
			switch (usrOp) {
			case 0:
				System.out.println("\nExitting Exercise 2\n");
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
				question4();
				break;
			case 5:
				System.out.println("Selected Question 5");
				question5();
				break;
			case 6:
				System.out.println("Selected Question 6");
				question6(data.getAccounts());
				break;
			default:
				System.out.println("Invalid option, try again.");
				break;
			}
			Prompt.continuePrompt(scanner);
		}

	}
	
	public static void question1() {
		System.out.println("Question 1");
		int num = 5;
		System.out.printf("%d", num);
	}

	public static void question2() {
		System.out.println("Question 2");
		int num = 100000000;
		System.out.printf("%,d", num);
	}

	public static void question3() {
		System.out.println("Question 3");
		double num = 5.567098;
		System.out.printf("%.4f", num);
	}

	public static void question4() {
		System.out.println("Question 4");
		String name = "Nguyen Van A";
		System.out.printf("My name is %s and I am single%n", name);
	}

	public static void question5() {
		System.out.println("Question 5");
		// 17/11/2025 07h:27p:00s
		System.out.printf("%1$td/%1$tm/%1$tY %1$tHh:%1$tMp:%1$tSs%n", LocalDateTime.now());
	}

	public static void question6(Account[] accounts) {
		System.out.println("Question 6");
		System.out.printf("|%4s|%20s|%10s|%20s|%25s|%20s|%30s|%n", "ID", "Email", "Username", "Fullname", "Department",
				"Position", "Groups");
		for (Account acc : accounts) {
			System.out.printf("|%4d|%20s|%10s|%20s|%25s|%20s|%30s|%n", acc.getId(), acc.getEmail(), acc.getUsername(),
					acc.getFullname(), acc.getDepartment().getName(), acc.getPosition().getName(),
					String.join(", ", Arrays.stream(acc.getGroups()).map(g -> g.getName()).toArray(String[]::new)));
		}
	}
}
