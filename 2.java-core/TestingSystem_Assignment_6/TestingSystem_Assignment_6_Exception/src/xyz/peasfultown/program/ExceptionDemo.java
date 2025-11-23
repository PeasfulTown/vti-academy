package xyz.peasfultown.program;

import java.util.Scanner;

import xyz.peasfultown.entity.Department;
import xyz.peasfultown.entity.Group;
import xyz.peasfultown.utils.PromptUtils;

public class ExceptionDemo {
	private Scanner scanner;
	private Department[] departments;
	
	public ExceptionDemo(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ExceptionDemo ed = new ExceptionDemo(scanner);
		ed.run();
		scanner.close();
	}
	
	public void run() {
		while (true) {
			System.out.println("Testing System 6 Menu");
			System.out.printf("%4s%s\n", "", "(1) Question 1-2 division exception demo");
			System.out.printf("%4s%s\n", "", "(2) Question 3 array out of bounds exception demo");
			System.out.printf("%4s%s\n", "", "(3) Question 4 department get index demo");
			System.out.printf("%4s%s\n", "", "(4) Question 5-6 input age method demo");
			System.out.printf("%4s%s\n", "", "(5) Question 9 department creation using ScannerUtils demo");
			System.out.printf("%4s%s\n", "", "(6) Question 10 group creation using ScannerUtils demo");
			
			System.out.printf("%4s%s\n", "", "(0) Quit");
			
			
			int usrop = PromptUtils.getIntegerUserInput(this.scanner, "Enter option: ");
			switch (usrop) {
			case 0:
				System.out.println("Closing program");
				return;
			case 1:
				int inp1 = PromptUtils.getIntegerUserInput(scanner, "Enter first number: ");
				int inp2 = PromptUtils.getIntegerUserInput(scanner, "Enter second number: ");
				System.out.printf("Division result of: %d / %d = %.2f\n", inp1, inp2, q1_2division(inp1, inp2));
				break;
			case 2:
				q3printArrayOutOfBounds();
				break;
			case 3:
				this.departments = new Department[3];
				for (int i = 0; i < this.departments.length; i++)
					this.departments[i] = new Department(String.format("Department %d", i + 1));
				q4departmentArray(this.scanner);
				break;
			case 4:
				q5_6inputAgeDemo(this.scanner);
				break;
			case 5:
				q9DepartmentCreation(this.scanner);
				break;
			case 6:
				q10GroupCreationUsingConstructorScanner(scanner);
				break;
			default:
				System.out.println("Invalid option, try again");
				break;
			}
			PromptUtils.continuePrompt(this.scanner);
		}
	}
	
	private float q1_2division(int a, int b) {
		float result = 0.0f;
		try {
			result = (float) a / b;
		} catch (ArithmeticException e) {
			System.out.println("Cannot divide by 0");
		} finally {
			System.out.println("Division completed!");
		}
		return result;
	}
	
	private void q3printArrayOutOfBounds() {
		int[] numbers = { 1, 2, 3 };
		try {
			System.out.println(numbers[10]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}
	}

	private Department getIndex(int index) {
		try {
			return this.departments[index];			
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Cannot find department");
		}
		return null;
	}
	
	private void q4departmentArray(Scanner scanner) {
		int index = PromptUtils.getIntegerUserInput(scanner, "Enter department index: ");
		Department d = getIndex(index);
		if (d != null)
			System.out.println(d);
	}

	private int q5_6inputAge(Scanner scanner) {
		while (true) {
			System.out.print("Enter age: ");
			try {
				int inp = Integer.valueOf(scanner.nextLine());
				if (inp <= 0) {
					System.out.println("Invalid input, age must be a positive number, try again");
					continue;
				}
				return inp;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input, input must be a number, try again");
				continue;
			}
		}
	}
	
	private void q5_6inputAgeDemo(Scanner scanner) {
		int age = q5_6inputAge(scanner);
		System.out.printf("Age from input: %d\n", age);
	}
	
	private void q9DepartmentCreation(Scanner scanner) {
		Department d = new Department(scanner);
		System.out.println("New department info:");
		d.showInfo();
	}
	
	private void q10GroupCreationUsingConstructorScanner(Scanner scanner) {
		Group g = new Group(scanner);
		System.out.println("New group info:");
		g.showInfo();
	}
}
