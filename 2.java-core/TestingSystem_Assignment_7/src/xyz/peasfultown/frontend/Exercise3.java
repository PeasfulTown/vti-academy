package xyz.peasfultown.frontend;

import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import xyz.peasfultown.utils.FileManager;
import xyz.peasfultown.utils.ScannerUtils;

public class Exercise3 {
	private Scanner scanner;

	public Exercise3(Scanner scanner) {
		this.scanner = scanner;
	}

	public void run() {
		while (true) {
			System.out.println("Testing System 7 - Exercise 3 Demo");
			ScannerUtils.printOptions("Question 1 - File exists", "Question 2 - Create file",
					"Question 4 - Delete file", "Question 5 - File or Directory",
					"Question 6 - Get all filenames of directory", "Question 7 - Copy file", "Question 8 - Move file",
					"Question 9 - Rename file", "Question 10 - Create new directory", "Question 11: Download file");

			int usrop = ScannerUtils.inputInt(this.scanner, "Enter option: ");
			switch (usrop) {
			case 0:
				return;
			case 1:
				this.question1(this.scanner);
				break;
			case 2:
				this.question2(this.scanner);
				break;
			case 3: 
				this.question4(this.scanner);
				break;
			case 4:
				this.question5(this.scanner);
				break;
			case 5:
				this.question6(this.scanner);
				break;
			case 6:
				this.question7(this.scanner);
				break;
			case 7:
				this.question8(this.scanner);
				break;
			case 8: 
				this.question9(this.scanner);
				break;
			case 9:
				this.question10(this.scanner);
				break;
			case 10:
				this.question11(this.scanner);
				break;
			default:
				System.out.println("Invalid option, try again");
				break;
			}
		}
	}

	private void question1(Scanner scanner) {
		System.out.println("Check if file exists");
		String path = ScannerUtils.inputString(scanner, "Enter filepath: ");
		System.out.printf("File exists: %s\n", FileManager.isFileExists(path));
	}

	private void question2(Scanner scanner) {
		System.out.println("Create new file");
		String path = ScannerUtils.inputString(scanner, "Enter filepath to create: ");
		FileManager.createNewFile(path);
	}

	private void question4(Scanner scanner) {
		System.out.println("Delete file");
		String path = ScannerUtils.inputString(scanner, "Enter filepath to delete: ");
		String confirm = ScannerUtils.inputString(scanner, "Are you sure you want to delete this file? (y/n): ");
		if (confirm.equals("y"))
			FileManager.deleteFile(path);
		else
			System.out.println("Operation aborted");
	}

	private void question5(Scanner scanner) {
		System.out.println("Check if path is a directory");
		String path = ScannerUtils.inputString(scanner, "Enter path: ");
		System.out.printf("Path is a %s\n", FileManager.isDirectory(path) ? "directory" : "file");
	}

	private void question6(Scanner scanner) {
		System.out.println("Print all file names in directory");
		String path = ScannerUtils.inputString(scanner, "Enter file path: ");
		List<String> filenames = FileManager.getAllFileName(path);
		System.out.println("Files in directory:");
		for (String s : filenames) {
			System.out.println(s);
		}
	}

	private void question7(Scanner scanner) {
		System.out.println("Copy file");
		String orig = ScannerUtils.inputString(scanner, "Enter path of file to copy: ");
		String dest = ScannerUtils.inputString(scanner, "Enter destination: ");
		FileManager.copyFile(orig, dest);
	}

	private void question8(Scanner scanner) {
		System.out.println("Move file");
		String orig = ScannerUtils.inputString(scanner, "Enter path of file to move: ");
		String dest = ScannerUtils.inputString(scanner, "Enter destination: ");
		FileManager.moveFile(orig, dest);
	}

	private void question9(Scanner scanner) {
		System.out.println("Rename file");
		String filepath = ScannerUtils.inputString(scanner, "Enter path of file to rename: ");
		String newname = ScannerUtils.inputString(scanner, "Enter new file name: ");
		FileManager.renameFile(filepath, newname);
	}

	private void question10(Scanner scanner) {
		System.out.println("Create directory");
		String path = ScannerUtils.inputString(scanner, "Enter new directory path: ");
		FileManager.createDirectory(path);
	}

	private void question11(Scanner scanner) {
		System.out.println("Download file");
		String url = ScannerUtils.inputString(scanner, "Enter url of file to download: ");
		String dest = ScannerUtils.inputString(scanner, "Enter file destination: ");
		FileManager.downloadFile(url, dest);
	}
}
