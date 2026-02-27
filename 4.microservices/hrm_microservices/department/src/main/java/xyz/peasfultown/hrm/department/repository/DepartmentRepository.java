package xyz.peasfultown.hrm.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.peasfultown.hrm.department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Short> {
    public Department findByName(String name);
    public boolean existsByName(String name);
}
