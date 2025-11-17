package xyz.peasfultown.entity;

public enum TypeQuestion {
	MULTIPLE_CHOICE(0, "Multiple Choice"),
	ESSAY(1, "Essay"),
	SHORT_ANSWER(2, "Short Answer"),
	LONG_ANSWER(3, "Long Answer"),
	PRACTICAL(4, "Practical"),
	PROJECT(5, "Project"),
	ORAL(6, "Oral"),
	MATCHING(7, "Matching"),
	FILL_IN_THE_BLANK(8, "Fill In The Blanks"),
	TRUE_OR_FALSE(9, "True or False"),
	CAPSTONE(10, "Capstone Project");
	
	private final int id;
	private final String name;
	
	private TypeQuestion(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
}
