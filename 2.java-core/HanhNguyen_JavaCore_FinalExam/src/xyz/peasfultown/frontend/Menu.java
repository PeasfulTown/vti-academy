package xyz.peasfultown.frontend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

import xyz.peasfultown.backend.controller.AccountController;
import xyz.peasfultown.backend.controller.ControllerException;
import xyz.peasfultown.backend.service.ServiceAccountAlreadyExistsException;
import xyz.peasfultown.backend.service.ServiceAccountNotExistsException;
import xyz.peasfultown.backend.service.ServiceInvalidCredentialsException;
import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.Lecturer;
import xyz.peasfultown.entity.Student;
import xyz.peasfultown.utils.ScannerUtils;

public class Menu {
	private AccountController ac;
	private Scanner scanner;

	public Menu() {
		this.ac = new AccountController();
		this.scanner = new Scanner(System.in);
	}

	public void run() {
		while (true) {
			System.out.println("Student management menu, log in to begin");
			ScannerUtils.printPromptOptions("Login", "Register");
			int usrop = ScannerUtils.getIntPositive(this.scanner, "Enter option: ");
			switch (usrop) {
			case 0:
				System.out.println("Exitting program");
				this.scanner.close();
				return;
			case 1:
				login();
				break;
			case 2:
				register();
				break;
			default:
				System.out.println("Invalid option, try again");
			}
		}
	}
	
	private void register() {
		String fullname = ScannerUtils.getString(this.scanner, "Enter fullname: ");
		String email = ScannerUtils.getString(this.scanner, "Enter email: ");
		String password = ScannerUtils.getPassword(this.scanner, "Enter password: ");
		LocalDate dob = ScannerUtils.getDate(this.scanner);
		String majorName = ScannerUtils.getString(this.scanner, "Enter major name: ");
		String lecturerInp = ScannerUtils.getString(this.scanner, "Are you a lecturer? (y/n): ");
		if (lecturerInp.equals("y")) {
			System.out.println("Creating lecturer account");
			try {
				ac.createLecturer(fullname, email, password, dob, majorName);				
			} catch (ControllerException ce) {
				if (ce.getCause() instanceof ServiceAccountAlreadyExistsException)
					System.err.println("Unable to create account, email already exists");
				else 
					System.err.println("Unable to create account: " + ce.getMessage());
			} finally {
				System.out.println("Account creation completed");
			}
		} else {
			System.out.println("Creating a student account by default");
			try {
				ac.createStudent(fullname, email, password, dob, majorName);				
			} catch (ControllerException ce) {
				if (ce.getCause() instanceof ServiceAccountAlreadyExistsException)
					System.err.println("Unable to create account, email already exists");
				else 
					System.err.println("Unable to create account: " + ce.getMessage());
			} finally {
				System.out.println("Account creation completed");
			}
		}
	}

	private void login() {
		System.out.println("Hint: Lecturers has all permissions\n Test email: lecturer1@gmail.com\n Test password: password\n");
		String email = ScannerUtils.getEmail(this.scanner, "Enter email: ");
		String password = ScannerUtils.getString(this.scanner, "Enter password: ");
		try {
			Account account = ac.login(email, password);
			if (account != null) {
				if (account instanceof Lecturer)
					lecturerMenu();
				else
					studentMenu();
			}
		} catch (ControllerException ce) {
			if (ce.getCause() instanceof ServiceInvalidCredentialsException) {
				System.err.println(ce.getMessage());
			} else {
				ce.printStackTrace();
			}
		}
	}

	private void studentMenu() {
		while (true) {
			System.out.println("Student Options:");
			ScannerUtils.printPromptOptions("1 - List Students", "2 - Show lecturers by major", "3 - Check password");
			int usrop = ScannerUtils.getIntPositive(this.scanner, "Enter option: ");
			switch (usrop) {
			case 0:
				return;
			case 1:
				listStudents();
				break;
			case 2: 
				showLecturersByMajor();
				break;
			default:
				System.err.println("Invalid option, try again");
				break;
			}
		}
	}

	private void lecturerMenu() {
		while (true) {
			System.out.println("Lecturer Options:");
			ScannerUtils.printPromptOptions("1 - List Students", "2 - Add student", "3 - Update student major",
					"4 - Delete student", "5 - Get students by major", "6 - Get lecturers by their major",
					"7 - Check password");
			int usrop = ScannerUtils.getIntPositive(this.scanner, "Enter option: ");
			switch (usrop) {
			case 0:
				return;
			case 1:
				listStudents();
				break;
			case 2:
				createStudent();
				break;
			case 3:
				updateStudentMajor();
				break;
			case 4:
				deleteStudent();
				break;
			case 5:
				showStudentsByMajor();
				break;
			case 6:
				showLecturersByMajor();
				break;
			case 7:
				checkPassword();
				break;
			default:
				System.err.println("Invalid option, try again");
				break;
			}
		}
	}
	
