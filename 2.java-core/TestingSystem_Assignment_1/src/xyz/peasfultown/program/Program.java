package xyz.peasfultown.program;

import java.time.Duration;
import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.Answer;
import xyz.peasfultown.entity.CategoryQuestion;
import xyz.peasfultown.entity.Department;
import xyz.peasfultown.entity.Exam;
import xyz.peasfultown.entity.Group;
import xyz.peasfultown.entity.Position;
import xyz.peasfultown.entity.Question;
import xyz.peasfultown.entity.TypeQuestion;
import java.util.Arrays;
import java.util.List;

public class Program {
	public static void main(String[] args) {		
		Account acc1 = new Account("acc1@gmail.com", "acc1", "acc1FullName", Department.SALES, Position.DEV);
		Account acc2 = new Account("acc2@gmail.com", "acc2", "acc2FullName", Department.WAITING_ROOM, Position.INTERN);
		Account acc3 = new Account("acc3@gmail.com", "acc3", "acc3FullName", Department.CUSTOMER_SERVICE, Position.DEV);
		Account acc4 = new Account("acc4@gmail.com", "acc4", "acc4FullName", Department.MARKETING, Position.PM);
		Account acc5 = new Account("acc5@gmail.com", "acc5", "acc5FullName", Department.INFORMATION_TECHNOLOGY, Position.DEV);
		
		Group grp1 = new Group("Grp1", acc2);
		Group grp2 = new Group("Grp2", acc3);
		Group grp3 = new Group("Grp3", acc1);

		Question q1 = new Question("Question1", CategoryQuestion.JAVA, TypeQuestion.MULTIPLE_CHOICE, acc1);
		Question q2 = new Question("Question1", CategoryQuestion.SQL, TypeQuestion.TRUE_OR_FALSE, acc2);
		Question q3 = new Question("Question1", CategoryQuestion.RUBY, TypeQuestion.MULTIPLE_CHOICE, acc4);
		
		Answer ans1 = new Answer("Answer1", q3, false);
		Answer ans2 = new Answer("Answer1", q1, false);
		Answer ans3 = new Answer("Answer1", q2, true);
		
		Exam exam1 = new Exam("ExamCode1", "ExamTitle1", CategoryQuestion.JAVA, Duration.ofMinutes(45), acc1);
		Exam exam2 = new Exam("ExamCode2", "ExamTitle2", CategoryQuestion.LINUX, Duration.ofMinutes(30), acc2);
		Exam exam3 = new Exam("ExamCode3", "ExamTitle3", CategoryQuestion.DOTNET, Duration.ofMinutes(60), acc1);
		
		List<Account> accounts = Arrays.asList(acc1, acc2, acc3, acc4, acc5);
		List<Group> groups = Arrays.asList(grp1, grp2, grp3);
		List<Question> questions = Arrays.asList(q1, q2, q3);
		List<Answer> answers = Arrays.asList(ans1, ans2, ans3);
		List<Exam> exams = Arrays.asList(exam1, exam2, exam3);
		
		System.out.println(acc1);
		System.out.println(grp1);
		System.out.println(q1);
		System.out.println(ans1);
		System.out.println(exam1);
	}
}
