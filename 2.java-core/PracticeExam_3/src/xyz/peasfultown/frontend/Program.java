package xyz.peasfultown.frontend;

import java.util.List;

import xyz.peasfultown.backend.api.UserController;
import xyz.peasfultown.backend.api.exception.APIException;
import xyz.peasfultown.backend.api.exception.APIInvalidCredentialsException;
import xyz.peasfultown.entity.Admin;
import xyz.peasfultown.entity.Employee;
import xyz.peasfultown.entity.User;

public class Program {
	public static void main(String[] args) {
		UserController uc = new UserController();
		try {
			User usr = uc.authenticate("admin2@example.com", "admin");
			System.out.println(usr);
			if (usr instanceof Admin)
				System.out.println("Authenticated as an Admin");
			else if (usr instanceof Employee)
				System.out.println("Authenticated as an Employee");
		} catch (APIInvalidCredentialsException aivce) {
			System.err.println("Unable to log in, invalid credentials");
		} catch (APIException apie) {
			apie.printStackTrace();
		}
	}
}
