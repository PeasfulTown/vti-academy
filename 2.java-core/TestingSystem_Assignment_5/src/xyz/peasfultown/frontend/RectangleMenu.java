package xyz.peasfultown.frontend;

import java.util.Scanner;

import xyz.peasfultown.entity.Rectangle;
import xyz.peasfultown.entity.Square;
import xyz.peasfultown.utils.PromptUtils;

public class RectangleMenu {
	private Scanner scanner;
	
	public RectangleMenu(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void run() {
		while (true) {
			System.out.println("Rectangle menu");
			System.out.printf("%4s%s\n", "", "(1) Create rectangle");
			System.out.printf("%4s%s\n", "", "(2) Create square");
			
			System.out.printf("%4s%s\n", "", "(0) Back");
			

			int usrop  = PromptUtils.getIntegerUserInput(this.scanner, "Enter option: ");
			switch (usrop) {
			case 0:
				System.out.println("Exitting program");
				return;
			case 1:
				rectangle(this.scanner);
				break;
			case 2:
				square(this.scanner);
				break;
			default:
				System.out.println("Invalid option, try again");
				break;
			}
		}
	}
	
	private void rectangle(Scanner scanner) {
		int length = PromptUtils.getIntegerUserInput(scanner, "Enter rectangle length: ");
		int width = PromptUtils.getIntegerUserInput(scanner, "Enter rectangle width: ");
		
		Rectangle r = new Rectangle(length, width);
		System.out.println(r.calcPerimeter());
		System.out.println(r.calcArea());
	}
	
	private void square(Scanner scanner) {
		int side = PromptUtils.getIntegerUserInput(scanner, "Enter square side length: ");
		
		Square s = new Square(side);
		System.out.println(s.calcPerimeter());
		System.out.println(s.calcArea());
	}
}
