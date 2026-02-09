package xyz.peasfultown.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.peasfultown.models.Department;
import xyz.peasfultown.repositories.IDepartmentRepository;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {
  private final IDepartmentRepository repo;

  @Autowired
  public DepartmentService(IDepartmentRepository repo) {
    this.repo = repo;
  }

  @Override
  public List<Department> findAll() {
    return repo.findAll();
  }
}
