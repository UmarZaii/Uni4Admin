package com.umarzaii.uni4admin.AdminSystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.umarzaii.uni4admin.Controller.FragmentController;
import com.umarzaii.uni4admin.Database.DBConstants;
import com.umarzaii.uni4admin.Database.TblDepartment;
import com.umarzaii.uni4admin.Database.TblLecturer;
import com.umarzaii.uni4admin.Database.TblTimeFrame;
import com.umarzaii.uni4admin.Database.TblUser;
import com.umarzaii.uni4admin.Mapper.LecturerMapper;
import com.umarzaii.uni4admin.Mapper.TimeTableMapper;
import com.umarzaii.uni4admin.Mapper.UserMapper;
import com.umarzaii.uni4admin.Model.LecturerModel;
import com.umarzaii.uni4admin.Model.UserModel;
import com.umarzaii.uni4admin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddDeptAdminFragment extends Fragment {

    private FragmentController controller;

    private TblUser tblUser;
    private TblLecturer tblLecturer;
    private TblDepartment tblDepartment;
    private TblTimeFrame tblTimeFrame;

    private Spinner spnDeptID;
    private EditText edtLecturerID, edtLecturerName;
    private TextView txtUserID;
    private Button btnScanDeptAdmin, btnAddDeptAdmin;

    private String strUserID;
    private String strLecturerName;
    private String strLecturerID;
    private String strFacultyID;
    private String strDepartmentIDSelection;

    private Boolean boolDepartment = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragm_adddeptadmin,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Add DeptAdmin");
        View v = getView();

        controller = new FragmentController(getActivity().getSupportFragmentManager());

        tblUser = new TblUser();
        tblLecturer = new TblLecturer();
        tblDepartment = new TblDepartment();
        tblTimeFrame = new TblTimeFrame();

        spnDeptID = (Spinner)v.findViewById(R.id.spnDeptID);
        edtLecturerID = (EditText)v.findViewById(R.id.edtLecturerID);
        edtLecturerName = (EditText)v.findViewById(R.id.edtUserName);
        txtUserID = (TextView)v.findViewById(R.id.txtUserID);
        btnAddDeptAdmin = (Button)v.findViewById(R.id.btnAddDeptAdmin);
        btnScanDeptAdmin = (Button)v.findViewById(R.id.btnScanDeptAdmin);

        getDepartment();

        btnScanDeptAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                Intent intent = integrator.createScanIntent();
                startActivityForResult(intent, integrator.REQUEST_CODE);
            }
        });

        btnAddDeptAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strLecturerID = edtLecturerID.getText().toString().trim();
                strLecturerName = edtLecturerName.getText().toString().trim();

                if (inputCheck()) {
                    addDeptAdmin();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null){
                Toast.makeText(getActivity(), "You cancelled the scanning", Toast.LENGTH_SHORT).show();
            } else {
                checkUserDetails(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void checkUserDetails(final String userID) {
        tblUser.getTable().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(userID)) {
                    strUserID = userID;
                    txtUserID.setText(strUserID);
                } else {
                    Toast.makeText(getActivity(), "User Does Not Exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getDepartment() {
        tblDepartment.getTable().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (boolDepartment) {
                    getSpnDepartment(dataSnapshot);
                    boolDepartment = false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        spnDeptID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strDepartmentIDSelection = parent.getItemAtPosition(position).toString();
                getFacultyID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getSpnDepartment(DataSnapshot dataSnapshot) {
        ArrayList<String> deptArr = new ArrayList<String>();

        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
            String deptID = postSnapshot.getKey();
            deptArr.add(deptID);
        }

        ArrayAdapter<String> adpDepartment = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, deptArr);
        adpDepartment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDeptID.setAdapter(adpDepartment);
    }

    private void getFacultyID() {
        tblDepartment.getFacultyID(strDepartmentIDSelection).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                strFacultyID = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private boolean inputCheck() {
        if (strUserID == null) {
            Toast.makeText(getActivity(), "Please scan user first", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(strLecturerID)) {
            Toast.makeText(getActivity(), "Please input user lecturerID", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(strLecturerName)) {
            Toast.makeText(getActivity(), "Please input user lecturerName", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(strDepartmentIDSelection)) {
            Toast.makeText(getActivity(), "Please select a department", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void addDeptAdmin() {

        LecturerModel lecturerModel = new LecturerModel();
        lecturerModel.setLecturerName(strLecturerName);
        lecturerModel.setFacultyID(strFacultyID);
        lecturerModel.setDeptID(strDepartmentIDSelection);
        lecturerModel.setDeptAdmin(true);
        lecturerModel.setDeptHead(false);
        LecturerMapper lecturerMapper = new LecturerMapper(lecturerModel);

        final Map<String, Object> dataLecturer = new HashMap<String, Object>();
        dataLecturer.put(strLecturerID, lecturerMapper.detailsToMap());
        tblLecturer.getTable().updateChildren(dataLecturer);

        UserModel userModel = new UserModel();
        userModel.setLecturerID(strLecturerID);
        userModel.setUserRole(DBConstants.lecturer);
        UserMapper userMapper = new UserMapper(userModel);

        final Map<String, Object> dataUser = new HashMap<String, Object>();
        dataUser.put(strUserID, userMapper.credentialsToMap());
        tblUser.getTable().updateChildren(dataUser);

        TimeTableMapper ttMapper = new TimeTableMapper(getActivity());
        final Map<String, Object> dataMapTt = new HashMap<String, Object>();
        dataMapTt.put(strLecturerID, ttMapper.timeTableInit(DBConstants.tblLecturer));
        tblTimeFrame.getTblLecturer().updateChildren(dataMapTt);

        controller.popBackStack("AddDeptAdmin");

    }

}
