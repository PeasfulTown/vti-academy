package xyz.peasfultown.models;

import jakarta.persistence.*;

import javax.security.auth.login.AccountException;
import java.util.List;

@Entity
@Table(name = "Department", catalog = "Testing_System")
public class Department {
  @Column(name = "DepartmentID")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private short id;

  @Column(name = "DepartmentName", length = 30, nullable = false, unique = true)
  private String name;

  @OneToMany(mappedBy = "department")
  List<Account> listAccounts;

  public short getId() {
    return id;
  }

  public void setId(short id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
