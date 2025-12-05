package xyz.peasfultown.entity;

public enum Gender {
	MALE, FEMALE, UNKNOWN;
	
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
