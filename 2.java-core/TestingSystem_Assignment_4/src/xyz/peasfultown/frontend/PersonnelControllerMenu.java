package xyz.peasfultown.frontend;

import java.util.Scanner;

import xyz.peasfultown.backend.PersonnelController;
import xyz.peasfultown.entity.Employee;
import xyz.peasfultown.entity.Engineer;
import xyz.peasfultown.entity.Personnel;
import xyz.peasfultown.entity.Personnel.Gender;
import xyz.peasfultown.entity.Worker;
import xyz.peasfultown.utils.PromptUtils;

public class PersonnelControllerMenu {
	private Scanner scanner;
	private PersonnelController pc;
	public PersonnelControllerMenu(Scanner scanner, PersonnelController pc) {
		this.scanner = scanner;
		this.pc = pc;
	}
	
	public void run() {
		while (true) {
			System.out.println("Personnel management options");
			System.out.printf("%4s%s\n", "", "(1) Add personnel");
			System.out.printf("%4s%s\n", "", "(2) Search personnel");
			System.out.printf("%4s%s\n", "", "(3) List all personnel data");
			System.out.printf("%4s%s\n", "", "(4) Delete personnel data");
			System.out.printf("%4s%s\n", "", "(0) Exit");
			
			int usrop = PromptUtils.getIntegerUserInput(this.scanner, "Enter option: ");
			switch (usrop) {
			case 0:
				System.out.println("Exiting program");
				return;
			case 1:
				addPersonnel(scanner);
				break;
			case 2:
				showPersonnelData(scanner);
				break;
			case 3:
				showAllPersonnelInfo();
				break;
			case 4:
				deletePersonnelByName(scanner);
				break;
			default:
				System.out.println("Invalid input, try again");
				break;
			}
			PromptUtils.continuePrompt(scanner);
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		PersonnelController pc = new PersonnelController();
		PersonnelControllerMenu menu = new PersonnelControllerMenu(scanner, pc);
		menu.run();
		
	}
	
	private void addPersonnel(Scanner scanner) {
		String fullname = PromptUtils.getStringUserInput(scanner, "Enter personnel name: ");
		int age = PromptUtils.getIntegerUserInput(scanner, "Enter personnel age: ");
		Gender gender = getGenderInput(scanner);
		String address = PromptUtils.getStringUserInput(scanner, "Enter personnel address: ");
		int typeop = getPersonnelTypeInput(scanner);
		switch (typeop) {
		case 1:
			int workerLevel = getWorkerLevel(scanner);
			this.pc.addPersonnel(new Worker(fullname, age, gender, address, workerLevel));
			break;
		case 2:
			String engineerSpecialization = PromptUtils.getStringUserInput(scanner, "Enter engineer specialization: ");
			this.pc.addPersonnel(new Engineer(fullname, age, gender, address, engineerSpecialization));
			break;
		case 3:
			String employeeResponsibility = PromptUtils.getStringUserInput(scanner, "This employee's responsibility: ");
			this.pc.addPersonnel(new Employee(fullname, age, gender, address, employeeResponsibility));
			break;
		}
		System.out.println("Personnel data added.");
	}
	
	private void showPersonnelData(Scanner scanner) {
		while (true) {
			String inp = PromptUtils.getStringUserInput(scanner, "Name of personnel to show (type 'quit' to go back): ");
			if (inp.equalsIgnoreCase("quit"))
				return;
			Personnel p = pc.getPersonnelByName(inp);
			if (p == null)
				System.out.println("No personnel found by that name");
			else
				p.showInfo();
		}
	}
	
	private void showAllPersonnelInfo() {
		pc.showAllPersonnelInfo();
	}
	
	private void deletePersonnelByName(Scanner scanner) {
		String inp = PromptUtils.getStringUserInput(scanner, "Enter personnel name to delete: ");
		Personnel p = pc.getPersonnelByName(inp);
		if (p == null) {
			System.out.println("No personnel found by that name");
			return;
		}
		p.showInfo();
		inp = PromptUtils.getStringUserInput(scanner, "Are you sure you want to delete this personnel? (y/n): ");
		if (inp.equals("y")) {
			pc.deletePersonnel(p);
			System.out.println("Personnel info deleted");
		} else {
			System.out.println("Personnel info not deleted");
		}
	}
	
	private Gender getGenderInput(Scanner scanner) {
		while (true) {
			String genderstr = PromptUtils.getStringUserInput(scanner, "Enter personnel gender (male, female, other): ");				
			for (Gender g : Gender.values()) {
				if (g.name().equalsIgnoreCase(genderstr))
					return g;
			}
			System.out.println("Invalid gender input, try again");
		}
	}
	
	private int getPersonnelTypeInput(Scanner scanner) {
		while (true) {
			System.out.println("Type of personnel");
			System.out.printf("%4s%s\n", "", "(1) Worker");
			System.out.printf("%4s%s\n", "", "(2) Engineer");
			System.out.printf("%4s%s\n", "", "(3) Employee");
			
			int typeop = PromptUtils.getIntegerUserInput(scanner, "Enter choice: ");
			if (typeop < 1 || typeop > 3) {
				System.out.println("Invalid option, try again");
				continue;
			}
			return typeop;	
		}
	}
	
	private int getWorkerLevel(Scanner scanner) {
		while (true) {
			int inp = PromptUtils.getIntegerUserInput(scanner, "Enter worker level (1-10): ");
			if (inp < 1 || inp > 10) {
				System.out.println("Worker level must not be lower than 1 or higher than 10");
				continue;
			}
			return inp;
		}
	}
}
