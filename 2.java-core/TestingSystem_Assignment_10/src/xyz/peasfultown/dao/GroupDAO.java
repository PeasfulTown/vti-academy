package xyz.peasfultown.dao;

import java.sql.SQLException;
import java.util.List;

import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.Group;

public interface GroupDAO {
	public void create(String name, Account creator) throws SQLException;

	public List<Group> read() throws SQLException;

	public Group read(int id) throws SQLException;

	public void update(int id, String newName) throws SQLException;

	public void delete(int id) throws SQLException;
}
