package xyz.peasfultown.entity;

public class Employee extends User {
	private Skill skill;

	public Employee(int id, String fullname, String email, String password, Skill skill) {
		super(id, fullname, email, password);
		this.skill = skill;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	@Override
	public String toString() {
		return String.format("id: %d, fullname: %s, email: %s, pass: %s, skill_id: %d, skill: %s", super.getId(),
				super.getFullname(), super.getEmail(), super.getPassword(), this.skill.getId(), this.skill.getName());
	}
}
