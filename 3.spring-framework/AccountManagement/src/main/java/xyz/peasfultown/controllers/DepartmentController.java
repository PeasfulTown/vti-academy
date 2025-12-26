package xyz.peasfultown.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.peasfultown.dtos.DepartmentDTO;
import xyz.peasfultown.models.Department;
import xyz.peasfultown.services.IDepartmentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/vi/departments")
@CrossOrigin("*")
public class DepartmentController {
  private final IDepartmentService service;

  @Autowired
  public DepartmentController(IDepartmentService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<?> getAllDepartments() {
    List<Department> departments = service.findAll();
    List<DepartmentDTO> dtos = new ArrayList<>();

    for (Department d : departments) {
      DepartmentDTO ddto = new DepartmentDTO();
      ddto.setId(d.getId());
      ddto.setName(d.getName());
      dtos.add(ddto);
    }
    return new ResponseEntity<>(dtos,HttpStatus.OK);
  }
}
