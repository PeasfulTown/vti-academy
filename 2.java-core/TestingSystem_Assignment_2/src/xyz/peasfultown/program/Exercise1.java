package xyz.peasfultown.program;

import java.util.Arrays;
import java.util.Scanner;

import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.Group;
import xyz.peasfultown.entity.Position;
import xyz.peasfultown.utils.Prompt;
import xyz.peasfultown.entity.Department;

public class Exercise1 {
	private Scanner scanner;
	private Data data;

	public Exercise1(Scanner scanner, Data data) {
		this.scanner = scanner;
		this.data = data;
	}

	public void run() {
		while (true) {
			System.out.println("Exercise 1 Options:");
			System.out.printf("%4s%s\n", " ", "(1) Question 1");
			System.out.printf("%4s%s\n", " ", "(2) Question 2");
			System.out.printf("%4s%s\n", " ", "(3) Question 3");
			System.out.printf("%4s%s\n", " ", "(4) Question 4");
			System.out.printf("%4s%s\n", " ", "(5) Question 5");
			System.out.printf("%4s%s\n", " ", "(6) Question 6");
			System.out.printf("%4s%s\n", " ", "(7) Question 7");
			System.out.printf("%4s%s\n", " ", "(8) Question 8");
			System.out.printf("%4s%s\n", " ", "(9) Question 9");
			System.out.printf("%4s%s\n", " ", "(10) Question 10");
			System.out.printf("%4s%s\n", " ", "(11) Question 11");
			System.out.printf("%4s%s\n", " ", "(12) Question 12");
			System.out.printf("%4s%s\n", " ", "(13) Question 13");
			System.out.printf("%4s%s\n", " ", "(14) Question 14");
			System.out.printf("%4s%s\n", " ", "(15) Question 15");
			System.out.printf("%4s%s\n", " ", "(16) Question 16 (more)");
			System.out.printf("%4s%s\n", " ", "(17) Question 17 (more)");

			System.out.printf("%4s%s\n", " ", "(0) Exit Exercise 1");

			int usrOp = Prompt.getIntegerUserInput(scanner, "\nEnter option: ");
			System.out.println();
			switch (usrOp) {
			case 0:
				System.out.println("\nExitting Exercise 1\n");
				return;
			case 1:
				System.out.println("Selected Question 1");
				question1(data.getAccounts());
				break;
			case 2:
				System.out.println("Selected Question 2");
				question2(data.getAccounts());
				break;
			case 3:
				System.out.println("Selected Question 3");
				question3(data.getAccounts());
				break;
			case 4:
				System.out.println("Selected Question 4");
				question4(data.getAccounts());
				break;
			case 5:
				System.out.println("Selected Question 5");
				question5(data.getGroups());
				break;
			case 6:
				System.out.println("Selected Question 6");
				question6(data.getAccounts());
				break;
			case 7:
				System.out.println("Selected Question 7");
				question7(data.getAccounts());
				break;
			case 8:
				System.out.println("Selected Question 8");
				question8(data.getAccounts());
				break;
			case 9:
				System.out.println("Selected Question 9");
				question9();
				break;
			case 10:
				System.out.println("Selected Question 10");
				question10(data.getAccounts());
				break;
			case 11:
				System.out.println("Selected Question 11");
				question11();
				break;
			case 12:
				System.out.println("Selected Question 12");
				question12();
				break;
			case 13:
				System.out.println("Selected Question 13");
				question13(data.getAccounts());
				break;
			case 14:
				System.out.println("Selected Question 14");
				question14(data.getAccounts());
				break;
			case 15:
				System.out.println("Selected Question 15");
				question15();
				break;
			case 16:
				System.out.println("Selected Question 16");
				runQ16();
				break;
			case 17:
				System.out.println("Selected Question 17");
				runQ17();
				break;
			default:
				System.out.println("Invalid option, try again.");
				break;
			}
			Prompt.continuePrompt(scanner);
		}
	}

	public void runQ16() {
		while (true) {
			System.out.println("Question 16 options:");
			System.out.printf("%4s%s\n", " ", "(1) Question 10");
			System.out.printf("%4s%s\n", " ", "(2) Question 11");
			System.out.printf("%4s%s\n", " ", "(3) Question 12");
			System.out.printf("%4s%s\n", " ", "(4) Question 13");
			System.out.printf("%4s%s\n", " ", "(5) Question 14");
			System.out.printf("%4s%s\n", " ", "(6) Question 15");

			System.out.printf("%4s%s\n", " ", "(0) Back");

			int usrOp = Prompt.getIntegerUserInput(scanner, "\nEnter option: ");
			switch (usrOp) {
			case 0:
				System.out.println("Exiting question 16");
				return;
			case 1:
				question16_10(data.getAccounts());
				break;
			case 2:
				question16_11();
				break;
			case 3:
				question16_12();
				break;
			case 4:
				question16_13(data.getAccounts());
				break;
			case 5:
				question16_14(data.getAccounts());
				break;
			case 6:
				question16_15();
				break;
			default:
				System.out.println("Invalid option, try again.");
				break;
			}
			Prompt.continuePrompt(scanner);
		}
	}

