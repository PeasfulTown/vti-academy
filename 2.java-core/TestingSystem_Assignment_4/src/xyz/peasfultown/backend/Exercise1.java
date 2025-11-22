package xyz.peasfultown.backend;

import java.time.LocalDate;
import java.time.LocalDateTime;

import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.Department;
import xyz.peasfultown.entity.Group;
import xyz.peasfultown.entity.Position;

public class Exercise1 {
	public Department[] question1() {
		Department[] departments = new Department[2];
		departments[0] = new Department();
		departments[1] = new Department("Some Department");
		return departments;
	}
	
	public Account[] question2() {
		Account[] accounts = new Account[4];
		accounts[0] = new Account();
		accounts[1] = new Account(20, "example1@example.com", "exampleUser1", "Firstname1", "Lastname1");
		accounts[2] = new Account(21, "example2@example.com", "exampleUser2", "Firstname2", "Lastname2", Position.ASSISTANT_MANAGER);
		accounts[3] = new Account(22, "example3@example.com", "exampleUser3", "Firstname3", "Lastname3", Position.DEV, LocalDateTime.of(2019, 8, 24, 1, 1, 1));
		return accounts;
	}
	
	public Group[] question3() {
		Group[] groups = new Group[3];
		groups[0] = new Group();
		groups[1] = new Group("New Group1", new Account("newuser1"), LocalDateTime.now(), new Account[] { new Account("member1"), new Account("member2") } );
		groups[2] = new Group("New Group2", new Account("newuser2"), LocalDateTime.now(), new String[] {"newuser3", "newuser4"});
		return groups;
				
	}
}
