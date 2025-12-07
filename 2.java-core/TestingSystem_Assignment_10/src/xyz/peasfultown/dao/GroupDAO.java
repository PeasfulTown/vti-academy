package xyz.peasfultown.dao;

import java.util.List;

import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.DatabaseException;
import xyz.peasfultown.entity.Group;

public interface GroupDAO {
	public void create(String name, Account creator) throws DatabaseException;

	public List<Group> read() throws DatabaseException;

	public Group read(int id) throws DatabaseException;

	public void update(int id, String newName) throws DatabaseException;

	public void delete(int id) throws DatabaseException;
}
