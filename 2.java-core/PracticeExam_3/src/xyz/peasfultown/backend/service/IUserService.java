package xyz.peasfultown.backend.service;

import java.util.List;

import xyz.peasfultown.backend.service.exception.ServiceException;
import xyz.peasfultown.backend.service.exception.ServiceIncorrectLoginInformationException;
import xyz.peasfultown.entity.User;

public interface IUserService {
	public void createEmployee(String fullname, String email, String pass, String skill) throws ServiceException;
	
	public List<User> getAllEmployees() throws ServiceException;

	public User getEmployeeByID(int id) throws ServiceException;
	
	public User authenticate(String email, String password) throws ServiceException, ServiceIncorrectLoginInformationException;
	
	public boolean checkUserIsAdmin(String email) throws ServiceException;

	public void deleteUser(int id) throws ServiceException;
}
