package com.umarzaii.uni4admin.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TblDepartment {

    private DatabaseReference database;
    private DatabaseReference tblDepartment;

    public TblDepartment() {
        database = FirebaseDatabase.getInstance().getReference();
        tblDepartment = database.child(DBConstants.tblDepartment);
    }

    //ROOT
    public DatabaseReference getTable() {
        return tblDepartment;
    }
    public DatabaseReference getTable(String deptID) {
        return getTable().child(deptID);
    }

    //DETAILS
    public DatabaseReference getDepartmentName(String deptID) {
        return getTable(deptID).child(DBConstants.deptName);
    }
    public DatabaseReference getDepartmentHead(String deptID) {
        return getTable(deptID).child(DBConstants.deptHead);
    }
    public DatabaseReference getDepartmentAdmin(String deptID) {
        return getTable(deptID).child(DBConstants.deptAdmin);
    }
    public DatabaseReference getFacultyID(String deptID) {
        return getTable(deptID).child(DBConstants.facultyID);
    }

}
