package xyz.peasfultown.entity;

public enum TypeQuestion {
	MULTIPLE_CHOICE,
	ESSAY,
	SHORT_ANSWER,
	LONG_ANSWER,
	PRACTICAL,
	PROJECT,
	ORAL,
	MATCHING,
	FILL_IN_THE_BLANK,
	TRUE_OR_FALSE,
	CAPSTONE;
	
	public int getId() {
		return this.getId();
	}
	
	public String getName() {
		return this.toString();
	}
}
