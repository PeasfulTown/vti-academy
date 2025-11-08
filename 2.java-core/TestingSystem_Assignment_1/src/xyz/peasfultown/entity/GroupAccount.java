package xyz.peasfultown.entity;

public class GroupAccount {
	private int id;
	private Group group;
	private Account account;
	public GroupAccount(int id, Group group, Account account) {
		super();
		this.id = id;
		this.group = group;
		this.account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "GroupAccount [id=" + id + ", group=" + group + ", account=" + account + "]";
	}
}
