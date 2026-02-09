package xyz.peasfultown.services;

import xyz.peasfultown.models.Department;

import java.util.List;

public interface IDepartmentService {
  List<Department> findAll();
}
