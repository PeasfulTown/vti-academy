package xyz.peasfultown.backend;

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

public class Data {
	private Department[] departments;
	private Account[] accounts;
	private Group[] groups;
	private Question[] questions;
	private Answer[] answers;
	private Exam[] exams;

	public Data() {
		Department dep0 = new Department("Sales", "Quebec");
		Department dep1 = new Department("Department A", "Montreal");
		Department dep2 = new Department("Human Resources", "Quebec");
		Department dep3 = new Department("Finance", "Los Angeles");
		Department dep4 = new Department("Information Technology", "New York");
		Department dep5 = new Department("Customer Service", "Toronto");
		Department dep6 = new Department("Maintenance", "Calgary");
		Department dep7 = new Department("Security", "Winnipeg");
		Department dep8 = new Department("Operations", "Washington");
		Department dep9 = new Department("Administration", "Wisconsin");
		Department dep10 = new Department("Waiting Room", "Texas");
		Department dep11 = new Department("Marketing", "Montreal");
		
		Account acc0 = new Account("acc1@gmail.com", "acc1", "acc1FullName", dep0, Position.DEV);
		Account acc1 = new Account("acc2@gmail.com", "acc2", "acc2FullName", dep10, Position.INTERN);
		Account acc2 = new Account("acc3@gmail.com", "acc3", "acc3FullName", dep6, Position.DEV);
		Account acc3 = new Account("acc4@gmail.com", "acc4", "acc4FullName", dep8, Position.PM);
		Account acc4 = new Account("acc5@gmail.com", "acc5", "acc5FullName", dep2, Position.DEV);

		Group grp0 = new Group("Grp1", acc2);
		Group grp1 = new Group("Grp2", acc3);
		Group grp2 = new Group("Grp3", acc1);
		Group grp3 = new Group("Grp4", acc1);
		Group grp4 = new Group("Grp5", acc1);
		Group grp5 = new Group("Java", acc2);
		Group grp6 = new Group("Java", acc1);

		acc1.addGroup(grp4, grp3);
		acc1.addGroup(grp2, grp1);

		acc2.addGroup(grp0, grp1);
		acc2.addGroup(grp2);

		acc3.addGroup(grp2, grp3);
		acc4.addGroup(grp3);

		grp0.addAccount(acc2);
		grp1.addAccount(acc1, acc2);
		grp2.addAccount(acc1, acc2, acc3);
		grp3.addAccount(acc1, acc3);
		grp4.addAccount(acc1);

		Question q0 = new Question("Question1", CategoryQuestion.JAVA, TypeQuestion.MULTIPLE_CHOICE, acc1);
		Question q1 = new Question("Question2", CategoryQuestion.SQL, TypeQuestion.TRUE_OR_FALSE, acc2);
		Question q2 = new Question("Question3", CategoryQuestion.RUBY, TypeQuestion.MULTIPLE_CHOICE, acc4);

		Answer ans0 = new Answer("Answer1", q0, false);
		Answer ans1 = new Answer("Answer2", q0, true);
		Answer ans2 = new Answer("Answer3", q1, true);
		Answer ans3 = new Answer("Answer4", q1, false);
		Answer ans4 = new Answer("Answer5", q2, false);
		Answer ans5 = new Answer("Answer6", q2, false);
		Answer ans6 = new Answer("Answer7", q2, true);

		q0.addAnswer(ans0, ans1);
		q1.addAnswer(ans2, ans3);
		q2.addAnswer(ans4, ans5, ans6);

		Exam exam0 = new Exam("ExamCode1", "ExamTitle1", CategoryQuestion.JAVA, Duration.ofMinutes(45), acc1);
		Exam exam1 = new Exam("ExamCode2", "ExamTitle2", CategoryQuestion.LINUX, Duration.ofMinutes(30), acc2);
		Exam exam2 = new Exam("ExamCode3", "ExamTitle3", CategoryQuestion.DOTNET, Duration.ofMinutes(60), acc1);

		exam0.addQuestion(q0, q1);
		exam1.addQuestion(q0, q1, q2);

		this.departments = new Department[] { dep0, dep1, dep2, dep3, dep4, dep5, dep6, dep7, dep8, dep9, dep10 };
		this.accounts = new Account[] { acc0, acc1, acc2, acc3, acc4 };
		this.groups = new Group[] { grp0, grp1, grp2, grp3, grp4, grp5, grp6 };
		this.questions = new Question[] { q0, q1, q2 };
		this.answers = new Answer[] { ans0, ans1, ans2 };
		this.exams = new Exam[] { exam0, exam1, exam2 };
	}

	Department[] getDepartments() {
		return this.departments;
	}
	
	Account[] getAccounts() {
		return this.accounts;
	}

	Group[] getGroups() {
		return this.groups;
	}

	Question[] getQuestions() {
		return this.questions;
	}

	Answer[] getAnswers() {
		return this.answers;
	}

	Exam[] getExams() {
		return this.exams;
	}
}
