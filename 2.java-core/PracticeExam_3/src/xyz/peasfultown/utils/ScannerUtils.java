package xyz.peasfultown.utils;

import java.util.Scanner;

public class ScannerUtils {

	public static void printPromptOptions(String... options) {
		for (int i = 0; i < options.length; i++) {
			System.out.printf("%4s(%d) %s\n", "", i+1, options[i]);
		}
		System.out.printf("%4s%s\n", "", "(0) Back");
	}
	
	public static int getInt(Scanner scanner, String message) {
		while (true) {
			System.out.print(message);
			try {
				int inp = Integer.parseInt(scanner.nextLine());
				
				return inp;
			} catch (NumberFormatException e) {
				System.out.println("Input must consist of only numerical characters, try again");
				continue;
			}
		}
	}

	public static float getFloat(Scanner scanner, String message, String errorMessage) {
		while (true) {
			System.out.print(message);
			try {
				float inp = Float.parseFloat(scanner.nextLine());
				if (inp <= 0) {
					System.out.println(errorMessage);
					continue;
				}
				return inp;
			} catch (NumberFormatException e) {
				System.out.println(errorMessage);
				continue;
			}
		}
	}

	public static double getDouble(Scanner scanner, String message, String errorMessage) {
		while (true) {
			System.out.print(message);
			try {
				double inp = Double.parseDouble(scanner.nextLine());
				if (inp <= 0) {
					System.out.println(errorMessage);
					continue;
				}
				return inp;
			} catch (NumberFormatException e) {
				System.out.println(errorMessage);
				continue;
			}
		}
	}

	public static String getString(Scanner scanner, String message) {
		while (true) {
			System.out.print(message);
			String inp = scanner.nextLine();
			if (inp.isEmpty()) {
				System.out.println("Input must not be empty, try again");
				continue;
			}
			return inp;
		}
	}
}
