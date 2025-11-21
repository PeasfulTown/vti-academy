package xyz.peasfultown.program;

import xyz.peasfultown.entity.Account;

public class Exercise2 {
	private Account[] accounts;

	public Exercise2() {
		this.accounts = new Account[5];
		for (int i = 0; i < this.accounts.length; i++) {
			this.accounts[i] = new Account("Email " + i, "Username " + i, "Fullname " + i, null, null);
		}
	}

	public void printAccounts() {
		for (Account acc : accounts) {
			System.out.printf("Email: %s, Username: %s, Fullname: %s, Create Date: %s\n", acc.getEmail(),
					acc.getUsername(), acc.getFullname(), acc.getCreateDate());
		}
	}
}
