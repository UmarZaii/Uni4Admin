package com.umarzaii.uni4admin.Model;

public class ClassLocationModel {

    private String classLocationID;
    private String classLocationName;
    private String facultyID;

    public ClassLocationModel() {
    }

    public String getClassLocationID() {
        return classLocationID;
    }

    public void setClassLocationID(String classLocationID) {
        this.classLocationID = classLocationID;
    }

    public String getClassLocationName() {
        return classLocationName;
    }

    public void setClassLocationName(String classLocationName) {
        this.classLocationName = classLocationName;
    }

    public String getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(String facultyID) {
        this.facultyID = facultyID;
    }
}
