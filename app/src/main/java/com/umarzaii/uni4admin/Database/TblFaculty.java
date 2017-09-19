package com.umarzaii.uni4admin.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TblFaculty {

    private DatabaseReference database;
    private DatabaseReference tblFaculty;

    public TblFaculty() {
        database = FirebaseDatabase.getInstance().getReference();
        tblFaculty = database.child(DBConstants.tblFaculty);
    }

    //ROOT
    public DatabaseReference getTable() {
        return tblFaculty;
    }
    public DatabaseReference getTable(String facultyID) {
        return getTable().child(facultyID);
    }

    //DETAILS
    public DatabaseReference getName(String facultyID) {
        return getTable(facultyID).child(DBConstants.facultyName);
    }

}
