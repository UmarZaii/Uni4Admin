package com.umarzaii.uni4admin.Model;

public class LecturerModel {

    private String lecturerID;
    private String lecturerName;
    private String facultyID;
    private String deptID;
    private Boolean deptAdmin;
    private Boolean deptHead;

    public LecturerModel() {
    }

    public String getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(String facultyID) {
        this.facultyID = facultyID;
    }

    public String getDeptID() {
        return deptID;
    }

    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    public Boolean getDeptAdmin() {
        return deptAdmin;
    }

    public void setDeptAdmin(Boolean deptAdmin) {
        this.deptAdmin = deptAdmin;
    }

    public Boolean getDeptHead() {
        return deptHead;
    }

    public void setDeptHead(Boolean deptHead) {
        this.deptHead = deptHead;
    }
}
