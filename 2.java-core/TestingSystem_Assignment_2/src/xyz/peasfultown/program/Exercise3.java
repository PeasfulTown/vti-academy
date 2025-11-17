package xyz.peasfultown.program;

import java.util.Arrays;
import java.util.Locale;

import xyz.peasfultown.entity.Exam;

public class Exercise3 {
	public static void main(String[] args) {
		Program program = new Program();
		Exam[] exams = program.getExams();
		
		question1(exams);
		question2(exams);
		question3(exams);
		question4(exams);
		question5(exams);
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
