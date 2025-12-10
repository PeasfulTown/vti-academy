package xyz.peasfultown.backend.service.impl;

import java.util.List;

import xyz.peasfultown.backend.repository.IUserRepository;
import xyz.peasfultown.backend.repository.exception.DatabaseException;
import xyz.peasfultown.backend.repository.impl.MySQLUserRepository;
import xyz.peasfultown.backend.service.IUserService;
import xyz.peasfultown.backend.service.exception.ServiceException;
import xyz.peasfultown.backend.service.exception.ServiceIncorrectLoginInformationException;
import xyz.peasfultown.entity.User;
import xyz.peasfultown.entity.UserHashException;
import xyz.peasfultown.utils.HashUtils;

public class SimpleUserService implements IUserService {
	private final String AUTH_INVALID_CREDENTIALS_EXCPT = "Unable to authenticate user, invalid credentials";

	private IUserRepository repo;

	public SimpleUserService() {
		repo = new MySQLUserRepository();
	}

	@Override
	public void createEmployee(String fullname, String email, String pass, String skill) throws ServiceException {
		try {
			repo.createEmployee(fullname, email, HashUtils.generateHash(pass), skill);
		} catch (UserHashException uhe) {
			throw new ServiceException(uhe.getMessage(), uhe);
		} catch (DatabaseException dbe) {
			throw new ServiceException(dbe.getMessage(), dbe);
		}
	}

	@Override
	public List<User> getAllEmployees() throws ServiceException {
		try {
			return repo.read();
		} catch (DatabaseException dbe) {
			throw new ServiceException(dbe.getMessage(), dbe);
		}
	}

	@Override
	public User getEmployeeByID(int id) throws ServiceException {
		try {
			return repo.read(id);
		} catch (DatabaseException dbe) {
			throw new ServiceException(dbe.getMessage(), dbe);
		}
	}

	@Override
	public User authenticate(String email, String inPassword) throws ServiceIncorrectLoginInformationException, ServiceException  {
		try {
			User user = repo.read(email);
			if (user == null)
				throw new ServiceIncorrectLoginInformationException(AUTH_INVALID_CREDENTIALS_EXCPT);
			if (HashUtils.auth(inPassword, user.getPassword()))
				return user;
			else
				throw new ServiceIncorrectLoginInformationException(AUTH_INVALID_CREDENTIALS_EXCPT);
		} catch (UserHashException uhe) {
			throw new ServiceException(uhe.getMessage(), uhe);
		} catch (DatabaseException dbe) {
			throw new ServiceException(dbe.getMessage(), dbe);
		}
	}

	@Override
	public boolean checkUserIsAdmin(String email) throws ServiceException {
		try {
			return repo.isAdmin(email);
		} catch (DatabaseException dbe) {
			throw new ServiceException(dbe.getMessage(), dbe);
		}
	}

	@Override
	public void deleteUser(int id) throws ServiceException {
		try {
			repo.delete(id);
		} catch (DatabaseException dbe) {
			throw new ServiceException(dbe.getMessage(), dbe);
		}
	}
}
