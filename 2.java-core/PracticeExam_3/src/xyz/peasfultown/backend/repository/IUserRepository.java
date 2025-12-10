package xyz.peasfultown.backend.repository;

import java.util.List;

import xyz.peasfultown.backend.repository.exception.DatabaseException;
import xyz.peasfultown.entity.User;

public interface IUserRepository {
	public void createEmployee(String fullname, String email, String pass, String skill) throws DatabaseException;
	
	public List<User> read() throws DatabaseException;

	public User read(int id) throws DatabaseException;
	
	public User read(String email) throws DatabaseException;
	
	public boolean isAdmin(String email) throws DatabaseException;

	public void delete(int id) throws DatabaseException;
}
