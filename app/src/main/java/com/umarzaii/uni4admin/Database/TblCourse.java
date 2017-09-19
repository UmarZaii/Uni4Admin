package com.umarzaii.uni4admin.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TblCourse {

    private DatabaseReference database;
    private DatabaseReference tblCourse;

    public TblCourse() {
        database = FirebaseDatabase.getInstance().getReference();
        tblCourse = database.child(DBConstants.tblCourse);
    }

    //ROOT
    public DatabaseReference getTable() {
        return tblCourse;
    }
    public DatabaseReference getTable(String courseID) {
        return getTable().child(courseID);
    }

    //DETAILS
    public DatabaseReference getCourseName(String courseID) {
        return getTable(courseID).child(DBConstants.courseName);
    }
    public DatabaseReference getDepartmentID(String courseID) {
        return getTable(courseID).child(DBConstants.deptID);
    }
    public DatabaseReference getFacultyID(String courseID) {
        return getTable(courseID).child(DBConstants.facultyID);
    }

}
