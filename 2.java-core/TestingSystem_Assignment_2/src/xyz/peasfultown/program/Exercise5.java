package xyz.peasfultown.program;

import java.util.Scanner;

import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.CategoryQuestion;
import xyz.peasfultown.entity.Department;
import xyz.peasfultown.entity.Position;
import xyz.peasfultown.entity.Question;
import xyz.peasfultown.entity.TypeQuestion;
import xyz.peasfultown.utils.Prompt;

public class Exercise5 {
	private Scanner scanner;
	private Data program;
	public Exercise5(Scanner scanner, Data program) {
		this.scanner = scanner;
		this.program = program;
	}
	
	public void run() {
		while (true) {
			System.out.println("Exercise 5 Options:");
			System.out.printf("%4s%s\n", " ", "(1) Question 1");
			System.out.printf("%4s%s\n", " ", "(2) Question 2");
			System.out.printf("%4s%s\n", " ", "(3) Question 3");
			System.out.printf("%4s%s\n", " ", "(5) Question 5");
			System.out.printf("%4s%s\n", " ", "(8) Question 8");
		
			System.out.printf("%4s%s\n", " ", "(0) Exit Exercise 5");

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
			case 5:
				System.out.println("Selected Question 5");
				question5();
				break;
			case 8:
				System.out.println("Selected Question 8");
				question8(program.getAccounts());
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
		Scanner scanner = new Scanner(System.in);
		int[] inps = getIntInput(scanner, 3);
		scanner.close();
		System.out.printf("\nInputs: %d, %d, %d\n", inps[0], inps[1], inps[2]);
	}

	public static void question2() {
		System.out.println("Question 2");
		Scanner scanner = new Scanner(System.in);
		float[] inps = getFloatInput(scanner, 2);
		scanner.close();
		System.out.printf("\nInputs: %.2f, %.2f\n", inps[0], inps[1]);
	}

	public static void question3() {
		System.out.println("Question 3");
		Scanner scanner = new Scanner(System.in);
		System.out.print("\nEnter fullname: ");
		String inp = scanner.nextLine();
		scanner.close();
		System.out.printf("Fullname: %s\n", inp);
	}

	public static void question5() {
		System.out.println("Question 4");
		Scanner scanner = new Scanner(System.in);
		String email, username, fullname;
		int departmentId, positionId;
		System.out.print("\nEnter email: ");
		email = scanner.nextLine();
		System.out.print("\nEnter username: ");
		username = scanner.nextLine();
		System.out.print("\nEnter fullname: ");
		fullname = scanner.nextLine();
		System.out.printf("\nEnter department ID(0-%d): ", Department.values().length - 1);
		departmentId = scanner.nextInt();
		System.out.printf("\nEnter position ID(0-%d): ", Position.values().length - 1);
		positionId = scanner.nextInt();
		scanner.close();

		Department dep = Department.getDepartmentById(departmentId);

		Position pos = Position.getPositionById(positionId);

		Account newAcc = new Account(email, username, fullname, dep, pos);
		System.out.printf("\nNew account: %d, %s, %s, %s, %s, %s\n", newAcc.getId(), newAcc.getEmail(),
				newAcc.getUsername(), newAcc.getFullname(), newAcc.getDepartment().getName(),
				newAcc.getPosition().getName());
	}

	public static void question8(Account[] accounts) {
		System.out.println("Question 8");
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("\nSelect an option:\n");
			System.out.printf("%4s(1) Create an account\n", " ");
			System.out.printf("%4s(2) Create a question\n", " ");
			System.out.printf("%4s(0) Exit\n", " ");
			System.out.print("Input: ");
			
			try {
				int inp = scanner.nextInt();
				scanner.nextLine();
				switch (inp) {
				case 1:
					System.out.println("Creating new account");
					System.out.print("\nEnter email: ");
					String email = scanner.nextLine();
					System.out.print("\nEnter username: ");
					String username = scanner.nextLine();
					System.out.print("\nEnter fullname: ");
					String fullname = scanner.nextLine();
					System.out.print("\nEnter department ID: ");
					int depId = scanner.nextInt();
					scanner.nextLine();
					System.out.print("\nEnter position ID: ");
					int posId = scanner.nextInt();
					scanner.nextLine();
					Account acc = new Account(email, username, fullname, Department.getDepartmentById(depId), Position.getPositionById(posId));
					System.out.printf("\nNew account: %d, %s, %s, %s, %s, %s\n", acc.getId(), acc.getEmail(),
							acc.getUsername(), acc.getFullname(), acc.getDepartment().getName(),
							acc.getPosition().getName());
					break;
				case 2:
					System.out.println("Creating new question");
					System.out.print("\nEnter your username: ");
					String usr = scanner.nextLine();
					Account curUsr = getAccountByUsername(accounts, usr);
					if (curUsr == null) {
						System.out.println("User not found.");
						break;
					}
					
					System.out.print("\nEnter question: ");
					String q = scanner.nextLine();
					System.out.printf("\nEnter question category ID(0-%d): ", CategoryQuestion.values().length - 1);
					int cqId = scanner.nextInt();
					scanner.nextLine();
					System.out.printf("\nEnter question type ID(0-%d): ", TypeQuestion.values().length - 1);
					int qtId = scanner.nextInt();
					scanner.nextLine();
					Question nwQ = new Question(q, CategoryQuestion.getCategoryQuestionById(cqId), TypeQuestion.getTypeQuestionById(qtId), curUsr);
					System.out.printf("New Question: %d, %s, %s, %s, %s", nwQ.getId(), nwQ.getContent(), nwQ.getCategory().getName(), nwQ.getType().getName(), nwQ.getCreator().getUsername());
					break;
				case 0:
					System.out.println("Exiting...");
					scanner.close();
					return;
				default:
					System.out.println("Invalid input, try again.");
					break;
				}
			} catch (Exception e) {
				System.out.println("Invalid input, try again.");
				scanner.nextLine();
				continue;
			}
		}
	}

	public static int[] getIntInput(Scanner scanner, int numberOfInputs) {
		int[] inps = new int[numberOfInputs];
		int i = 0;
		while (i < numberOfInputs) {
			if (i == 0)
				System.out.print("\nEnter first number: ");
			else
				System.out.print("\nEnter next number: ");

			try {
				inps[i] = scanner.nextInt();
			} catch (Exception e) {
				System.out.println("Invalid input, try again.");
				scanner.nextLine();
				continue;
			}
			++i;
		}

		return inps;
	}

	public static float[] getFloatInput(Scanner scanner, int numberOfInputs) {
		float[] inps = new float[numberOfInputs];
		int i = 0;
		while (i < numberOfInputs) {
			if (i == 0)
				System.out.print("\nEnter first float: ");
			else
				System.out.print("\nEnter next float: ");

			try {
				inps[i] = scanner.nextFloat();
			} catch (Exception e) {
				System.out.println("Invalid input, try again.");
				scanner.nextLine();
				continue;
			}
			++i;
		}
		return inps;
	}

	public static Account getAccountByUsername(Account[] accounts, String username) {
		for (Account acc : accounts) {
			if (acc.getUsername().equals(username))
				return acc;
		}
		return null;
	}
}
