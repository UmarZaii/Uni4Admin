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
import com.umarzaii.uni4admin.Controller.DropdownController;
import com.umarzaii.uni4admin.Controller.FirebaseController;
import com.umarzaii.uni4admin.Controller.FragmentController;
import com.umarzaii.uni4admin.Database.TblCourse;
import com.umarzaii.uni4admin.Database.TblDepartment;
import com.umarzaii.uni4admin.Database.TblSubject;
import com.umarzaii.uni4admin.Mapper.CourseMapper;
import com.umarzaii.uni4admin.Mapper.SubjectMapper;
import com.umarzaii.uni4admin.Model.CourseModel;
import com.umarzaii.uni4admin.Model.SubjectModel;
import com.umarzaii.uni4admin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddSubjectFragment extends Fragment {

    private FragmentController fragmentController;
    private DropdownController dpController;

    private TblSubject tblSubject;
    private TblDepartment tblDepartment;

    private Spinner spnDeptID;
    private EditText edtSubjectID;
    private EditText edtSubjectName;
    private Button btnAddSubject;

    private String strSubjectID;
    private String strSubjectName;
    private String strFacultyID;
    private String strDeptIDSelection;

    private Boolean boolDeptID = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragm_addsubject,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Add Subject");
        View v = getView();

        fragmentController = new FragmentController(getActivity().getSupportFragmentManager());
        dpController = new DropdownController(getActivity());

        tblSubject = new TblSubject();
        tblDepartment = new TblDepartment();

        spnDeptID = (Spinner)v.findViewById(R.id.spnDeptID);
        edtSubjectID = (EditText)v.findViewById(R.id.edtSubjectID);
        edtSubjectName = (EditText)v.findViewById(R.id.edtSubjectName);
        btnAddSubject = (Button) v.findViewById(R.id.btnAddSubject);

        getDepartmentList();

        btnAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strSubjectID = edtSubjectID.getText().toString().trim();
                strSubjectName = edtSubjectName.getText().toString().trim();

                if (inputCheck()) {
                    addSubject();
                }
            }
        });

    }

    private void getDepartmentList() {
        tblDepartment.getTable().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (boolDeptID) {
                    ArrayAdapter adapter = dpController.getAdapter(dataSnapshot);
                    spnDeptID.setAdapter(adapter);
                    boolDeptID = false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        spnDeptID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strDeptIDSelection = parent.getItemAtPosition(position).toString();
                getFacultyID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getFacultyID() {
        tblDepartment.getFacultyID(strDeptIDSelection).addValueEventListener(new ValueEventListener() {
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
        if (TextUtils.isEmpty(strDeptIDSelection)) {
            Toast.makeText(getActivity(), "Please select department", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(strSubjectID)) {
            Toast.makeText(getActivity(), "Please input subject id", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(strSubjectName)) {
            Toast.makeText(getActivity(), "Please input subject name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void addSubject() {
        SubjectModel model = new SubjectModel();
        model.setSubjectName(strSubjectName);
        model.setFacultyID(strFacultyID);
        model.setDeptID(strDeptIDSelection);
        SubjectMapper mapper = new SubjectMapper(model);
        tblSubject.getTable(strSubjectID).updateChildren(mapper.detailsToMap());

        fragmentController.popBackStack("AddSubject");
    }
}
