package xyz.peasfultown.frontend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import xyz.peasfultown.utils.ScannerUtils;
import xyz.peasfultown.backend.Exercise1Controller;
import xyz.peasfultown.entity.Circle;
import xyz.peasfultown.entity.Rectangle;
import xyz.peasfultown.entity.Shape;
import xyz.peasfultown.entity.ShapeException;

public class Exercise1 {
	private Scanner scanner;
	private Exercise1Controller ex1;
	public Exercise1(Scanner scanner, Exercise1Controller ex1) {
		this.scanner = scanner;
		this.ex1 = ex1;
	}
	
	public void run() {
		while (true) {
			System.out.println("Testing System 7 Menu");
			this.printOptions(
					"Question 1: Print Student college",
					"Question 2: Group money",
					"Question 3: MyMath",
					"Question 6: Student/PrimaryStudent/SecondaryStudent counts",
					"Question 8: ShapeException demo"
			);
			
			int usrop = ScannerUtils.inputInt(this.scanner, "Enter option: ", "Input must be a positive number, try again");
			switch (usrop) {
			case 0:
				return;
			case 1:
				this.ex1.question1();
				break;
			case 2:
				this.ex1.question2();
				break;
			case 3:
				this.question3(this.scanner);
				break;
			case 4:
				this.ex1.question6();
				break;
			case 5:
				this.question8(this.scanner);
				break;
			default: 
				System.out.println("Invalid option, try again");
				break;
			}
		}
	}
	
	private void question3(Scanner scanner) {
		int mathin1 = ScannerUtils.inputInt(scanner, "Enter first number: ");
		int mathin2 = ScannerUtils.inputInt(scanner, "Enter second number: ");
		this.ex1.question3_max(mathin1, mathin2);
		this.ex1.question3_min(mathin1, mathin2);
		this.ex1.question3_sum(mathin1, mathin2);
	}
	
	private void question8(Scanner scanner) {
		ArrayList<Shape> shapes = new ArrayList<>();
		while (true) {
			System.out.println("Create shapes");
			printOptions("Create Rectangle", "Create Circle", "List shapes");
			int usrop = ScannerUtils.inputInt(scanner, "Enter option: ");
			switch (usrop) {
			case 0:
				return;
			case 1:
				float length = ScannerUtils.inputFloat(scanner, "Enter rectangle length: ", "Invalid input, try again");
				float width = ScannerUtils.inputFloat(scanner, "Enter rectangle width: ", "Invalid input, try again");
				try {
					Rectangle rec = new Rectangle(length, width);
					this.ex1.addShape(rec);						
					rec.showInfo();
				}
				catch (ShapeException e) {
					System.err.println(e.getMessage());					
				}
				break;
			case 2:
				float radius = ScannerUtils.inputFloat(scanner, "Enter circle radius: ", "Invalid input, try again");
				try {
					Circle cir = new Circle(radius);
					shapes.add(cir);
					cir.showInfo();
				} catch (ShapeException e) {
					System.err.println(e.getMessage());
				}
				break;
			case 3:
				this.ex1.showShapesInfo();
				break;
			default: 
				System.out.println("Invalid option, try again");
				break;
			}
		}
	}
	

	private void printOptions(String... options) {
		for (int i = 0; i < options.length; i++) {
			System.out.printf("%4s(%d) %s\n", "", i+1, options[i]);
		}
		System.out.printf("%4s%s\n", "", "(0) Back");
	}
}
