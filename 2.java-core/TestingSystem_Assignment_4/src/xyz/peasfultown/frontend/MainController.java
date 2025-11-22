package xyz.peasfultown.frontend;

import java.util.Scanner;

import xyz.peasfultown.backend.PersonnelController;
import xyz.peasfultown.entity.VietnamesePhone;
import xyz.peasfultown.utils.PromptUtils;

public class MainController {
	private Scanner scanner;
	public MainController(Scanner scanner) {
		this.scanner = scanner;
	}
	public void run() {
		while (true) {
			System.out.println("Testing System 4 options:");
			System.out.printf("%4s%s\n", " ", "(1) Constructor demo");
			System.out.printf("%4s%s\n", " ", "(2) Personnel management program");
			System.out.printf("%4s%s\n", " ", "(3) Vietnamese phone number demo");
		
			System.out.printf("%4s%s\n", " ", "(0) Exit");
				
			int usrop = PromptUtils.getIntegerUserInput(scanner, "Enter option: ");
			switch(usrop) {
			case 0:
				System.out.println("Exiting program");
				return;
			case 1:
				Program1 prg1 = new Program1(this.scanner);
				prg1.run();
				break;
			case 2:
				PersonnelController pc = new PersonnelController();
				PersonnelControllerMenu pcm = new PersonnelControllerMenu(scanner, pc);
				pcm.run();
				break;
			case 3:
				VietnamesePhone vp = new VietnamesePhone();
				VietnamesePhoneMenu vpm = new VietnamesePhoneMenu(scanner, vp);
				vpm.run();
				break;
			default:
				System.out.println("Invalid option, try again.");
				break;
			}
			PromptUtils.continuePrompt(this.scanner);
		}
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MainController controller = new MainController(scanner);
		controller.run();
		scanner.close();
	}
}
