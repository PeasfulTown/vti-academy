package xyz.peasfultown.backend.repository;

import java.time.LocalDate;
import java.util.List;

import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.Lecturer;
import xyz.peasfultown.entity.Student;

public interface IAccountRepository {
	public Account getAccountByEmail(String email) throws RepositoryException;

	public List<Student> getStudents() throws RepositoryException;

	public void createStudent(String fullname, String email, String password, LocalDate dob, String majorName)
			throws RepositoryException;
	
	public void createLecturer(String fullname, String email, String password, LocalDate dob, String majorName)
			throws RepositoryException;
	
	public void updateStudentMajor(int studentId, String newMajorName) throws RepositoryException;
	
	public boolean studentExists(int studentId) throws RepositoryException;
	
	public void deleteStudent(int studentId) throws RepositoryException;
	
	public List<Student> getStudentByMajor(String majorName) throws RepositoryException;
	
	public List<Lecturer> getLecturersByMajor(String majorName) throws RepositoryException;
	
}
