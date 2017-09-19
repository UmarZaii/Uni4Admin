package com.umarzaii.uni4admin.AdminSystem;

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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.umarzaii.uni4admin.Controller.FirebaseController;
import com.umarzaii.uni4admin.Controller.FragmentController;
import com.umarzaii.uni4admin.Database.TblDepartment;
import com.umarzaii.uni4admin.Database.TblFaculty;
import com.umarzaii.uni4admin.Mapper.DepartmentMapper;
import com.umarzaii.uni4admin.Model.DepartmentModel;
import com.umarzaii.uni4admin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddDeptFragment extends Fragment {

    private FragmentController fragmentController;

    private TblFaculty tblFaculty;
    private TblDepartment tblDepartment;

    private Spinner spnFacultyID;
    private EditText edtDeptID, edtDeptName;
    private Button btnAddDept;

    private String strDeptID;
    private String strDeptName;
    private String strFacultyIDSelection;

    private Boolean boolFacultyID = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragm_adddept,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Add Department");
        View v = getView();

        fragmentController = new FragmentController(getActivity().getSupportFragmentManager());

        tblFaculty = new TblFaculty();
        tblDepartment = new TblDepartment();

        spnFacultyID = (Spinner)v.findViewById(R.id.spnFacultyID);
        edtDeptID = (EditText)v.findViewById(R.id.edtDeptID);
        edtDeptName = (EditText)v.findViewById(R.id.edtDeptName);
        btnAddDept = (Button) v.findViewById(R.id.btnAddDept);

        getFacultyList();

        btnAddDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strDeptID = edtDeptID.getText().toString().trim();
                strDeptName = edtDeptName.getText().toString().trim();

                if (inputCheck()) {
                    addDepartment();
                }
            }
        });

    }

    private void getFacultyList() {
        tblFaculty.getTable().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (boolFacultyID) {
                    getSpnFacultyID(dataSnapshot);
                    boolFacultyID = false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        spnFacultyID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strFacultyIDSelection = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getSpnFacultyID(DataSnapshot dataSnapshot) {
        ArrayList<String> facultyArr = new ArrayList<String>();

        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
            String facultyID = postSnapshot.getKey();
            facultyArr.add(facultyID);
        }

        ArrayAdapter<String> adpFacultyID = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, facultyArr);
        adpFacultyID.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFacultyID.setAdapter(adpFacultyID);
    }

    private boolean inputCheck() {
        if (TextUtils.isEmpty(strFacultyIDSelection)) {
            Toast.makeText(getActivity(), "Please select faculty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(strDeptID)) {
            Toast.makeText(getActivity(), "Please input department id", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(strDeptName)) {
            Toast.makeText(getActivity(), "Please input department name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void addDepartment() {

        DepartmentModel model = new DepartmentModel();
        model.setFacultyID(strFacultyIDSelection);
        model.setDeptName(strDeptName);
        model.setDeptAdmin("");
        model.setDeptHead("");
        DepartmentMapper mapper = new DepartmentMapper(model);

        final Map<String, Object> dataDept = new HashMap<String, Object>();
        dataDept.put(strDeptID, mapper.detailsToMap());
        tblDepartment.getTable().updateChildren(dataDept);

        fragmentController.popBackStack("AddDepartment");
    }
}