	public void runQ17() {
		while (true) {
			System.out.println("Question 17 options:");
			System.out.printf("%4s%s\n", " ", "(1) Question 10");
			System.out.printf("%4s%s\n", " ", "(2) Question 11");
			System.out.printf("%4s%s\n", " ", "(3) Question 12");
			System.out.printf("%4s%s\n", " ", "(4) Question 13");
			System.out.printf("%4s%s\n", " ", "(5) Question 14");
			System.out.printf("%4s%s\n", " ", "(6) Question 15");

			System.out.printf("%4s%s\n", " ", "(0) Back");

			int usrOp = Prompt.getIntegerUserInput(scanner, "\nEnter option: ");
			switch (usrOp) {
			case 0:
				System.out.println("Exiting question 17");
				return;
			case 1:
				question17_10(data.getAccounts());
				break;
			case 2:
				question17_11();
				break;
			case 3:
				question17_12();
				break;
			case 4:
				question17_13(data.getAccounts());
				break;
			case 5:
				question17_14(data.getAccounts());
				break;
			case 6:
				question17_15();
				break;
			default:
				System.out.println("Invalid option, try again.");
				break;
			}
			Prompt.continuePrompt(scanner);
		}
	}

	private void question1(Account[] accounts) {
		System.out.println("Question 1");
		Account acc = getAccountById(accounts, 2);
		if (acc.getDepartment() == null)
			System.out.println("This employee does not have a department");
		else
			System.out.printf("This employee's department is: %s%n", acc.getDepartment().getName());
	}

	private void question2(Account[] accounts) {
		System.out.println("Question 2");
		Account acc = getAccountById(accounts, 2);
		Group[] accGroups = acc.getGroups();

		if (accGroups.length != 0) {
			if (accGroups.length <= 2) {
				String[] groupNames = Arrays.stream(accGroups).map(g -> g.getName()).toArray(String[]::new);
				for (int i = 0; i < accGroups.length; i++) {
					System.out.printf("Employee is in groups: ", String.join(", ", groupNames));
				}
			} else if (accGroups.length == 3) {
				System.out.println("This employee is a VIP, in is many groups");
			} else if (accGroups.length >= 4) {
				System.out.println("This employee is nosy, is in all the groups");
			}
		} else {
			System.out.println("This employee is not in a group");
		}
	}

	private void question3(Account[] accounts) {
		System.out.println("Question 3");
		Account acc = getAccountById(accounts, 2);
		System.out
				.println(acc.getDepartment() != null ? "This employee's department is: " + acc.getDepartment().getName()
						: "This employee does not have a department");
	}

	private void question4(Account[] accounts) {
		System.out.println("Question 4");
		Account acc = getAccountById(accounts, 0);
		System.out
				.println(acc.getPosition() != null && acc.getPosition() == Position.DEV ? "This employee is a developer"
						: "This employee is not a developer");
	}

	private void question5(Group[] groups) {
		System.out.println("Question 5");
		Group grp = getGroupById(groups, 0);
		switch (grp.getAccounts().length) {
		case 1:
			System.out.println("Group has 1 member");
			break;
		case 2:
			System.out.println("Group has 2 members");
			break;
		case 3:
			System.out.println("Group has 3 members");
			break;
		default:
			System.out.println("Group has many members");
			break;
		}
	}

	private void question6(Account[] accounts) {
		System.out.println("Question 6");
		Group[] accGrps = getAccountById(accounts, 2).getGroups();
		switch (accGrps.length) {
		case 0:
			System.out.println("Employee is not in any group");
			break;
		case 1:
		case 2:
			String[] grpStr = Arrays.stream(accGrps).map(g -> g.getName()).toArray(String[]::new);
			System.out.printf("Employee is in group(s): %s%n", String.join(", ", grpStr));
			break;
		case 3:
			System.out.println("This employee is a VIP, is in many groups");
			break;
		default:
			System.out.println("This employee is nosy, is in all the groups");
			break;
		}
	}

	private void question7(Account[] accounts) {
		System.out.println("Question 7");
		Account acc = getAccountById(accounts, 0);
		switch (acc.getPosition()) {
		case Position.DEV:
			System.out.println("This employee is a developer");
			break;
		default:
			System.out.println("This employee is not a developer");
			break;
		}
	}

