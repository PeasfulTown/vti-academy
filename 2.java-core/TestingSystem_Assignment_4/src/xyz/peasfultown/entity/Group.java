package xyz.peasfultown.entity;

import java.time.LocalDateTime;
import java.util.stream.Collector;
import java.util.Arrays;
import java.util.List;
public class Group {
	private static int numberOfGroups;
	static {
		numberOfGroups = 0;
	}
	private int id;
	private String name;
	private Account creator;
	private LocalDateTime createDate;
	private Account[] accounts;
	{
		this.id = numberOfGroups++;
		this.createDate = LocalDateTime.now();
		this.accounts = new Account[0];
	}

	public Group(String name, Account creator) {
		this.name = name;
		this.creator = creator;
	}

	public Group(String name, Account creator, Account[] accounts) {
		this(name, creator);
		this.accounts = accounts;
	}

	public Group(String name, Account creator, LocalDateTime createDate) {
		this(name, creator);
		this.createDate = createDate;
	}

	public Group(String name, Account creator, LocalDateTime createDate, Account[] accounts) {
		this(name, creator, createDate);
		this.accounts = accounts;
	}
	
	public static int getNumberOfGroups() {
		return numberOfGroups;
	}
	
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Account[] getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Account[] accounts) {
		this.accounts = accounts;
	}

	public void addAccount(Account newAccount) {
		Account[] newAccounts = new Account[this.accounts.length + 1];
		for (int i = 0; i < this.accounts.length; i++)
			newAccounts[i] = this.accounts[i];
		newAccounts[newAccounts.length - 1] = newAccount;
		this.accounts = newAccounts;
	}
	
	public void addAccount(Account... accounts) {
		int newAccountsLength = this.accounts.length + accounts.length;
		Account[] newAccounts = new Account[newAccountsLength];
		for (int i = 0; i < this.accounts.length; i++)
			newAccounts[i] = this.accounts[i];
		for (int i = this.accounts.length, j = 0; i < newAccountsLength; i++, j++)
			newAccounts[i] = accounts[j];
		this.accounts = newAccounts;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", creator=" + creator + ", createDate=" + createDate + "]";
	}
}
