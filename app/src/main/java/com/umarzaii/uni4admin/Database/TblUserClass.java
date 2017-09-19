package com.umarzaii.uni4admin.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TblUserClass {

    private DatabaseReference database;
    private DatabaseReference tblUserClass;

    public TblUserClass() {
        database = FirebaseDatabase.getInstance().getReference();
        tblUserClass = database.child(DBConstants.tblUserClass);
    }

    //ROOT
    public DatabaseReference getTable() {
        return tblUserClass;
    }
    public DatabaseReference getTable(String userClassID) {
        return getTable().child(userClassID);
    }

    //DETAILS
    public DatabaseReference getDepartmentID(String userClassID) {
        return getTable(userClassID).child(DBConstants.deptID);
    }
    public DatabaseReference getFacultyID(String userClassID) {
        return getTable(userClassID).child(DBConstants.facultyID);
    }
    public DatabaseReference getCourseID(String userClassID) {
        return getTable(userClassID).child(DBConstants.courseID);
    }
    public DatabaseReference getSemesterID(String userClassID) {
        return getTable(userClassID).child(DBConstants.semesterID);
    }

}
