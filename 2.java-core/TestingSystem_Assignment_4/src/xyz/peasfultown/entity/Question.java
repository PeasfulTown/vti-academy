package xyz.peasfultown.entity;

import java.time.LocalDateTime;

public class Question {
	private static int numberOfQuestions;
	static {
		numberOfQuestions = 0;
	}
	private int id;
	private String content;
	private CategoryQuestion category;
	private TypeQuestion type;
	private Account creator;
	private LocalDateTime createDate;
	private Answer[] answers;
	{
		this.id = numberOfQuestions++;
		this.createDate = LocalDateTime.now();
		this.answers = new Answer[0];
	}

	public Question(String content, CategoryQuestion category, TypeQuestion type, Account creator) {
		this.content = content;
		this.category = category;
		this.type = type;
		this.creator = creator;
	}
	
	public Question(String content, CategoryQuestion category, TypeQuestion type, Account creator,
			Answer[] answers) {
		this(content, category, type, creator);
		this.answers = answers;
	}

	public Question(String content, CategoryQuestion category, TypeQuestion type, Account creator,
			LocalDateTime createDate) {
		this(content, category, type, creator);
		this.createDate = createDate;
	}

	public static int getNumberOfQuestions() {
		return numberOfQuestions;
	}
	
	public int getId() {
		return this.id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CategoryQuestion getCategory() {
		return this.category;
	}

	public void setCategory(CategoryQuestion category) {
		this.category = category;
	}

	public TypeQuestion getType() {
		return this.type;
	}

	public void setType(TypeQuestion type) {
		this.type = type;
	}

	public Account getCreator() {
		return this.creator;
	}

	public void setCreator(Account creator) {
		this.creator = creator;
	}

	public LocalDateTime getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	public Answer[] getAnswers() {
		return this.answers;
	}
	
	public void setAnswers(Answer[] answers) {
		this.answers = answers;
	}
	
	public void addAnswer(Answer answer) {
		Answer[] newAnswers = new Answer[this.answers.length + 1];
		for (int i = 0; i < this.answers.length; i++) {
			newAnswers[i] = this.answers[i];
		}
		newAnswers[newAnswers.length - 1] = answer;
		this.answers = newAnswers;
	}

	public void addAnswer(Answer... answers) {
		int newAnswerSize = this.answers.length + answers.length;
		Answer[] newAnswers = new Answer[newAnswerSize];
		for (int i = 0; i < this.answers.length; i++)
			newAnswers[i] = this.answers[i];
		for (int i = this.answers.length, j = 0; i < newAnswerSize; i++, j++)
			newAnswers[i] = answers[j];
		this.answers = newAnswers;
	}
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", content=" + content + ", category=" + category + ", type=" + type
				+ ", creator=" + creator + ", createDate=" + createDate + "]";
	}
}
