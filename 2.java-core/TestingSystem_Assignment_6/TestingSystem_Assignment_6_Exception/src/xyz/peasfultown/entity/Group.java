package xyz.peasfultown.entity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import xyz.peasfultown.utils.ScannerUtils;

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
	
	public Group(Scanner scanner) {
		this.id = numberOfGroups++;
		this.name = ScannerUtils.inputString(scanner, "Enter group name: ");
		this.creator = new Account(ScannerUtils.inputString(scanner, "Enter creator username: "));
		this.createDate = LocalDateTime.now();
		this.accounts = new Account[0];
		String inp = ScannerUtils.inputString(scanner, "Do you want to add accounts to this group? (y/n): ");
		if (inp.equals("y")) {
			while (true) {
				String usrname = ScannerUtils.inputString(scanner, "Enter account username (enter 'quit' to stop): ");
				if (usrname.equals("quit"))
					break;
				this.addAccount(new Account(usrname));
				System.out.println("Added user to group");
			}
		}
	}
	
	public Group(String name) {
		this(name, null, LocalDateTime.now(), new Account[0]);
	}
	
	public Group(String name, Account creator, LocalDateTime createDate, Account[] accounts) {
		this.name = name; 
		this.creator = creator;
		this.createDate = createDate;
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
	
	public void showInfo() {
		System.out.printf("ID: %d\n", this.id);
		System.out.printf("Name: %s\n", this.name);
		System.out.printf("Creator: %s\n", this.creator != null ? this.creator.getUsername() : "none");
		System.out.printf("Creation date: %tF\n", this.createDate);
		String[] usernames = Arrays.stream(this.accounts).map( a -> a.getUsername() ).toArray(String[]::new);
		System.out.printf("Accounts in group: %s\n", usernames.length != 0 ? String.join(", ", usernames) : "none");
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", creator=" + creator + ", createDate=" + createDate + "]";
	}
}
