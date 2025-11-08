package xyz.peasfultown.entity;

import java.time.LocalDateTime;

public class Group {
	private int id;
	private String name;
	private Account creator;
	private LocalDateTime createDate;
	public Group(int id, String name, Account creator) {
		this.id = id;
		this.name = name;
		this.creator = creator;
		this.createDate = LocalDateTime.now();
	}
	public Group(int id, String name, Account creator, LocalDateTime createDate) {
		this.id = id;
		this.name = name;
		this.creator = creator;
		this.createDate = createDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return "Group [id=" + id + ", name=" + name + ", creator=" + creator + ", createDate=" + createDate + "]";
	}
}
