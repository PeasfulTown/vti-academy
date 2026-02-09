package xyz.peasfultown.forms;

public class AccountUpdateForm {
  private String fullname;
  private short departmentId;
  private short positionId;

  public short getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(short departmentId) {
    this.departmentId = departmentId;
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
}
