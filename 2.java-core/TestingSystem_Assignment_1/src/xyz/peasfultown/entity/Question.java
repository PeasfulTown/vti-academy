package xyz.peasfultown.entity;

import java.time.LocalDateTime;

public class Question {
	private int id;
	private String content;
	private CategoryQuestion category;
	private TypeQuestion type;
	private Account creator;
	private LocalDateTime createDate;
	public Question(int id, String content, CategoryQuestion category, TypeQuestion type, Account creator) {
		this.id = id;
		this.content = content;
		this.category = category;
		this.type = type;
		this.creator = creator;
		this.createDate = LocalDateTime.now();
	}
	public Question(int id, String content, CategoryQuestion category, TypeQuestion type, Account creator,
			LocalDateTime createDate) {
		super();
		this.id = id;
		this.content = content;
		this.category = category;
		this.type = type;
		this.creator = creator;
		this.createDate = createDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
