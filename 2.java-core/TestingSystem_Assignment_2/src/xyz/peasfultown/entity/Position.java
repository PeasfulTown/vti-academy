package xyz.peasfultown.entity;

public enum Position {
	DIRECTOR(0, "Director"),
	VICE_PRESIDENT(1, "Vice President"),
	MANAGER(2, "Manager"),
	ASSISTANT_MANAGER(3, "Assistant Manager"),
	TECH_LEAD(4, "Tech Lead"),
	PM(5, "Project Manager"),
	SCRUM_MASTER(6, "Scrum Master"),
	DEV(7, "Developer"),
	TEST(8, "Tester"),
	INTERN(9, "Intern"),
	TRAINEE(10, "Trainee");
	
	private final int id;
	private final String name;
	
	private Position(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public static Position getPositionById(int id) {
		for (Position pos : Position.values()) {
			if (pos.getId() == id)
				return pos;
		}
		return null;
	}
}
