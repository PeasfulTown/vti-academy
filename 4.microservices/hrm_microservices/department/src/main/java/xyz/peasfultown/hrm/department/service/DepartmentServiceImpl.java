package xyz.peasfultown.hrm.department.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.peasfultown.hrm.department.entity.Department;
import xyz.peasfultown.hrm.department.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository repo;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Department> getAllDepartments() {
        return repo.findAll();
    }

    @Override
    public Department getDepartmentById(short id) {
        return repo.findById(id).get();
    }
}
