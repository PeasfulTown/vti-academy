package xyz.peasfultown.backend.api;

import java.util.List;

import xyz.peasfultown.backend.api.exception.APIException;
import xyz.peasfultown.backend.api.exception.APIInvalidCredentialsException;
import xyz.peasfultown.backend.service.IUserService;
import xyz.peasfultown.backend.service.exception.ServiceException;
import xyz.peasfultown.backend.service.exception.ServiceIncorrectLoginInformationException;
import xyz.peasfultown.backend.service.impl.SimpleUserService;
import xyz.peasfultown.entity.User;

public class UserController {
	private IUserService service;
	public UserController() {
		this.service = new SimpleUserService();
	}
	
	public void createEmployee(String fullname, String email, String pass, String skill) throws APIException {
		try {
			service.createEmployee(fullname, email, pass, skill);			
		} catch (ServiceException sve) {
			throw new APIException(sve.getMessage(), sve);
		}
	};
	
	public List<User> getAllEmployees() throws APIException {
		try {
			return service.getAllEmployees();			
		} catch (ServiceException sve) {
			throw new APIException(sve.getMessage(), sve);
		}
	}

	public User getEmployeeByID(int id) throws APIException {
		try {
			return service.getEmployeeByID(id);			
		} catch (ServiceException sve) {
			throw new APIException(sve.getMessage(), sve);
		}
	}
	
	public User authenticate(String email, String password) throws APIInvalidCredentialsException, APIException {
		try {
			return service.authenticate(email, password);			
		} catch (ServiceIncorrectLoginInformationException silie) {
			throw new APIInvalidCredentialsException(silie.getMessage(), silie);
		} catch (ServiceException sve) {
			throw new APIException(sve.getMessage(), sve);
		}
	}
	
	public boolean checkUserIsAdmin(String email) throws APIException {
		try {
			return service.checkUserIsAdmin(email);			
		} catch (ServiceException sve) {
			throw new APIException(sve.getMessage(), sve);
		}
	}

	public void deleteUser(int id) throws APIException {
		try {
			service.deleteUser(id);			
		} catch (ServiceException sve) {
			throw new APIException(sve.getMessage(), sve);
		}
	}

}
