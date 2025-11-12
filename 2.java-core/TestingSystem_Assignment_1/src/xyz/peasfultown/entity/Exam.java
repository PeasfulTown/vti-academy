package xyz.peasfultown.entity;

import java.time.Duration;
import java.time.LocalDateTime;

public class Exam {
	private static int numberOfExams;
	static {
		numberOfExams = 0;
	}
	private int id;
	private String code;
	private String title;
	private CategoryQuestion category;
	private Duration duration;
	private Account creator;
	private LocalDateTime createDate;
	private Question[] questions;
	{
		this.id = numberOfExams++;
		this.createDate = LocalDateTime.now();
	}

	public Exam(String code, String title, CategoryQuestion category, Duration duration, Account creator) {
		this.code = code;
		this.title = title;
		this.category = category;
		this.duration = duration;
		this.creator = creator;
	}

	public Exam(String code, String title, CategoryQuestion category, Duration duration, Account creator,
			LocalDateTime createDate) {
		this(code, title, category, duration, creator);
		this.createDate = createDate;
	}

	public static int getNumberOfExams() {
		return numberOfExams;
	}

	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CategoryQuestion getCategory() {
		return category;
	}

	public void setCategory(CategoryQuestion category) {
		this.category = category;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public Account getCreator() {
		return creator;
	}

	public void setCreator(Account creator) {
		this.creator = creator;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	public Question[] getQuestions() {
		return this.questions;
	}
	
	public void setQuestions(Question[] questions) {
		this.questions = questions;
	}
	
	public void addQuestion(Question newQuestion) {
		Question[] newQuestions = new Question[this.questions.length];
		for (int i = 0; i < this.questions.length; i++) {
			newQuestions[i] = this.questions[i];
		}
		newQuestions[newQuestions.length - 1] = newQuestion;
		this.questions = newQuestions;
	}

	@Override
	public String toString() {
		return "Exam [id=" + id + ", code=" + code + ", title=" + title + ", category=" + category + ", duration="
				+ duration + ", creator=" + creator + ", createDate=" + createDate + "]";
	}
}
