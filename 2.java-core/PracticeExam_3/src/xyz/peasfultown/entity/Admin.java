package xyz.peasfultown.entity;

public class Admin extends User {
	private short yearsOfExp;

	public Admin(int id, String fullname, String email, String password, short yearsOfExp) {
		super(id, fullname, email, password);
		this.yearsOfExp = yearsOfExp;
	}

	public short getYearExperience() {
		return yearsOfExp;
	}

	public void setYearExperience(short yearExperience) {
		this.yearsOfExp = yearExperience;
	}

	@Override
	public String toString() {
		return String.format("id: %d, fullname: %s, email: %s, pass: %s, yearsOfExperience: %d", super.getId(),
				super.getFullname(), super.getEmail(), super.getPassword(), getYearExperience());
	}

}
