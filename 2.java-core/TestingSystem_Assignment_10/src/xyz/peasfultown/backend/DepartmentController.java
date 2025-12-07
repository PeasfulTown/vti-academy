package xyz.peasfultown.backend;

import java.util.List;

import xyz.peasfultown.dao.DepartmentDAO;
import xyz.peasfultown.entity.DatabaseException;
import xyz.peasfultown.entity.Department;

public class DepartmentController {
	private DepartmentDAO dao;
	public DepartmentController(DepartmentDAO dao) {
		this.dao = dao;
	}
	
	public void show() throws DatabaseException {
		List<Department> departments = dao.read();
		for (Department d : departments) {
			System.out.println(d);
		}
	}
	
	public void show(int id) throws DatabaseException {
		Department dep = dao.read(id);
		if (dep != null)
			System.out.println(dep);
		else 
			System.out.println("No department found");
	}
	
	public void create(String name) throws DatabaseException {
		dao.create(name);
	}
	
	public void update(int id, String newName) throws DatabaseException {
		dao.update(id, newName);
	}
	
	public void delete(int id) throws DatabaseException {
		dao.delete(id);
	}
}