	private void listStudents() {
		try {
			List<Student> students = ac.getStudents();
			
			for (Student s : students) {
				System.out.println(s);
			}
		} catch (ControllerException ce) {
			ce.printStackTrace();
		}		
	}
	
	private void createStudent() {
		String fullname = ScannerUtils.getString(this.scanner, "Enter student fullname: ");
		String email = ScannerUtils.getEmail(this.scanner, "Enter email: ");
		String password = ScannerUtils.getPassword(this.scanner, "Enter password: ");
		LocalDate dob = ScannerUtils.getDate(this.scanner);
		String majorName = ScannerUtils.getString(this.scanner, "Enter major name: ");
		try {
			ac.createStudent(fullname, email, password, dob, majorName);
		} catch (ControllerException ce) {
			if (ce.getCause() instanceof ServiceAccountAlreadyExistsException)
				System.err.println("Unable to create student account, account by that email already exists");
			else 
				ce.printStackTrace();
		} finally {
			System.out.println("Student account created");
		}	
	}
	
	private void updateStudentMajor() {
		int studentId = ScannerUtils.getIntPositive(this.scanner, "Enter student ID to update: ");
		String newMajor = ScannerUtils.getString(this.scanner, "Enter new major name: ");
		try {
			ac.updateStudentMajor(studentId, newMajor);
		} catch (ControllerException ce) {
			if (ce.getCause() instanceof ServiceAccountNotExistsException)
				System.err.println("Unable to update: this student does not exist");
			else
				System.err.println("Unable to update student major: " + ce.getMessage());
		}
	}
	
	private void deleteStudent() {
		int studentId = ScannerUtils.getIntPositive(this.scanner, "Enter student ID to delete: ");
		String confirm = ScannerUtils.getString(this.scanner, "Are you sure? (y/n): ");
		if (confirm.equals("y")) {
			try {
				ac.deleteStudent(studentId);
			} catch (ControllerException ce) {
				if (ce.getCause() instanceof ServiceAccountNotExistsException)
					System.err.println("Unable to delete: this student does not exist");
				else
					System.err.println("Unable to delete student: " + ce.getMessage());
			}
		} else {
			System.out.println("Cancelled student deletion");
		}
	}
	
	private void showStudentsByMajor() {
		String majorName = ScannerUtils.getString(this.scanner, "Enter major name: ");
		try {
			List<Student> students = ac.getStudentsByMajorName(majorName);
			if (students.size() == 0)
				System.err.println("Looks like this major has no students yet");
			else {
				for (Student s : students) {
					System.out.println(s);
				}
			}
		} catch (ControllerException ce) {
			System.err.println(ce.getMessage());
		}
	}
	
	private void showLecturersByMajor() {
		String majorName = ScannerUtils.getString(this.scanner, "Enter major name: ");
		try {
			List<Lecturer> lecturers = ac.getLecturersByMajor(majorName);
			if (lecturers.size() == 0) {
				System.err.println("Looks like there's no lecturers for this major");
			} else {
				for (Lecturer l : lecturers) {
					System.out.println(l);
				}
			}
		} catch (ControllerException ce) {
			System.err.println("Unable to show lecturers: " + ce.getMessage());
		}
	}
	
	private void checkPassword() {
		while (true) {
			StringBuilder requirements = new StringBuilder("Password must include:\n");
			requirements.append("  Total of at least 8 characters and no more than 20 characters\n");
			requirements.append("  At least 1 number\n");
			requirements.append("  At least 1 lowercase character\n");
			requirements.append("  At least 1 uppercase character\n");
			requirements.append("  At least 1 special character (@#$%^&+!) \n");
			requirements.append("  No spaces\n");
			requirements.append("Enter password to test (or 'quit' to go back): ");
			while (true) {
				String inp = ScannerUtils.getString(scanner, requirements.toString());
				Matcher m = ScannerUtils.VALID_PASSWORD_REGEX.matcher(inp);
				if (inp.equalsIgnoreCase("quit"))
					return;
				if (m.matches()) 
					System.out.println("Password matches all requirements");
				else {
					System.err.println("Password does not fit requirement");
					continue;
				}
					
			}
		}
	}
}
