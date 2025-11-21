package xyz.peasfultown.program;

import java.util.Scanner;

import xyz.peasfultown.utils.Prompt;

public class Program {
	private Scanner scanner;
	private Data data;
	public Program(Scanner scanner, Data data) {
		System.out.println("Initializing Program...");
		this.scanner = scanner;
		this.data = data;
	}
	
	public void run() {
		while (true) {
			System.out.println("Testing System 3 Options:");
			System.out.printf("%4s%s\n", " ", "(1) Exercise 1");
			System.out.printf("%4s%s\n", " ", "(2) Exercise 2");
			System.out.printf("%4s%s\n", " ", "(3) Exercise 3");
			System.out.printf("%4s%s\n", " ", "(4) Exercise 4");
			System.out.printf("%4s%s\n", " ", "(5) Exercise 5");
			System.out.printf("%4s%s\n", " ", "(0) Exit program");

			int usrOp = Prompt.getIntegerUserInput(scanner, "\nEnter option: ");
			switch (usrOp) {
			case 0: 
				System.out.println("\nExiting Program...");
				return;
			case 1: 
				Exercise1 ex1 = new Exercise1(scanner);
				ex1.run();
				break;
			case 2:
				Exercise2 ex2 = new Exercise2();
				ex2.printAccounts();
				break;
			case 3: 
				Exercise3 ex3 = new Exercise3(scanner);
				ex3.run();
				break;
			case 4:
				Exercise4 ex4 = new Exercise4(scanner, data);
				ex4.run();
				break;
			case 5:
				Exercise5 ex5 = new Exercise5(scanner, data);
				ex5.run();
				break;
			default:
				System.out.println("Invalid option, try again.");
				break;
			}
			Prompt.continuePrompt(scanner);
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Data data = new Data();
		Program program = new Program(scanner, data);
		program.run();
		scanner.close();
	}
}
