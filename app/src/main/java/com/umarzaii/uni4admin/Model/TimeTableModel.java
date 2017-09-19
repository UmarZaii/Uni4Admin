package com.umarzaii.uni4admin.Model;

public class TimeTableModel {

    private String dayID;
    private String timeID;
    private String userClassID;
    private String classLocationID;
    private String subjectID;
    private String lecturerID;

    public TimeTableModel() {
    }

    public String getDayID() {
        return dayID;
    }

    public void setDayID(String dayID) {
        this.dayID = dayID;
    }

    public String getTimeID() {
        return timeID;
    }

    public void setTimeID(String timeID) {
        this.timeID = timeID;
    }

    public String getUserClassID() {
        return userClassID;
    }

    public void setUserClassID(String userClassID) {
        this.userClassID = userClassID;
    }

    public String getClassLocationID() {
        return classLocationID;
    }

    public void setClassLocationID(String classLocationID) {
        this.classLocationID = classLocationID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
    }
}
