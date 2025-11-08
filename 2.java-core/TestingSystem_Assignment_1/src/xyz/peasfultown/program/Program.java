package xyz.peasfultown.program;

import java.time.Duration;

import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.Answer;
import xyz.peasfultown.entity.CategoryQuestion;
import xyz.peasfultown.entity.Department;
import xyz.peasfultown.entity.Exam;
import xyz.peasfultown.entity.ExamQuestion;
import xyz.peasfultown.entity.Group;
import xyz.peasfultown.entity.GroupAccount;
import xyz.peasfultown.entity.Position;
import xyz.peasfultown.entity.Question;
import xyz.peasfultown.entity.TypeQuestion;

public class Program {
	public static void main(String[] args) {
		Department dep1 = new Department(1, "Dep1");
		Department dep2 = new Department(2, "Dep2");
		Department dep3 = new Department(3, "Dep3");
		
		Position	 pos1 = new Position(1, "Pos1");
		Position	 pos2 = new Position(2, "Pos2");
		Position	 pos3 = new Position(3, "Pos3");
		
		Account acc1 = new Account(1, "acc1@gmail.com", "acc1", "acc1FullName", dep2, pos1);
		Account acc2 = new Account(2, "acc2@gmail.com", "acc2", "acc2FullName", dep3, pos3);
		Account acc3 = new Account(3, "acc3@gmail.com", "acc3", "acc3FullName", dep1, pos2);
		Account acc4 = new Account(4, "acc4@gmail.com", "acc4", "acc4FullName", dep3, pos1);
		Account acc5 = new Account(5, "acc5@gmail.com", "acc5", "acc5FullName", dep3, pos3);
		
		Group grp1 = new Group(1, "Grp1", acc2);
		Group grp2 = new Group(2, "Grp2", acc3);
		Group grp3 = new Group(3, "Grp3", acc1);

		GroupAccount ga1 = new GroupAccount(1, grp1, acc1);
		GroupAccount ga2 = new GroupAccount(2, grp3, acc1);
		GroupAccount ga3 = new GroupAccount(3, grp2, acc3);
		GroupAccount ga4 = new GroupAccount(4, grp2, acc2);
		GroupAccount ga5 = new GroupAccount(5, grp2, acc5);
		
		TypeQuestion tq1 = new TypeQuestion(1, "TypeQ1");
		TypeQuestion tq2 = new TypeQuestion(2, "TypeQ2");
		TypeQuestion tq3 = new TypeQuestion(3, "TypeQ3");
		
		CategoryQuestion cq1 = new CategoryQuestion(1, "CQ1");
		CategoryQuestion cq2 = new CategoryQuestion(2, "CQ2");
		CategoryQuestion cq3 = new CategoryQuestion(3, "CQ3");
		
		Question q1 = new Question(1, "Question1", cq1, tq1, acc1);
		Question q2 = new Question(2, "Question1", cq1, tq2, acc3);
		Question q3 = new Question(3, "Question1", cq2, tq3, acc4);
		
		Answer ans1 = new Answer(1, "Answer1", q3, false);
		Answer ans2 = new Answer(2, "Answer1", q1, false);
		Answer ans3 = new Answer(3, "Answer1", q2, true);
		
		Exam exam1 = new Exam(1, "ExamCode1", "ExamTitle1", cq2, Duration.ofMinutes(45), acc1);
		Exam exam2 = new Exam(2, "ExamCode2", "ExamTitle2", cq1, Duration.ofMinutes(30), acc2);
		Exam exam3 = new Exam(3, "ExamCode3", "ExamTitle3", cq3, Duration.ofMinutes(60), acc1);
		
		ExamQuestion eq1 = new ExamQuestion(1, exam1, q1);
		ExamQuestion eq2 = new ExamQuestion(2, exam1, q2);
		ExamQuestion eq3 = new ExamQuestion(3, exam1, q3);
		ExamQuestion eq4 = new ExamQuestion(4, exam3, q1);
		ExamQuestion eq5 = new ExamQuestion(5, exam2, q1);
		ExamQuestion eq6 = new ExamQuestion(6, exam2, q2);
		
		System.out.println(dep1);
		
		System.out.println(pos1);
		
		System.out.println(acc1);
		
		System.out.println(grp1);
		
		System.out.println(ga1);
		
		System.out.println(tq1);
		
		System.out.println(cq1);
		
		System.out.println(q1);
		
		System.out.println(ans1);
		
		System.out.println(exam1);
		
		System.out.println(eq1);
	}
}
