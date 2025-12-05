package xyz.peasfultown.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Group {
	private int id;
	private String name; 
	private Account creator;
	private LocalDateTime createDate;
	private List<Account> accounts; 
	
	public Group(int id, String name, Account creator, LocalDateTime createDate) {
		this(id, name, creator, createDate, new ArrayList<>());
	}
	
	public Group(int id, String name, Account creator, LocalDateTime createDate, List<Account> accounts) {
		this.id = id;
		this.name = name;
		this.creator = creator;
		this.createDate = createDate;
		this.accounts = accounts;
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

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", creator=" + creator + ", createDate=" + createDate + "]";
	}
}
