package com.umarzaii.uni4admin.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TblLecturer {

    private DatabaseReference database;
    private DatabaseReference tblLecturer;

    public TblLecturer() {
        database = FirebaseDatabase.getInstance().getReference();
        tblLecturer = database.child(DBConstants.tblLecturer);
    }

    //ROOT
    public DatabaseReference getTable() {
        return tblLecturer;
    }
    public DatabaseReference getTable(String lecturerID) {
        return getTable().child(lecturerID);
    }

    //DETAILS
    public DatabaseReference getFacultyID(String lecturerID) {
        return getTable(lecturerID).child(DBConstants.facultyID);
    }
    public DatabaseReference getDepartmentID(String lecturerID) {
        return getTable(lecturerID).child(DBConstants.deptID);
    }
    public DatabaseReference getDepartmentHead(String lecturerID) {
        return getTable(lecturerID).child(DBConstants.deptHead);
    }
    public DatabaseReference getDepartmentAdmin(String lecturerID) {
        return getTable(lecturerID).child(DBConstants.deptAdmin);
    }
    public DatabaseReference getLecturerName(String lecturerID) {
        return getTable(lecturerID).child(DBConstants.lecturerName);
    }

//    //LIST SUBJECT
//    public DatabaseReference getTableListSubj(String lecturerID) {
//        return getTableList(lecturerID).child(DBConstants.tblSubject);
//    }

}
