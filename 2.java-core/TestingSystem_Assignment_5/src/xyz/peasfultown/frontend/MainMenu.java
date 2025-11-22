package xyz.peasfultown.frontend;

import java.util.Scanner;

import xyz.peasfultown.backend.NewsController;
import xyz.peasfultown.backend.StudentController;
import xyz.peasfultown.utils.PromptUtils;

public class MainMenu {
	private Scanner scanner;
	private MyNews mn;
	private StudentMenu sm;
	private RectangleMenu rm;
	
	public MainMenu(Scanner scanner, MyNews mn, StudentMenu sm, RectangleMenu rm) {
		this.scanner = scanner;
		this.mn = mn;
		this.sm = sm;
		this.rm = rm;
	}
	
	public void run() {
		while (true) {
			System.out.println("Testing System 5 Menu");
			System.out.printf("%4s%s\n", "", "(1) News menu");
			System.out.printf("%4s%s\n", "", "(2) Students menu");
			System.out.printf("%4s%s\n", "", "(3) Rectangle menu");
			
			System.out.printf("%4s%s\n", "", "(0) Exit");
			
			int usrop = PromptUtils.getIntegerUserInput(this.scanner, "Enter option: ");
			switch (usrop) {
			case 0: 
				System.out.println("Exiting program");
				return;
			case 1:
				NewsController nc = new NewsController();
				this.mn = new MyNews(this.scanner, nc);
				mn.run();
				break;
			case 2:
				StudentController sc = new StudentController();
				this.sm = new StudentMenu(this.scanner, sc);
				this.sm.insert10Students();
				this.sm.run();
				break;
			case 3:
				this.rm.run();
				break;
			default:
				System.out.println("Invalid option, try again");
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		NewsController nc = new NewsController();
		MyNews mn = new MyNews(scanner, nc);
		
		StudentController sc = new StudentController();
		StudentMenu sm = new StudentMenu(scanner, sc);
		
		RectangleMenu rm = new RectangleMenu(scanner);
		
		MainMenu mm = new MainMenu(scanner, mn, sm, rm);
		mm.run();
		scanner.close();
	}
	
	
}
