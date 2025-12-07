package xyz.peasfultown.frontend;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import xyz.peasfultown.backend.DepartmentController;
import xyz.peasfultown.dao.AccountDAO;
import xyz.peasfultown.dao.DepartmentDAO;
import xyz.peasfultown.dao.GroupDAO;
import xyz.peasfultown.dao.impl.MySQLAccountDAO;
import xyz.peasfultown.dao.impl.MySQLDepartmentDAO;
import xyz.peasfultown.dao.impl.MySQLGroupDAO;
import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.DatabaseException;
import xyz.peasfultown.entity.Department;
import xyz.peasfultown.entity.Gender;
import xyz.peasfultown.entity.Group;
import xyz.peasfultown.entity.Position;
import xyz.peasfultown.utils.JDBCUtils;
import xyz.peasfultown.utils.ScannerUtils;

public class Program {
	private Scanner scanner;
	private DepartmentController depCon;

	public Program(Scanner scanner) {
		this.scanner = scanner;
		depCon = new DepartmentController(new MySQLDepartmentDAO());
	}

	public void run() throws DatabaseException {
		while (true) {
			System.out.println("Department Controller");
			ScannerUtils.printOptions("Show list departments", "Show department by id",
					"Create department", "Update department", "Delete department");

			int usrop = ScannerUtils.inputInt(this.scanner, "Enter option: ");
			switch (usrop) {
			case 0:
				return;
			case 1:
				this.depCon.show();
				break;
			case 2:
				this.depCon.show(ScannerUtils.inputInt(this.scanner, "Enter department ID: "));
				break;
			case 3:
				this.depCon.create(ScannerUtils.inputString(this.scanner, "Enter new department name: "));
				break;
			case 4:
				this.depCon.update(ScannerUtils.inputInt(this.scanner, "Enter department id: "), ScannerUtils.inputString(this.scanner, "Enter new department name: "));
				break;
			case 5:
				this.depCon.delete(ScannerUtils.inputInt(this.scanner, "Enter department id: "));
			default:
				System.out.println("Invalid option, try again");
				break;
			}
		}
	}

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			Program prog = new Program(scanner);
			prog.run();
		} catch (DatabaseException sqle) {
			sqle.printStackTrace();
		}

	}
}
