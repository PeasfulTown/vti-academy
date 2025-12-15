package xyz.peasfultown.backend.service;

import java.time.LocalDate;
import java.util.List;

import xyz.peasfultown.backend.repository.IAccountRepository;
import xyz.peasfultown.backend.repository.MySQLAccountRepository;
import xyz.peasfultown.backend.repository.RepositoryException;
import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.Lecturer;
import xyz.peasfultown.entity.Student;
import xyz.peasfultown.utils.HashUtils;
import xyz.peasfultown.utils.exception.UserHashException;

public class SimpleAccountService implements IAccountService{
	private IAccountRepository repo;
	public SimpleAccountService() {
		this.repo = new MySQLAccountRepository();
	}

	@Override
	public void createStudent(String fullname, String email, String password, LocalDate dob, String majorName)
			throws ServiceException {
		try { 
			if (repo.getAccountByEmail(email) != null) 
				throw new ServiceAccountAlreadyExistsException("Unable to create account, email already exists");
			else 
				repo.createStudent(fullname, email, HashUtils.generateHash(password), dob, majorName);
		} catch (UserHashException uhe) {
			throw new ServiceException(uhe.getMessage(), uhe);
		} catch (RepositoryException re) {
			throw new ServiceException(re.getMessage(), re);
		}
	}
	
	@Override
	public void createLecturer(String fullname, String email, String password, LocalDate dob, String majorName)
			throws ServiceException {
		try { 
			if (repo.getAccountByEmail(email) != null) 
				throw new ServiceAccountAlreadyExistsException("Unable to create account, email already exists");
			else 
				repo.createLecturer(fullname, email, HashUtils.generateHash(password), dob, majorName);
		} catch (UserHashException uhe) {
			throw new ServiceException(uhe.getMessage(), uhe);
		} catch (RepositoryException re) {
			throw new ServiceException(re.getMessage(), re);
		}
	}

	@Override
	public List<Student> getStudents() throws ServiceException {
		try {
			return repo.getStudents();
		} catch (RepositoryException re) {
			throw new ServiceException(re.getMessage(), re);
		}
	}

	@Override
	public void updateStudentMajor(int studentId, String newMajorName) throws ServiceException {
		try {
			if (repo.studentExists(studentId))
				repo.updateStudentMajor(studentId, newMajorName);
			else 
				throw new ServiceAccountNotExistsException("Student by this ID does not exist");
		} catch (RepositoryException re) {
			throw new ServiceException(re.getMessage(), re);
		}
	}

	@Override
	public void deleteStudent(int id) throws ServiceException {
		try {
			if (repo.studentExists(id))
				repo.deleteStudent(id);
			else 
				throw new ServiceAccountNotExistsException("Student by this ID does not exist");
		} catch (RepositoryException re) {
			throw new ServiceException(re.getMessage(), re);
		}
		
	}
	
	@Override
	public List<Student> getStudentsByMajor(String majorName) throws ServiceException {
		try {
			return repo.getStudentByMajor(majorName);
		} catch (RepositoryException re) {
			throw new ServiceException(re.getMessage(), re);
		}
	}

	@Override
	public List<Lecturer> getLecturerByMajor(String majorName) throws ServiceException {
		try {
			return repo.getLecturersByMajor(majorName);
		} catch (RepositoryException re) {
			throw new ServiceException(re.getMessage(), re);
		}
	}

	@Override
	public Account login(String email, String password) throws ServiceException {
		try {
			Account acc = repo.getAccountByEmail(email);
			if (acc == null) {
				throw new ServiceInvalidCredentialsException("Unable to login, no account by that email");
			} else {
				if (HashUtils.auth(password, acc.getPassword())) {
					return acc;
				} else {
					throw new ServiceInvalidCredentialsException("Unable to login, invalid password");
				}
			}
		} catch (UserHashException uhe) {
			throw new ServiceException(uhe.getMessage(), uhe);
		} catch (RepositoryException re) {
			throw new ServiceException(re.getMessage(), re);
		}
	}

}
