package xyz.peasfultown.frontend;

import java.util.Scanner;

import xyz.peasfultown.entity.Contact;
import xyz.peasfultown.entity.VietnamesePhone;
import xyz.peasfultown.utils.PromptUtils;

public class VietnamesePhoneMenu {
	private Scanner scanner;
	private VietnamesePhone vp;

	public VietnamesePhoneMenu(Scanner scanner) {
		this.scanner = scanner;
		this.vp = new VietnamesePhone();
	}

	public VietnamesePhoneMenu(Scanner scanner, VietnamesePhone vp) {
		this.scanner = scanner;
		this.vp = vp;
	}

	public void run() {
		while (true) {
			System.out.println("Vietnamese phone menu");
			System.out.printf("%4s%s\n", "", "(1) Add contact number");
			System.out.printf("%4s%s\n", "", "(2) Remove contact");
			System.out.printf("%4s%s\n", "", "(3) Update contact");
			System.out.printf("%4s%s\n", "", "(4) Search contact");

			System.out.printf("%4s%s\n", "", "(0) Exit");

			int usropt = PromptUtils.getIntegerUserInput(scanner, "Enter option: ");
			switch (usropt) {
			case 0:
				System.out.println("Exiting program");
				return;
			case 1:
				addContact();
				break;
			case 2:
				removeContact();
				break;
			case 3:
				updateContact();
				break;
			case 4:
				searchContact();
				break;
			default:
				System.out.println("Invalid option, try again");
				break;
			}
			PromptUtils.continuePrompt(scanner);
		}
	}

	private void addContact() {
		String name = PromptUtils.getStringUserInput(scanner, "Enter new contact name: ");
		String phone = getPhoneNumberInput(scanner);
		this.vp.insertContact(name, phone);
		System.out.printf("Contact \"%s\" added\n", name);
	}

	private void removeContact() {
		String name = PromptUtils.getStringUserInput(scanner, "Enter contact name to remove: ");
		Contact c = vp.getContactByName(name);
		if (c == null) {
			System.out.println("No contact found by that name");
			return;
		}
		c.showInfo();
		String confirm = PromptUtils.getStringUserInput(scanner, "Are you sure you want to delete this contact? (y/n): ");
		if (confirm.equals("y")) {
			vp.removeContact(name);
			System.out.println("Contact deleted");
		} else 
			System.out.println("Cancelled contact deletion");
	}
	
	private void updateContact() {
		String name = PromptUtils.getStringUserInput(scanner, "Enter name of contact to update: ");
		Contact c = this.vp.getContactByName(name);
		if (c == null) {
			System.out.println("No contact found by that name");
			return;
		}
		c.showInfo();
		
		String newPhone = getPhoneNumberInput(scanner);
		this.vp.updateContact(name, newPhone);
		System.out.println("Contact updated");
	}
	
	private void searchContact() {
		while (true) {
			String name = PromptUtils.getStringUserInput(scanner, "Enter contact name to display (type 'quit' to go back): ");
			if (name.equalsIgnoreCase("quit"))
				return;
			this.vp.searchContact(name);
		}
		
	}

	private String getPhoneNumberInput(Scanner scanner) {
		while (true) {
			String inp = PromptUtils.getStringUserInput(scanner, "Enter new contact number: ");
			try {
				Integer.valueOf(inp);
			} catch (NumberFormatException e) {
				System.out.println("Phone number must not contain any non-numerical characters, try again");
				continue;
			}
			return inp;
		}
	}
}
