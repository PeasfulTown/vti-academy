package xyz.peasfultown.entity;

public class Student {
	private static int count;
	static {
		count = 0;
	}
	private int id;
	private String name;
	private String hometown;
	private int points;
	
	public Student(String name, String hometown) {
		this.id = count++;
		this.name = name;
		this.hometown = hometown;
		this.points = 0;
	}

	public static int getCount() {
		return count;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		if (points > 10 || points < 0) {
			System.out.println("Points may not be lower than 0 or higher than 10");
			return;
		}
		this.points = points;
	}
	
	public void incrementPoints(int pt) {
		this.points+=pt;
	}
	
	public void decrementPoints(int pt) {
		this.points-=pt;
	}
	
	public void showInfo() {
		String ptdesc;
		if (this.points < 4)
			ptdesc = "Weak";
		else if(this.points >= 4 && this.points < 6)
			ptdesc = "Average";
		else if(this.points >= 6 && this.points < 8) 
			ptdesc = "Good";
		else 
			ptdesc = "Very Good";
		
		System.out.printf("Name: %s\n", this.name);
		System.out.printf("Points: %d (%s)\n", this.points, ptdesc);
	}
	
	@Override
	public String toString() {
		return String.format("ID: %d, name: %s, hometown: %s, points: %d", this.id, this.name, this.hometown, this.points);
	}
}
