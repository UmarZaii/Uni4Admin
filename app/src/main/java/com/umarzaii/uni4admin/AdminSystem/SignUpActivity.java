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
import com.umarzaii.uni4admin.Controller.FirebaseController;
import com.umarzaii.uni4admin.Database.TblAdmin;
import com.umarzaii.uni4admin.Mapper.AdminMapper;
import com.umarzaii.uni4admin.Model.AdminModel;
import com.umarzaii.uni4admin.R;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseController controller;

    private TblAdmin tblAdmin;

    private EditText edtAdminNameReg, edtAdminEmailReg, edtAdminPassReg;
    private Button btnSignUp;
    private ProgressDialog progressDialog;

    private String strAdminNameReg, strAdminEmailReg, strAdminPassReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);

        controller = new FirebaseController();

        tblAdmin = new TblAdmin();

        edtAdminNameReg = (EditText)findViewById(R.id.edtAdminNameReg);
        edtAdminEmailReg = (EditText)findViewById(R.id.edtAdminEmailReg);
        edtAdminPassReg = (EditText)findViewById(R.id.edtAdminPassReg);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strAdminNameReg = edtAdminNameReg.getText().toString().trim();
                strAdminEmailReg = edtAdminEmailReg.getText().toString().trim();
                strAdminPassReg = edtAdminPassReg.getText().toString().trim();

                if (inputCheck()) {
                    signUp();
                }
            }
        });

    }

    private boolean inputCheck() {
        if(TextUtils.isEmpty(strAdminNameReg)) {
            Toast.makeText(SignUpActivity.this, "Please input your user name", Toast.LENGTH_LONG).show();
            return false;
        } else if (TextUtils.isEmpty(strAdminEmailReg)){
            Toast.makeText(SignUpActivity.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(strAdminPassReg)){
            Toast.makeText(SignUpActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void signUp() {

        progressDialog.setMessage("Signing Up, Please Wait...");
        progressDialog.show();

        controller.getFirebaseAuth().createUserWithEmailAndPassword(strAdminEmailReg,strAdminPassReg).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){

                    progressDialog.dismiss();
                    Log.d("Unsuccessfull", task.getException().toString());
                    Toast.makeText(SignUpActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

                } else {

                    final String strUserIDReg = controller.getUserID();
                    final Map<String, Object> dataMap = new HashMap<String, Object>();

//                    AdminModel adminModel = new AdminModel(strUserIDReg,strAdminEmailReg,strAdminNameReg);
                    AdminModel model = new AdminModel();
                    model.setAdminEmail(strAdminEmailReg);
                    model.setAdminName(strAdminNameReg);
                    AdminMapper mapper = new AdminMapper(model);

                    dataMap.put(strUserIDReg, mapper.detailsToMap());
                    tblAdmin.getTable().updateChildren(dataMap);

                    controller.getCurrentUser().sendEmailVerification();

                    Intent intent = new Intent(SignUpActivity.this, ActivationActivity.class);
                    intent.putExtra("adminEmail", strAdminEmailReg);
                    intent.putExtra("adminPass", strAdminPassReg);
                    startActivity(intent);

                    finish();
                    progressDialog.dismiss();

                }
            }
        });

    }

}
