package xyz.peasfultown.entity;

public class TypeQuestion {
	private int id;
	private String name;
	public TypeQuestion(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "TypeQuestion [id=" + id + ", name=" + name + "]";
	}
}
