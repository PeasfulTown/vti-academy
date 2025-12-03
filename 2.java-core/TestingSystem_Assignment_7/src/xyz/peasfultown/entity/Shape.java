package xyz.peasfultown.entity;

public abstract class Shape {
	private static int count = 0;
	
	public Shape() {
		if (count < Configs.MAX_NUMBER_OF_SHAPES)
			count++;
		else
			throw new ShapeException("Number of shapes must not be higher than " + Configs.MAX_NUMBER_OF_SHAPES);
	}
	
	public static int getCount() {
		return Shape.count;
	}
	
	public abstract float perimeter();
	public abstract float area();
	public void showInfo() {
		System.out.printf("Perimeter: %.2f\n", this.perimeter());
		System.out.printf("Area: %.2f\n", this.area());
	}
}
