package xyz.peasfultown.entity;

public class Skill {
	private short id;
	private String name;

	public Skill(short id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
