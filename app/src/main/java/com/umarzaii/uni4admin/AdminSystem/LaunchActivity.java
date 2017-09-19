package com.umarzaii.uni4admin.AdminSystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.umarzaii.uni4admin.Controller.FirebaseController;
import com.umarzaii.uni4admin.R;

public class LaunchActivity extends AppCompatActivity {


    private FirebaseController controller;
    private FirebaseAuth.AuthStateListener fAuthListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        controller = new FirebaseController();

        fAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    checkUserLogin();
                } else {
                    startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        controller.getFirebaseAuth().addAuthStateListener(fAuthListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        controller.getFirebaseAuth().removeAuthStateListener(fAuthListener);
    }

    private void checkUserLogin() {

        if (controller.getCurrentUser().isEmailVerified()) {
            startActivity(new Intent(LaunchActivity.this, MainActivity.class));
            finish();
        } else {
            controller.getFirebaseAuth().signOut();
            startActivity(new Intent(LaunchActivity.this, LoginActivity.class));
            finish();
        }

    }

}
