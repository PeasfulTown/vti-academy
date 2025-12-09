package xyz.peasfultown.dao;

import java.util.List;

import xyz.peasfultown.entity.DatabaseException;
import xyz.peasfultown.entity.User;
import xyz.peasfultown.entity.UserHashException;

public interface UserDAO {
	public void createEmployee(String fullname, String email, String pass, String skill) throws DatabaseException, UserHashException;
	
	public List<User> read() throws DatabaseException;

	public User read(int id) throws DatabaseException;
	
	public User auth(String email, String password) throws DatabaseException, UserHashException;
	
	public boolean isAdmin(String email) throws DatabaseException;
	
	public boolean isAdmin(User user) throws DatabaseException;

	public void delete(int id) throws DatabaseException;
}
