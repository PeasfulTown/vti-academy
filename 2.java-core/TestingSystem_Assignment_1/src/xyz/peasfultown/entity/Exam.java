package xyz.peasfultown.entity;

import java.time.Duration;
import java.time.LocalDateTime;

public class Exam {
	private int id;
	private String code;
	private String title;
	private CategoryQuestion category;
	private Duration duration;
	private Account creator;
	private LocalDateTime createDate;
	public Exam(int id, String code, String title, CategoryQuestion category, Duration duration, Account creator) {
		this.id = id;
		this.code = code;
		this.title = title;
		this.category = category;
		this.duration = duration;
		this.creator = creator;
		this.createDate = LocalDateTime.now();
	}
	public Exam(int id, String code, String title, CategoryQuestion category, Duration duration, Account creator,
			LocalDateTime createDate) {
		this.id = id;
		this.code = code;
		this.title = title;
		this.category = category;
		this.duration = duration;
		this.creator = creator;
		this.createDate = createDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Exam [id=" + id + ", code=" + code + ", title=" + title + ", category=" + category + ", duration="
				+ duration + ", creator=" + creator + ", createDate=" + createDate + "]";
	}
}
