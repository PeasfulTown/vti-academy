package xyz.peasfultown.frontend;

import java.nio.file.Paths;
import java.util.Scanner;

import xyz.peasfultown.utils.FileManager;
import xyz.peasfultown.utils.ScannerUtils;

public class Exercise3 {
	private Scanner scanner;
	private FileManager fm;
	public Exercise3(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public void run() {
		while (true) {
			System.out.println("Testing System 7 - Exercise 3 Demo");
			ScannerUtils.printOptions("Question 1 - File exists", "Question 2 - Create file");
			
			int usrop = ScannerUtils.inputInt(this.scanner, "Enter option: ");
			switch (usrop) {
			case 0:
				return;
			case 1:
				this.question1();
				break;
			case 2:
				this.question2(this.scanner);
				break;
			default:
				System.out.println("Invalid option, try again");
				break;
			}
		}
	}
	
	private void question1() {
		String path = Paths.get("./src/resources/test.txt").toAbsolutePath().toString();
		System.out.printf("Path: %s\n", path);
		System.out.printf("File in resources path exists: %s\n", FileManager.isFileExists(path));
	}
	
	private void question2(Scanner scanner) {
		String path = Paths.get("./src/resources/").toAbsolutePath().toString();
		String filename = ScannerUtils.inputString(scanner, "Enter filename to create: ");
		if (FileManager.isFileExists(Paths.get(path).resolve(filename).toString())) {
			System.err.println("File already exists, aborting");
			return;
		}
		FileManager.createNewFile(path, filename);
		System.out.printf("File created at %s\n", Paths.get(path).resolve(filename).toAbsolutePath());
	}
	
	
}
