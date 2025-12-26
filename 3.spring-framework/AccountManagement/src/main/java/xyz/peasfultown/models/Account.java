package xyz.peasfultown.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "`Account`", catalog = "Testing_System")
public class Account {
  @Column(name = "AccountID")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private short id;

  @Column(name = "Email", length = 50, nullable = false, unique = true)
  private String email;

  @Column(name = "Username", length = 50, nullable = false, unique = true)
  private String username;

  @Column(name = "Fullname", length = 50, nullable = false)
  private String fullname;

  @ManyToOne
  @JoinColumn(name = "DepartmentID", nullable = false)
  private Department department;

  @ManyToOne
  @JoinColumn(name = "PositionID", nullable = false)
  private Position position;

  @Column(name = "CreateDate")
  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  private Date createDate;

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public short getId() {
    return id;
  }

  public void setId(short id) {
    this.id = id;
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
