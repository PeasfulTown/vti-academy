package xyz.peasfultown.dao;

import java.sql.SQLException;
import java.util.List;

import xyz.peasfultown.entity.Department;

public interface DepartmentDAO {
	public void create(String depName) throws SQLException;

	public List<Department> read() throws SQLException;

	public Department read(int id) throws SQLException;

	public void update(int id, String newName) throws SQLException;

	public void delete(int id) throws SQLException;
}
