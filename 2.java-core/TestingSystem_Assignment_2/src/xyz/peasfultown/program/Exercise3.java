package xyz.peasfultown.program;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

import xyz.peasfultown.entity.Exam;
import xyz.peasfultown.utils.Prompt;

public class Exercise3 {
	private Scanner scanner;
	private Data data;
	
	public Exercise3(Scanner scanner, Data data) {
		this.scanner = scanner;
		this.data = data;
	}
	
	public void run() {
		while (true) {
			System.out.println("Exercise 3 Options:");
			System.out.printf("%4s%s\n", " ", "(1) Question 1");
			System.out.printf("%4s%s\n", " ", "(2) Question 2");
			System.out.printf("%4s%s\n", " ", "(3) Question 3");
			System.out.printf("%4s%s\n", " ", "(4) Question 4");
			System.out.printf("%4s%s\n", " ", "(5) Question 5");

			System.out.printf("%4s%s\n", " ", "(0) Exit Exercise 3");

			int usrOp = Prompt.getIntegerUserInput(scanner, "\nEnter option: ");
			System.out.println();
			switch (usrOp) {
			case 0:
				System.out.println("\nExitting Exercise 3\n");
				return;
			case 1:
				System.out.println("Selected Question 1");
				question1(data.getExams());
				break;
			case 2:
				System.out.println("Selected Question 2");
				question2(data.getExams());
				break;
			case 3:
				System.out.println("Selected Question 3");
				question3(data.getExams());
				break;
			case 4:
				System.out.println("Selected Question 4");
				question4(data.getExams());
				break;
			case 5:
				System.out.println("Selected Question 5");
				question5(data.getExams());
				break;
			default:
				System.out.println("Invalid option, try again.");
				break;
			}
			Prompt.continuePrompt(scanner);
		}
	}
	
	public static void question1(Exam[] exams) {
		System.out.println("Question 1");

		Exam e = getExamById(exams, 0);
		String[] quStr = Arrays.stream(e.getQuestions()).map( q -> q.getContent()).toArray(String[]::new);
		System.out.printf(new Locale("vi")
				, "%1$d, %2$s, %3$s, %4$s, %5$s, %6$s, %7$tA %7$td %7$tb %7$tY %7$tr, %8$s%n"
				, e.getId(), e.getCode(), e.getTitle(), e.getCategory().getName()
				, e.getDuration()
				, e.getCreator().getUsername()
				, e.getCreateDate()
				, "Questions: " + String.join(", ", quStr));
	}

	public static void question2(Exam[] exams) {
		System.out.println("Question 2");
		for (int i = 0; i < exams.length; i++) {
			System.out.printf("ID-%d, Create Date: %2$tY-%2$tm-%2$td %2$tHh:%2$tMp:%2$tSg%n", exams[i].getId(), exams[i].getCreateDate());
		}
	}

	public static void question3(Exam[] exams) {
		System.out.println("Question 3");
		for (int i = 0; i < exams.length; i++) {
			System.out.printf("ID-%d, Create Year: %tY%n", exams[i].getId(), exams[i].getCreateDate());
		}
	}

	public static void question4(Exam[] exams) {
		System.out.println("Question 4");
		for (int i = 0; i < exams.length; i++) {
			System.out.printf("ID-%1$d, Create Date: %2$tm-%2$tY%n", exams[i].getId(), exams[i].getCreateDate());
		}
	}
	
	public static void question5(Exam[] exams) {
		System.out.println("Question 5");
		for (int i = 0; i < exams.length; i++) {
			System.out.printf("ID-%1$d, Create Date: %2$tm-%2$td%n", exams[i].getId(), exams[i].getCreateDate());
		}
	}
	
	public static Exam getExamById(Exam[] exams, int id) {
		for (int i = 0; i < exams.length; i++) {
			if (exams[i].getId() == id)
				return exams[i];
		}
		return null;
	}
}
