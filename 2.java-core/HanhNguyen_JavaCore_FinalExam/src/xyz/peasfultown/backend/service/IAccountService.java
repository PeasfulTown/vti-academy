package xyz.peasfultown.backend.service;

import java.time.LocalDate;
import java.util.List;

import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.Lecturer;
import xyz.peasfultown.entity.Student;

public interface IAccountService {
	public Account login(String email, String password) throws ServiceException;

	public List<Student> getStudents() throws ServiceException;

	public void createStudent(String fullname, String email, String password, LocalDate dob, String majorName)
			throws ServiceException;

	public void createLecturer(String fullname, String email, String password, LocalDate dob, String majorName)
			throws ServiceException;

	public void updateStudentMajor(int studentId, String newMajorName) throws ServiceException;

	// TODO: check student exists before deleting
	public void deleteStudent(int id) throws ServiceException;

	public List<Student> getStudentsByMajor(String majorName) throws ServiceException;

	public List<Lecturer> getLecturerByMajor(String majorName) throws ServiceException;
}
