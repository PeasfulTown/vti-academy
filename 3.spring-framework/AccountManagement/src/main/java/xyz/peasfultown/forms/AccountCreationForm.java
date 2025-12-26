package xyz.peasfultown.forms;

public class AccountCreationForm {
  private String email;
  private String username;
  private String fullname;
  private short departmentId;
  private short positionId;

  public short getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(short departmentId) {
    this.departmentId = departmentId;
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

  public short getPositionId() {
    return positionId;
  }

  public void setPositionId(short positionId) {
    this.positionId = positionId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
