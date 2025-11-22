package xyz.peasfultown.frontend;

import java.util.Scanner;

import xyz.peasfultown.backend.Exercise1;
import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.Department;
import xyz.peasfultown.entity.Group;
import xyz.peasfultown.utils.PromptUtils;

public class Program1 {
	private Scanner scanner;
	private Exercise1 ex1;
	public Program1 (Scanner scanner) {
		this.scanner = scanner;
		this.ex1 = new Exercise1();
	}
	public void run() {
		while (true) {
			System.out.println("Exercise 1 Options:");
			System.out.printf("%4s%s\n", " ", "(1) Question 1");
			System.out.printf("%4s%s\n", " ", "(2) Question 2");
			System.out.printf("%4s%s\n", " ", "(3) Question 3");
			System.out.printf("%4s%s\n", " ", "(0) Back");
			
			
			int usrop = PromptUtils.getIntegerUserInput(scanner, "Enter option: ");
			switch (usrop) {
			case 0:
				System.out.println("Exiting Exercise 1");
				return;
			case 1:
				Department[] q1dep = ex1.question1();
				System.out.println("Created departments using constructors:");
				for (Department dep : q1dep)
					System.out.println(dep);
				break;
			case 2: 
				Account[] q2acc = ex1.question2();
				System.out.println("Created accounts using constructors:");
				for (Account acc : q2acc)
					System.out.println(acc);
				break;
			case 3:
				Group[] q3grp = ex1.question3();
				System.out.println("Created groups using constructors:");
				for (Group grp : q3grp)
					System.out.println(grp);
				break;
			default:
					System.out.println("Invalid option, try again.");
			}
			PromptUtils.continuePrompt(scanner);
		}
	}
}
