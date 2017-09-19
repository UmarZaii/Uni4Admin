package com.umarzaii.uni4admin.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TblStudent {

    private DatabaseReference database;
    private DatabaseReference tblStudent;

    public TblStudent() {
        database = FirebaseDatabase.getInstance().getReference();
        tblStudent = database.child(DBConstants.tblStudent);
    }

    //ROOT
    public DatabaseReference getTable() {
        return tblStudent;
    }
    public DatabaseReference getTable(String studentID) {
        return tblStudent.child(studentID);
    }

    //DETAILS
    public DatabaseReference getFacultyID(String studentID) {
        return getTable(studentID).child(DBConstants.facultyID);
    }
    public DatabaseReference getDepartmentID(String studentID) {
        return getTable(studentID).child(DBConstants.deptID);
    }
    public DatabaseReference getCourseID(String studentID) {
        return getTable(studentID).child(DBConstants.courseID);
    }
    public DatabaseReference getUserClassID(String studentID) {
        return getTable(studentID).child(DBConstants.userClassID);
    }
    public DatabaseReference getSemesterID(String studentID) {
        return getTable(studentID).child(DBConstants.semesterID);
    }

}
