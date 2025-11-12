package xyz.peasfultown.entity;

public enum Position {
	DIRECTOR,
	VICE_PRESIDENT,
	MANAGER,
	ASSISTANT_MANAGER,
	TECH_LEAD,
	PM,
	SCRUM_MASTER,
	DEV,
	TEST,
	INTERN,
	TRAINEE;
	
	public int getId() {
		return this.getId();
	}
	
	public String getName() {
		return this.toString();
	}
}
