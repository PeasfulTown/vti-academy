package xyz.peasfultown.hrm.department.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Department", catalog = "TestingSystem")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Department implements Serializable  {
    @Column(name = "DepartmentID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Column(name = "DepartmentName", length = 30, nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "department")
    private List<Account> account;}