	private void question8(Account[] accounts) {
		System.out.println("Question 8");
		System.out.printf("%-20s %-20s %-15s%n", "Email", "Fullname", "Department");

		for (Account acc : accounts) {
			System.out.printf("%-20s %-20s %-15s%n", acc.getEmail(), acc.getFullname(), acc.getDepartment().getName());
		}
	}

	private void question9() {
		System.out.println("Question 9");
		System.out.printf("%-4s %-15s%n", "ID", "Department Name");

		for (Department dep : Department.values()) {
			System.out.printf("%-4d %-15s%n", dep.getId(), dep.getName());
		}
	}

	private void question10(Account[] accounts) {
		System.out.println("Question 10");
		for (int i = 0; i < accounts.length; i++) {
			System.out.printf("Information for account #%d:%n", accounts[i].getId());
			System.out.printf("Email: %s%n", accounts[i].getEmail());
			System.out.printf("Fullname: %s%n", accounts[i].getFullname());
			System.out.printf("Department: %s%n",
					accounts[i].getDepartment() != null ? accounts[i].getDepartment().getName() : "None");
			System.out.printf("%n%n");
		}
	}

	private void question11() {
		System.out.println("Question 11");
		for (int i = 0; i < Department.values().length; i++) {
			System.out.printf("Information for Department #%d%n", Department.values()[i].getId());
			System.out.printf("%4s ID: %d%n", " ", Department.values()[i].getId());
			System.out.printf("%4s Name: %s%n", " ", Department.values()[i].getName());
		}
	}

	private void question12() {
		System.out.println("Question 12");
		for (int i = 0; i < 2; i++) {
			System.out.printf("Information for Department #%d%n", Department.values()[i].getId());
			System.out.printf("%4s ID: %d%n", " ", Department.values()[i].getId());
			System.out.printf("%4s Name: %s%n", " ", Department.values()[i].getName());
		}
	}

