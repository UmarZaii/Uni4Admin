package com.umarzaii.uni4admin.Controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.umarzaii.uni4admin.Database.DBConstants;

public class FirebaseController {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    private DBConstants constants;
    private DatabaseReference database;

    private String userID;
    private static boolean isPersistenceEnabled = false;

    public FirebaseController() {
        if (!isPersistenceEnabled) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            isPersistenceEnabled = true;
        }
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            userID = auth.getCurrentUser().getUid();
        }
        constants = new DBConstants();
        database = FirebaseDatabase.getInstance().getReference();
    }

    public FirebaseAuth getFirebaseAuth() {
        return auth;
    }

    public FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }

    public String getUserID() {
        if (auth.getCurrentUser() != null) {
            userID = auth.getCurrentUser().getUid();
        }
        return userID;
    }

}
