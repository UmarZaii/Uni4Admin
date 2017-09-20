package com.umarzaii.uni4admin.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TblAdmin {

    private DatabaseReference database;
    private DatabaseReference tblAdmin;

    public TblAdmin() {
        database = FirebaseDatabase.getInstance().getReference();
        tblAdmin = database.child(DBConstants.tblAdmin);
    }

    //ROOT
    public DatabaseReference getTable() {
        return tblAdmin;
    }
    public DatabaseReference getTable(String adminID) {
        return getTable().child(adminID);
    }

    //DETAILS
    public DatabaseReference getAdminEmail(String adminID) {
        return getTable(adminID).child(DBConstants.adminEmail);
    }

}
