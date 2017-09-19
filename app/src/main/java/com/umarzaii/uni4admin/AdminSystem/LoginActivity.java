package com.umarzaii.uni4admin.AdminSystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.umarzaii.uni4admin.Controller.FirebaseController;
import com.umarzaii.uni4admin.Database.TblAdmin;
import com.umarzaii.uni4admin.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseController controller;

    private TblAdmin tblAdmin;

    private EditText edtAdminEmailLogin, edtAdminPassLogin;
    private Button btnLogin, btnGoToSignUp;
    private ProgressDialog progressDialog;

    private String strAdminEmail, strAdminPass, strAdminID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);

        controller = new FirebaseController();

        tblAdmin = new TblAdmin();

        edtAdminEmailLogin = (EditText)findViewById(R.id.edtAdminEmailLogin);
        edtAdminPassLogin = (EditText)findViewById(R.id.edtAdminPassLogin);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnGoToSignUp = (Button)findViewById(R.id.btnGoToSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strAdminEmail = edtAdminEmailLogin.getText().toString().trim();
                strAdminPass = edtAdminPassLogin.getText().toString().trim();

                if (inputCheck()) {
                    userLogin();
                }
            }
        });

        btnGoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

    }

    private boolean inputCheck() {

        if(TextUtils.isEmpty(strAdminEmail) || strAdminEmail == null) {
            Toast.makeText(this, "Please input your email", Toast.LENGTH_LONG).show();
            return false;
        } else if(TextUtils.isEmpty(strAdminPass) || strAdminPass == null) {
            Toast.makeText(this, "Please input your password", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }

    }

    private void userLogin() {

        progressDialog.setMessage("LogIn, Please Wait...");
        progressDialog.show();

        controller.getFirebaseAuth().signInWithEmailAndPassword(strAdminEmail,strAdminPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Error", task.getException().toString());
                } else  {
                    adminCheck();
                }
            }
        });

    }

    private void adminCheck() {
        strAdminID = controller.getUserID();

        tblAdmin.getTable().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(strAdminID) && controller.getCurrentUser().isEmailVerified()) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    progressDialog.dismiss();
                } else if (!dataSnapshot.hasChild(strAdminID)) {
                    Toast.makeText(LoginActivity.this, "Please enter valid admin email", Toast.LENGTH_SHORT).show();
                    controller.getFirebaseAuth().signOut();
                    progressDialog.dismiss();
                } else {
                    Intent intent = new Intent(LoginActivity.this, ActivationActivity.class);
                    intent.putExtra("adminEmail", strAdminEmail);
                    intent.putExtra("adminPass", strAdminPass);
                    startActivity(intent);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}