package xyz.peasfultown.entity;

public enum Gender {
	MALE('M'), FEMALE('F'), UNKNOWN('U');
	private char gender;
	
	private Gender(char g) {
		this.gender = g;
	}
	
	public char getGender() {
		return this.gender;
	}
	
	public static Gender getGender(char c) {
		switch (c) {
		case 'M':
			return Gender.MALE;
		case 'F':
			return Gender.FEMALE;
		default:
			return Gender.UNKNOWN;
		}
	}
}
