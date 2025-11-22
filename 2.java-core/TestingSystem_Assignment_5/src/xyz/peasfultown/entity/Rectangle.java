package xyz.peasfultown.entity;

public class Rectangle {
	private int length;
	private int width;
	
	public Rectangle(int length, int width) {
		this.length = length;
		this.width = width;
	}
	
	public int calcPerimeter() {
		System.out.println("Calculating rectangle perimeter");
		return 2 * (length + width);
	}
	
	public int calcArea() {
		System.out.println("Calculating rectangle area");
		return length * width;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
