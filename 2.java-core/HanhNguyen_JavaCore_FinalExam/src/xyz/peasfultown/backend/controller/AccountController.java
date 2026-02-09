package xyz.peasfultown.backend.controller;

import java.time.LocalDate;
import java.util.List;

import xyz.peasfultown.backend.service.IAccountService;
import xyz.peasfultown.backend.service.ServiceException;
import xyz.peasfultown.backend.service.SimpleAccountService;
import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.Lecturer;
import xyz.peasfultown.entity.Student;

public class AccountController {
	private IAccountService service;
	public AccountController() {
		this.service = new SimpleAccountService();
	}
	public void createStudent(String fullname, String email, String password, LocalDate dob, String majorName) throws ControllerException {
		try {
			service.createStudent(fullname, email, password, dob, majorName);
		} catch (ServiceException se) {
			throw new ControllerException(se.getMessage(), se);
		}
	}
	
	public void createLecturer(String fullname, String email, String password, LocalDate dob, String majorName) throws ControllerException {
		try {
			service.createLecturer(fullname, email, password, dob, majorName);
		} catch (ServiceException se) {
			throw new ControllerException(se.getMessage(), se);
		}
	}
	
	public List<Student> getStudents() throws ControllerException {
		try {
			return service.getStudents();
		} catch (ServiceException se) {
			throw new ControllerException(se.getMessage(), se);
		}
	}
	
	public void updateStudentMajor(int studentId, String newMajorName) throws ControllerException {
		try {
			service.updateStudentMajor(studentId, newMajorName);
		} catch (ServiceException se) {
			throw new ControllerException(se.getMessage(), se);
		}
	}
	
	public void deleteStudent(int studentId) throws ControllerException {
		try {
			service.deleteStudent(studentId);
		} catch (ServiceException se) {
			throw new ControllerException(se.getMessage(), se);
		}
	}
	
	public List<Student> getStudentsByMajorName(String majorName) throws ControllerException {
		try {
			return service.getStudentsByMajor(majorName);
		} catch (ServiceException se) {
			throw new ControllerException(se.getMessage(), se);
		} 
	}
	
	public List<Lecturer> getLecturersByMajor(String majorName) throws ControllerException {
		try {
			return service.getLecturerByMajor(majorName);
		} catch (ServiceException se) {
			throw new ControllerException(se.getMessage(), se);
		} 
	}
	
	public Account login(String email, String password) throws ControllerException {
		try {
			return service.login(email, password);
		} catch (ServiceException se) {
			throw new ControllerException(se.getMessage(), se);
		}
	}
}
