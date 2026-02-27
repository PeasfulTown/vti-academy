package xyz.peasfultown.hrm.department.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.peasfultown.hrm.department.dto.DepartmentDto;
import xyz.peasfultown.hrm.department.entity.Department;
import xyz.peasfultown.hrm.department.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/departments")
@CrossOrigin("*")
public class DepartmentController {
    private final DepartmentService service;

    @Autowired
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllDepartments() {
        List<Department> entities = service.getAllDepartments();
        List<DepartmentDto> dtos = new ArrayList<>();

        dtos =
                entities.stream().map(e -> new DepartmentDto(e.getId(),
                        e.getName())).collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable(value = "id") short id) {
        Department dep = service.getDepartmentById(id);
        DepartmentDto dto = new DepartmentDto(dep.getId(), dep.getName());
        return ResponseEntity.ok(dto);
    }

}
