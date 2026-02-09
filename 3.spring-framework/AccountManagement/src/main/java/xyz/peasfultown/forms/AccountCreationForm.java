package xyz.peasfultown.forms;

import xyz.peasfultown.exception.BadRequestException;

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
        if (departmentId < 0) throw new BadRequestException("Department ID cannot be lower than 0");
        this.departmentId = departmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.isEmpty()) throw new BadRequestException("Email field cannot be blank");
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        if (fullname.isEmpty()) throw new BadRequestException("Fullname cannot be blank");
        this.fullname = fullname;
    }

    public short getPositionId() {
        return positionId;
    }

    public void setPositionId(short positionId) {
        if (positionId < 0) throw new BadRequestException("Position ID cannot be lower than 0");
        this.positionId = positionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.isBlank()) throw new BadRequestException("Username cannot be blank");
        this.username = username;
    }
}
