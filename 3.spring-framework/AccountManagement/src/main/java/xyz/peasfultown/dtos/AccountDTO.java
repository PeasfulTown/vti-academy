package xyz.peasfultown.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import xyz.peasfultown.models.Department;
import xyz.peasfultown.models.Position;

import java.util.Date;

public class AccountDTO {
  private short id;
  private String email;
  private String username;
  private String fullname;
  private Department department;
  private Position position;

  @JsonFormat(pattern = "yyyy-MM-dd")
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
