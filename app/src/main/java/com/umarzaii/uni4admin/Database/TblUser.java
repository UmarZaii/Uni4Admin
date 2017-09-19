package com.umarzaii.uni4admin.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TblUser {

    private DatabaseReference database;
    private DatabaseReference tblUser;

    public TblUser() {
        database = FirebaseDatabase.getInstance().getReference();
        tblUser = database.child(DBConstants.tblUser);
    }

    //ROOT
    public DatabaseReference getTable() {
        return tblUser;
    }
    public DatabaseReference getTable(String userID) {
        return tblUser.child(userID);
    }

    //DETAILS
    public DatabaseReference getUserEmail(String userID) {
        return getTable(userID).child(DBConstants.userEmail);
    }
    public DatabaseReference getStudentID(String userID) {
        return getTable(userID).child(DBConstants.studentID);
    }
    public DatabaseReference getLecturerID(String userID) {
        return getTable(userID).child(DBConstants.lecturerID);
    }
    public DatabaseReference getUserRole(String userID) {
        return getTable(userID).child(DBConstants.userRole);
    }

}
