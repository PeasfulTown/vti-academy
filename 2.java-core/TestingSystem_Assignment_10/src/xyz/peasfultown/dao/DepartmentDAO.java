package xyz.peasfultown.dao;

import java.util.List;

import xyz.peasfultown.entity.DatabaseException;
import xyz.peasfultown.entity.Department;

public interface DepartmentDAO {
	public void create(String depName) throws DatabaseException;

	public List<Department> read() throws DatabaseException;

	public Department read(int id) throws DatabaseException;

	public void update(int id, String newName) throws DatabaseException;

	public void delete(int id) throws DatabaseException;
}
