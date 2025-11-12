package xyz.peasfultown.entity;

public enum CategoryQuestion {
	JAVA(0, "Java"),
	DOTNET(1, ".Net"),
	SQL(2, "SQL"),
	POSTMAN(3, "Postman"),
	RUBY(4, "Ruby"),
	LINUX(5, "Linux"),
	CLOUD(6, "Cloud"),
	AI(7, "AI"),
	GAME(8, "Game"),
	JAVASCRIPT(9, "JavaScript");
	
	private final int id;
	private final String name;
	
	CategoryQuestion(int id, String name) {
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