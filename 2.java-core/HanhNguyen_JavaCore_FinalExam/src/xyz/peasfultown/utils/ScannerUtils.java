package xyz.peasfultown.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScannerUtils {
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Za-z]{2,6}$", Pattern.CASE_INSENSITIVE);
	public static final Pattern VALID_PASSWORD_REGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+!])(?=\\S+$).{8,20}$");
	
	public static void printPromptOptions(String... options) {
		for (int i = 0; i < options.length; i++) {
			System.out.printf("%4s(%d) %s\n", "", i + 1, options[i]);
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
				System.err.println("Input must consist of only numerical characters, try again");
				continue;
			}
		}
	}
	
	public static int getIntPositive(Scanner scanner, String message) {
		while (true) {
			System.out.print(message);
			try {
				int inp = Integer.parseInt(scanner.nextLine());
				
				if (inp < 0) {
					System.err.println("Input must be a positive integer");
					continue;
				}

				return inp;
			} catch (NumberFormatException e) {
				System.err.println("Input must consist of only numerical characters, try again");
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
					System.err.println(errorMessage);
					continue;
				}
				return inp;
			} catch (NumberFormatException e) {
				System.err.println(errorMessage);
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
				System.err.println(errorMessage);
				continue;
			}
		}
	}

	public static String getString(Scanner scanner, String message) {
		while (true) {
			System.out.print(message);
			String inp = scanner.nextLine();
			if (inp.isEmpty()) {
				System.err.println("Input must not be empty, try again");
				continue;
			}
			return inp;
		}
	}
	
	public static String getEmail(Scanner scanner, String message) {
		while (true) {
			String inp = ScannerUtils.getString(scanner, message);
			Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(inp);
			if (matcher.matches()) {
				return inp;
			} else 
				System.err.println("Input does not match email pattern, try again");
		}
	}

	public static String getPassword(Scanner scanner, String message) {
		StringBuilder requirements = new StringBuilder("Password must include:\n");
		requirements.append("  Total of at least 8 characters and no more than 20 characters\n");
		requirements.append("  At least 1 number\n");
		requirements.append("  At least 1 lowercase character\n");
		requirements.append("  At least 1 uppercase character\n");
		requirements.append("  At least 1 special character (@#$%^&+!) \n");
		requirements.append("  No spaces\n");
		requirements.append(message);
		while (true) {
			String inp = ScannerUtils.getString(scanner, requirements.toString());
			Matcher m = VALID_PASSWORD_REGEX.matcher(inp);
			if (m.matches()) 
				return inp;
			else {
				System.err.println("Password does not fit requirement, try again");
				continue;
			}
				
		}
	}
	
	public static String getPhoneNumber(Scanner scanner, String message) {
		while (true) {
			System.out.print(message);
			try {
				String inp = scanner.nextLine();
				if (inp.isEmpty()) {
					System.err.println("Input must not be empty, try again");
					continue;
				} else if (inp.length() < 9 || inp.length() > 12) {
					System.err.println("Phone number must be between 9 to 12 numbers");
				}
				Integer.parseInt(inp); // throw exception and loop again if unable to parse
				return inp;
			} catch (NumberFormatException nfe) { 
				System.err.println("Phone number must contain only numerical characters, try again.");
				continue;
			}
		}
	}
	
	public static LocalDate getDate(Scanner scanner) {
		while (true) {
			String inp = ScannerUtils.getString(scanner, "Enter birth date(DD/MM/YYYY): ");
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				return LocalDate.parse(inp, formatter);
			} catch(DateTimeParseException e) {
				System.out.println("Invalid date input, try again");
				continue;
			}
		}
	}

}