	private void question13(Account[] accounts) {
		System.out.println("Question 13");
		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i].getId() == 1)
				continue;
			System.out.printf("Information for account #%d:%n", accounts[i].getId());
			System.out.printf("Email: %s%n", accounts[i].getEmail());
			System.out.printf("Fullname: %s%n", accounts[i].getFullname());
			System.out.printf("Department: %s%n",
					accounts[i].getDepartment() != null ? accounts[i].getDepartment().getName() : "None");
			System.out.printf("%n%n");
		}
	}

	private void question14(Account[] accounts) {
		System.out.println("Question 14");
		for (int i = 0; i < accounts.length; i++) {
			if (accounts[i].getId() >= 4)
				continue;
			System.out.printf("Information for account #%d:%n", accounts[i].getId());
			System.out.printf("Email: %s%n", accounts[i].getEmail());
			System.out.printf("Fullname: %s%n", accounts[i].getFullname());
			System.out.printf("Department: %s%n",
					accounts[i].getDepartment() != null ? accounts[i].getDepartment().getName() : "None");
			System.out.printf("%n%n");
		}
	}

	private void question15() {
		System.out.println("Question 15");
		for (int i = 0; i <= 20; i++) {
			if (i % 2 == 0)
				System.out.println(i);
		}

		/*
		 * for (int i = 0; i <= 20; i+=2) { System.out.println(i); }
		 */
	}

	private void question16_10(Account[] accounts) {
		System.out.println("Question 16_10");
		int i = 0;
		while (i < accounts.length) {
			System.out.printf("Information for account #%d:%n", accounts[i].getId());
			System.out.printf("Email: %s%n", accounts[i].getEmail());
			System.out.printf("Fullname: %s%n", accounts[i].getFullname());
			System.out.printf("Department: %s%n",
					accounts[i].getDepartment() != null ? accounts[i].getDepartment().getName() : "None");
			System.out.printf("%n%n");
			++i;
		}
	}

	private void question16_11() {
		System.out.println("Question 16_11");
		int i = 0;
		while (i < Department.values().length) {
			System.out.printf("Information for Department #%d%n", Department.values()[i].getId());
			System.out.printf("%4s ID: %d%n", " ", Department.values()[i].getId());
			System.out.printf("%4s Name: %s%n", " ", Department.values()[i].getName());
			++i;
		}
	}

	private void question16_12() {
		System.out.println("Question 16_12");
		int i = 0;
		while (i < 2) {
			System.out.printf("Information for Department #%d%n", Department.values()[i].getId());
			System.out.printf("%4s ID: %d%n", " ", Department.values()[i].getId());
			System.out.printf("%4s Name: %s%n", " ", Department.values()[i].getName());
			++i;
		}
	}

	private void question16_13(Account[] accounts) {
		System.out.println("Question 16_13");
		int i = 0;
		while (i < accounts.length) {
			if (accounts[i].getId() == 2)
				continue;
			System.out.printf("Information for account #%d:%n", accounts[i].getId());
			System.out.printf("Email: %s%n", accounts[i].getEmail());
			System.out.printf("Fullname: %s%n", accounts[i].getFullname());
			System.out.printf("Department: %s%n",
					accounts[i].getDepartment() != null ? accounts[i].getDepartment().getName() : "None");
			System.out.printf("%n%n");
			++i;
		}
	}

	private void question16_14(Account[] accounts) {
		System.out.println("Question 16_14");
		int i = 0;
		while (i < accounts.length) {
			if (accounts[i].getId() >= 4)
				continue;
			System.out.printf("Information for account #%d:%n", accounts[i].getId());
			System.out.printf("Email: %s%n", accounts[i].getEmail());
			System.out.printf("Fullname: %s%n", accounts[i].getFullname());
			System.out.printf("Department: %s%n",
					accounts[i].getDepartment() != null ? accounts[i].getDepartment().getName() : "None");
			System.out.printf("%n%n");
			++i;
		}
	}

	private void question16_15() {
		System.out.println("Question 16_15");
		int i = 0;
		while (i <= 20) {
			if (i % 2 == 0)
				System.out.println(i++);
		}
	}

	private void question17_10(Account[] accounts) {
		System.out.println("Question 17_10");
		int i = 0;
		do {
			System.out.printf("Information for account #%d:%n", accounts[i].getId());
			System.out.printf("Email: %s%n", accounts[i].getEmail());
			System.out.printf("Fullname: %s%n", accounts[i].getFullname());
			System.out.printf("Department: %s%n",
					accounts[i].getDepartment() != null ? accounts[i].getDepartment().getName() : "None");
			System.out.printf("%n%n");
			++i;
		} while (i < accounts.length);
	}

	private void question17_11() {
		System.out.println("Question 17_11");
		int i = 0;
		do {
			System.out.printf("Information for Department #%d%n", Department.values()[i].getId());
			System.out.printf("%4s ID: %d%n", " ", Department.values()[i].getId());
			System.out.printf("%4s Name: %s%n", " ", Department.values()[i].getName());
			++i;
		} while (i < Department.values().length);
	}

	private void question17_12() {
		System.out.println("Question 17_12");
		int i = 0;
		do {
			System.out.printf("Information for Department #%d%n", Department.values()[i].getId());
			System.out.printf("%4s ID: %d%n", " ", Department.values()[i].getId());
			System.out.printf("%4s Name: %s%n", " ", Department.values()[i].getName());
			++i;
		} while (i < 2);
	}

	private void question17_13(Account[] accounts) {
		System.out.println("Question 17_13");
		int i = 0;
		do {
			if (accounts[i].getId() == 2)
				continue;
			System.out.printf("Information for account #%d:%n", accounts[i].getId());
			System.out.printf("Email: %s%n", accounts[i].getEmail());
			System.out.printf("Fullname: %s%n", accounts[i].getFullname());
			System.out.printf("Department: %s%n",
					accounts[i].getDepartment() != null ? accounts[i].getDepartment().getName() : "None");
			System.out.printf("%n%n");
			++i;
		} while (i < accounts.length);
	}

	private void question17_14(Account[] accounts) {
		System.out.println("Question 17_14");
		int i = 0;
		do {
			if (accounts[i].getId() >= 4)
				continue;
			System.out.printf("Information for account #%d:%n", accounts[i].getId());
			System.out.printf("Email: %s%n", accounts[i].getEmail());
			System.out.printf("Fullname: %s%n", accounts[i].getFullname());
			System.out.printf("Department: %s%n",
					accounts[i].getDepartment() != null ? accounts[i].getDepartment().getName() : "None");
			System.out.printf("%n%n");
			++i;
		} while (i < accounts.length);
	}

	private void question17_15() {
		System.out.println("Question 17_15");
		int i = 0;
		do {
			if (i % 2 == 0)
				System.out.println(i++);
		} while (i <= 20);
	}

	private Account getAccountById(Account[] accounts, int id) {
		for (Account acc : accounts) {
			if (acc.getId() == id)
				return acc;
		}
		return null;
	}

	private Group getGroupById(Group[] groups, int id) {
		for (Group grp : groups) {
			if (grp.getId() == id)
				return grp;
		}
		return null;
	}
}
