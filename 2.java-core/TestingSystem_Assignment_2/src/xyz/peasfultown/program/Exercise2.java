package xyz.peasfultown.program;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import xyz.peasfultown.entity.Account;

public class Exercise2 {
	public static void main(String[] args) {
		Program program = new Program();
		Account[] accounts = program.getAccounts();
		
		question1();
		question2();
		question3();
		question4();
		question5();
		question6(accounts);
	}

	public static void question1() {
		System.out.println("Question 1");
		int num = 5;
		System.out.printf("%d", num);
	}

	public static void question2() {
		System.out.println("Question 2");
		int num = 100000000;
		System.out.printf("%,d", num);
	}

	public static void question3() {
		System.out.println("Question 3");
		double num = 5.567098;
		System.out.printf("%.4f", num);
	}

	public static void question4() {
		System.out.println("Question 4");
		String name = "Nguyen Van A";
		System.out.printf("My name is %s and I am single%n", name);
	}

	public static void question5() {
		System.out.println("Question 5");
		System.out.printf("%1$td/%1$tm/%1$tY %1$tHh:%1$tMp:%1$tSs%n", LocalDateTime.now());
	}

	public static void question6(Account[] accounts) {
		System.out.println("Question 6");
		System.out.printf("|%4s|%20s|%10s|%20s|%25s|%20s|%30s|%n", "ID", "Email", "Username", "Fullname", "Department",
				"Position", "Groups");
		for (Account acc : accounts) {
			System.out.printf("|%4d|%20s|%10s|%20s|%25s|%20s|%30s|%n", acc.getId(), acc.getEmail(), acc.getUsername(),
					acc.getFullname(), acc.getDepartment().getName(), acc.getPosition().getName(),
					String.join(", ", Arrays.stream(acc.getGroups()).map(g -> g.getName()).toArray(String[]::new)));
		}
	}
}
