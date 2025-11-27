package xyz.peasfultown.entity;

public class Circle extends Shape {
	private final static float PI = 3.1415926535898f;
	private float radius;

	public Circle(float radius) throws ShapeException {
		super();
		this.radius = radius;
	}

	public float perimeter() {
		return 2 * Circle.PI * radius;
	}

	public float area() {
		return Circle.PI * (radius * radius);
	}
	
	public void showInfo() {
		System.out.printf("Circle radius: %.2f\n", this.radius);
		super.showInfo();
	}
}
