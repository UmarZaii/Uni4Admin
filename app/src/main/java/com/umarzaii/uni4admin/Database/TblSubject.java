package com.umarzaii.uni4admin.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TblSubject {

    private DatabaseReference database;
    private DatabaseReference tblSubject;

    public TblSubject() {
        database = FirebaseDatabase.getInstance().getReference();
        tblSubject = database.child(DBConstants.tblSubject);
    }

    //ROOT
    public DatabaseReference getTable() {
        return tblSubject;
    }
    public DatabaseReference getTable(String subjectID) {
        return getTable().child(subjectID);
    }

    //DETAILS
    public DatabaseReference getSubjectName(String subjectID) {
        return getTable(subjectID).child(DBConstants.subjectName);
    }
    public DatabaseReference getFacultyID(String subjectID) {
        return getTable(subjectID).child(DBConstants.facultyID);
    }
    public DatabaseReference getDeptID(String subjectID) {
        return getTable(subjectID).child(DBConstants.deptID);
    }

//    //LIST LECTURER
//    public DatabaseReference getTableListLect(String subjectID) {
//        return getTableList(subjectID).child(DBConstants.tblLecturer);
//    }

}
