package xyz.peasfultown.entity;

public class Position {
	private int id;
	private String name;
	public Position(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getPositionId() {
		return id;
	}
	public void setPositionId(int id) {
		this.id = id;
	}
	public String getPositionName() {
		return name;
	}
	public void setPositionName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Position [id=" + id + ", name=" + name + "]";
	}	
}
