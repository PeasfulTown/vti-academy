package xyz.peasfultown.hrm.department.service;

import xyz.peasfultown.hrm.department.entity.Department;

import java.util.List;

public interface DepartmentService {
    public List<Department> getAllDepartments();
    public Department getDepartmentById(short id);
}
