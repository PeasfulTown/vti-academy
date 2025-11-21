package xyz.peasfultown.utils;

import java.util.Scanner;

public class Prompt {
	public static int getIntegerUserInput(Scanner scanner, String message) {
		while (true) {
			System.out.print(message);
			try { 
				return Integer.valueOf(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Input must be a number, try again.");	
				continue;
			}
		}
	}
	
	public static String getStringUserInput(Scanner scanner, String message) {
		while (true) {
			System.out.print(message);
			String inp = scanner.nextLine();
			if (inp.isBlank()) {
				System.out.println("Input cannot be empty, try again.");
				continue;
			}
			return inp;
		}
	}

	public static void continuePrompt(Scanner scanner) {
		System.out.println("\nPress Enter to Continue...\n");
		scanner.nextLine();
	}
}
