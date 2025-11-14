package xyz.peasfultown.program;

import java.util.Arrays;

import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.Group;
import xyz.peasfultown.entity.Position;
import xyz.peasfultown.entity.Department;

public class Exercise1 {
	public static void main(String... args) {
		Program program = new Program();
		Account[] accounts = program.getAccounts();
		Group[] groups = program.getGroups();

		// if statement
		question1(accounts);
		question2(accounts);
		question3(accounts);
		question4(accounts);
		
		// switch case
		question5(groups);
		question6(accounts);
		question7(accounts);
		
		// foreach
		question8(accounts);
		question9();
		
		// for
		question10(accounts);
		question11();
		question12();
		question13(accounts);
		question14(accounts);
		question15();
		
		// while
		question16_10(accounts);
		question16_11();
		question16_12();
		question16_13(accounts);
		question16_14(accounts);
		question16_15();
		
		// do while
		question17_10(accounts);
		question17_11();
		question17_12();
		question17_13(accounts);
		question17_14(accounts);
		question17_15();
	}

	private static void question1(Account[] accounts) {
		System.out.println("Question 1");
		Account acc = getAccountById(accounts, 2);
		if (acc.getDepartment() == null)
			System.out.println("This employee does not have a department");
		else
			System.out.printf("This employee's department is: %s%n", acc.getDepartment().getName());
	}

	private static void question2(Account[] accounts) {
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

	private static void question3(Account[] accounts) {
		System.out.println("Question 3");
		Account acc = getAccountById(accounts, 2);
		System.out
				.println(acc.getDepartment() != null ? "This employee's department is: " + acc.getDepartment().getName()
						: "This employee does not have a department");
	}

	private static void question4(Account[] accounts) {
		System.out.println("Question 4");
		Account acc = getAccountById(accounts, 0);
		System.out
				.println(acc.getPosition() != null && acc.getPosition() == Position.DEV ? "This employee is a developer"
						: "This employee is not a developer");
	}

	private static void question5(Group[] groups) {
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

	private static void question6(Account[] accounts) {
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

	private static void question7(Account[] accounts) {
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

	private static void question8(Account[] accounts) {
		System.out.println("Question 8");
		System.out.printf("%-20s %-20s %-15s%n", "Email", "Fullname", "Department");

		for (Account acc : accounts) {
			System.out.printf("%-20s %-20s %-15s%n", acc.getEmail(), acc.getFullname(), acc.getDepartment().getName());
		}
	}

	private static void question9() {
		System.out.println("Question 9");
		System.out.printf("%-4s %-15s%n", "ID", "Department Name");

		for (Department dep : Department.values()) {
			System.out.printf("%-4d %-15s%n", dep.getId(), dep.getName());
		}
	}

	private static void question10(Account[] accounts) {
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

	private static void question11() {
		System.out.println("Question 11");
		for (int i = 0; i < Department.values().length; i++) {
			System.out.printf("Information for Department #%d%n", Department.values()[i].getId());
			System.out.printf("%4s ID: %d%n", " ", Department.values()[i].getId());
			System.out.printf("%4s Name: %s%n", " ", Department.values()[i].getName());
		}
	}

	private static void question12() {
		System.out.println("Question 12");
		for (int i = 0; i < 2; i++) {
			System.out.printf("Information for Department #%d%n", Department.values()[i].getId());
			System.out.printf("%4s ID: %d%n", " ", Department.values()[i].getId());
			System.out.printf("%4s Name: %s%n", " ", Department.values()[i].getName());
		}
	}

	private static void question13(Account[] accounts) {
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

	private static void question14(Account[] accounts) {
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

	private static void question15() {
		System.out.println("Question 15");
		for (int i = 0; i <= 20; i++) {
			if (i % 2 == 0)
				System.out.println(i);
		}

		/*
		 * for (int i = 0; i <= 20; i+=2) { System.out.println(i); }
		 */
	}

	private static void question16_10(Account[] accounts) {
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

	private static void question16_11() {
		System.out.println("Question 16_11");
		int i = 0;
		while (i < Department.values().length) {
			System.out.printf("Information for Department #%d%n", Department.values()[i].getId());
			System.out.printf("%4s ID: %d%n", " ", Department.values()[i].getId());
			System.out.printf("%4s Name: %s%n", " ", Department.values()[i].getName());
			++i;
		}
	}

	private static void question16_12() {
		System.out.println("Question 16_12");
		int i = 0;
		while (i < 2) {
			System.out.printf("Information for Department #%d%n", Department.values()[i].getId());
			System.out.printf("%4s ID: %d%n", " ", Department.values()[i].getId());
			System.out.printf("%4s Name: %s%n", " ", Department.values()[i].getName());
			++i;
		}
	}

	private static void question16_13(Account[] accounts) {
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

	private static void question16_14(Account[] accounts) {
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

	private static void question16_15() {
		System.out.println("Question 16_15");
		int i = 0;
		while (i <= 20) {
			if (i % 2 == 0)
				System.out.println(i++);
		}
	}
	
	private static void question17_10(Account[] accounts) {
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
	
	private static void question17_11() {
		System.out.println("Question 17_11");
		int i = 0;
		do {
			System.out.printf("Information for Department #%d%n", Department.values()[i].getId());
			System.out.printf("%4s ID: %d%n", " ", Department.values()[i].getId());
			System.out.printf("%4s Name: %s%n", " ", Department.values()[i].getName());
			++i;
		} while (i < Department.values().length);
	}
	
	private static void question17_12() {
		System.out.println("Question 17_12");
		int i = 0;
		do {
			System.out.printf("Information for Department #%d%n", Department.values()[i].getId());
			System.out.printf("%4s ID: %d%n", " ", Department.values()[i].getId());
			System.out.printf("%4s Name: %s%n", " ", Department.values()[i].getName());
			++i;
		} while (i < 2);
	}
	
	private static void question17_13(Account[] accounts) {
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
	
	private static void question17_14(Account[] accounts) {
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
	
	private static void question17_15() {
		System.out.println("Question 17_15");
		int i = 0;
		do {
			if (i % 2 == 0)
				System.out.println(i++);
		} while (i <= 20);
	}

	private static Account getAccountById(Account[] accounts, int id) {
		for (Account acc : accounts) {
			if (acc.getId() == id)
				return acc;
		}
		return null;
	}

	private static Group getGroupById(Group[] groups, int id) {
		for (Group grp : groups) {
			if (grp.getId() == id)
				return grp;
		}
		return null;
	}
}
