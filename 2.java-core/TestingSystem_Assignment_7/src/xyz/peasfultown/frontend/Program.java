package xyz.peasfultown.frontend;

import java.net.URI;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import xyz.peasfultown.backend.Exercise1Controller;
import xyz.peasfultown.entity.Student;
import xyz.peasfultown.utils.IOManager;
import xyz.peasfultown.utils.ScannerUtils;

public class Program {
	private Scanner scanner;
	public Program(Scanner scanner) {
		this.scanner = scanner;
	}
	public void run() {
		while (true) {
			System.out.println("Testing System 7 Menu");
			ScannerUtils.printOptions("Exercise 1 Demo", "Exercise 3 Demo", "Exercise 5 Demo");
			int usrop = ScannerUtils.inputInt(this.scanner, "Enter option: ");
			switch (usrop) {
			case 0:
				return;
			case 1:
				Exercise1Controller ex1c = new Exercise1Controller();
				Exercise1 ex1 = new Exercise1(this.scanner, ex1c);
				ex1.run();
				break;
			case 2:
				Exercise3 ex3 = new Exercise3(this.scanner);
				ex3.run();
				break;
			case 3:
				Exercise5 ex5 = new Exercise5(this.scanner);
				ex5.run();
				break;
			default: 
				System.err.println("Invalid input, try again");
				break;
			}
		}
	}
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		Program program = new Program(scanner);
		program.run();
		scanner.close();
	}
}
