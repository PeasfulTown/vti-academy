package xyz.peasfultown.entity;

public class Rectangle extends Shape {
	private float length;
	private float width;
	
	public Rectangle(int length, int width) throws ShapeException {
		super();
		this.length = (float) length;
		this.width = (float) width;
	}
	
	public Rectangle(float length, float width) throws ShapeException {
		super();
		this.length = length;
		this.width = width;
	}
	
	public float perimeter() {
		return 2*length + 2*width;
	}
	
	public float area() {
		return length * width;
	}
	
	public void showInfo() {
		System.out.printf("Rectangle length: %.2f, width: %.2f\n", this.length, this.width);
		super.showInfo();
	}
}
