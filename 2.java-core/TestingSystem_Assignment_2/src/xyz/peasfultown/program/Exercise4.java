package xyz.peasfultown.program;

import xyz.peasfultown.entity.Account;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class Exercise4 {
	public static void main(String[] args) {
		Random rand = new Random();
		Program program = new Program();
		Account[] accounts = program.getAccounts();
		
		question1(rand);
		question2(rand);
		question3(accounts, rand);
		question4(rand);
		question5(rand);
	}
	
	public static void question1(Random rand) {
		System.out.println("Question 1");
		System.out.println(rand.nextInt());
	}
	
	public static void question2(Random rand) {
		System.out.println("Question 2");
		System.out.println(rand.nextFloat());
	}
	
	public static void question3(Account[] accounts, Random rand) {
		System.out.println("Question 3");
		int randId = rand.nextInt(accounts.length);
		System.out.println(accounts[randId].getFullname());
	}
	
	public static void question4(Random rand) {
		System.out.println("Question 4");
		long orig = LocalDate.of(1995, 7, 24).toEpochDay();
		long bound = LocalDate.of(1995, 12, 20).toEpochDay();
		long selection = rand.nextLong(orig, bound);
		System.out.printf("Randomly selected time: %tF%n", LocalDate.ofEpochDay(selection));
	}

	public static void question5(Random rand) {
		long now = LocalDate.now().toEpochDay();
		long past = LocalDate.now().minusYears(1).toEpochDay();
		
		System.out.printf("Selected day: %tF%n", LocalDate.ofEpochDay(rand.nextLong(past, now)));
	}
	
	public static void question6(Random rand) {
		long now = LocalDate.now().toEpochDay();
		System.out.printf("Selected day: %tF%n", LocalDate.ofEpochDay(rand.nextLong(now)));
	}
	
	public static void question7(Random rand) {
		System.out.printf("Random 3 digit number: %d%n", rand.nextInt(100, 999));
	}
}
