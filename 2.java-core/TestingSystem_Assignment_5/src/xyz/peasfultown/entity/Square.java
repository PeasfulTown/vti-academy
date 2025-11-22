package xyz.peasfultown.entity;

public class Square extends Rectangle {
	public Square(int side) {
		super(side, side);
	}
	
	@Override 
	public int calcPerimeter() {
		System.out.println("Calculating square perimeter");
		return 4 * super.getLength();
	}
	
	@Override
	public int calcArea() {
		System.out.println("Calculating square area");
		return super.getLength() * super.getLength();
	}
}
