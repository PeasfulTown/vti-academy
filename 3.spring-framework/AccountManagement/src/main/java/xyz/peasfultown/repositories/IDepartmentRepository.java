package xyz.peasfultown.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.peasfultown.models.Department;

public interface IDepartmentRepository extends JpaRepository<Department, Short> {
}
