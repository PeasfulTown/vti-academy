package xyz.peasfultown.program;

import java.util.Random;
import java.util.Scanner;

import xyz.peasfultown.utils.Prompt;

public class Program {
	private Scanner scanner;
	private Random rand;
	private Data data;

	public Program(Scanner scanner, Random rand, Data data) {
		this.scanner = scanner;
		this.rand = rand;
		this.data = data;
	}

	public void run() {
		while (true) {
			System.out.println("Testing System 2 Options:");
			System.out.printf("%4s%s\n", " ", "(1) Exercise 1");
			System.out.printf("%4s%s\n", " ", "(2) Exercise 2");
			System.out.printf("%4s%s\n", " ", "(3) Exercise 3");
			System.out.printf("%4s%s\n", " ", "(4) Exercise 4");
			System.out.printf("%4s%s\n", " ", "(5) Exercise 5");
			System.out.printf("%4s%s\n", " ", "(0) Exit Testing System 2");

			int usrOp = Prompt.getIntegerUserInput(scanner, "\nEnter option: ");
			System.out.println();
			switch (usrOp) {
			case 0:
				System.out.println("\nExitting Testing System 2\n");
				return;
			case 1:
				Exercise1 ex1 = new Exercise1(scanner, data);
				ex1.run();
				break;
			case 2:
				Exercise2 ex2 = new Exercise2(scanner, data);
				ex2.run();
				break;
			case 3:
				Exercise3 ex3 = new Exercise3(scanner, data);
				ex3.run();
				break;
			case 4:
				Exercise4 ex4 = new Exercise4(scanner, rand, data);
				ex4.run();
				break;
			case 5:
				Exercise5 ex5 = new Exercise5(scanner, data);
				ex5.run();
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
		Random rand = new Random();
		Program program = new Program(scanner, rand, data);
		program.run();
		scanner.close();
	}
}
