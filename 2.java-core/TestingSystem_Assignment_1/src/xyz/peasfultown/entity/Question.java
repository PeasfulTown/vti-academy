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
	{
		this.id = numberOfQuestions++;
		this.createDate = LocalDateTime.now();
	}

	public Question(String content, CategoryQuestion category, TypeQuestion type, Account creator) {
		this.content = content;
		this.category = category;
		this.type = type;
		this.creator = creator;
	}

	public Question(String content, CategoryQuestion category, TypeQuestion type, Account creator,
			LocalDateTime createDate) {
		this(content, category, type, creator);
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CategoryQuestion getCategory() {
		return category;
	}

	public void setCategory(CategoryQuestion category) {
		this.category = category;
	}

	public TypeQuestion getType() {
		return type;
	}

	public void setType(TypeQuestion type) {
		this.type = type;
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

	@Override
	public String toString() {
		return "Question [id=" + id + ", content=" + content + ", category=" + category + ", type=" + type
				+ ", creator=" + creator + ", createDate=" + createDate + "]";
	}
}
