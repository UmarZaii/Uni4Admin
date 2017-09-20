package com.umarzaii.uni4admin.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TblClassLocation {

    private DatabaseReference database;
    private DatabaseReference tblClassLocation;

    public TblClassLocation() {
        database = FirebaseDatabase.getInstance().getReference();
        tblClassLocation = database.child(DBConstants.tblClassLocation);
    }

    //ROOT
    public DatabaseReference getTable() {
        return tblClassLocation;
    }
    public DatabaseReference getTable(String classLocationID) {
        return getTable().child(classLocationID);
    }

    //DETAILS
    public DatabaseReference getClassLocationID(String classLocationID) {
        return getTable(classLocationID).child(DBConstants.classLocationID);
    }
    public DatabaseReference getClassLocationName(String classLocationID) {
        return getTable(classLocationID).child(DBConstants.classLocationName);
    }
    public DatabaseReference getFacultyID(String classLocationID) {
        return getTable(classLocationID).child(DBConstants.facultyID);
    }

}
