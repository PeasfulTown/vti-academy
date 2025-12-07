package xyz.peasfultown.dao;

import java.time.LocalDateTime;
import java.util.List;

import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.DatabaseException;
import xyz.peasfultown.entity.Department;
import xyz.peasfultown.entity.Gender;
import xyz.peasfultown.entity.Position;

public interface AccountDAO {
	public void create(String email, String username, String fullname, Gender gender, Department department,
			Position position) throws DatabaseException;

	public List<Account> read() throws DatabaseException;

	public Account read(int id) throws DatabaseException;

	public void update(int id, String email, String username, String fullname, Gender gender, Department department,
			Position position, LocalDateTime createDate) throws DatabaseException;

	public void delete(int id) throws DatabaseException;
}
