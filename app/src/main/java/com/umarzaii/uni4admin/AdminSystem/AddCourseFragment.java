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
import com.umarzaii.uni4admin.Database.TblCourse;
import com.umarzaii.uni4admin.Database.TblDepartment;
import com.umarzaii.uni4admin.Mapper.CourseMapper;
import com.umarzaii.uni4admin.Model.CourseModel;
import com.umarzaii.uni4admin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddCourseFragment extends Fragment {

    private FragmentController fragmentController;
    private FirebaseController firebaseController;

    private TblCourse tblCourse;
    private TblDepartment tblDepartment;

    private Spinner spnDeptID;
    private EditText edtCourseIDReg;
    private EditText edtCourseNameReg;
    private Button btnAddDeptAdmin;

    private String strCourseID;
    private String strCourseName;
    private String strFacultyID;
    private String strDeptIDSelection;

    private Boolean boolDeptID = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragm_addcourse,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Add Course");
        View v = getView();

        fragmentController = new FragmentController(getActivity().getSupportFragmentManager());
        firebaseController = new FirebaseController();

        tblCourse = new TblCourse();
        tblDepartment = new TblDepartment();

        spnDeptID = (Spinner)v.findViewById(R.id.spnDeptID);
        edtCourseIDReg = (EditText)v.findViewById(R.id.edtCourseIDReg);
        edtCourseNameReg = (EditText)v.findViewById(R.id.edtCourseNameReg);
        btnAddDeptAdmin = (Button) v.findViewById(R.id.btnAddDeptAdmin);

        getDepartmentList();

        btnAddDeptAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strCourseID = edtCourseIDReg.getText().toString().trim();
                strCourseName = edtCourseNameReg.getText().toString().trim();

                if (inputCheck()) {
                    addCourse();
                }
            }
        });

    }

    private void getDepartmentList() {
        tblDepartment.getTable().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (boolDeptID) {
                    getSpnDepartmentID(dataSnapshot);
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

    private void getSpnDepartmentID(DataSnapshot dataSnapshot) {
        ArrayList<String> deptArr = new ArrayList<String>();

        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
            String deptID = postSnapshot.getKey();
            deptArr.add(deptID);
        }

        ArrayAdapter<String> adpDeptID = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, deptArr);
        adpDeptID.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDeptID.setAdapter(adpDeptID);
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
        } else if (TextUtils.isEmpty(strCourseID)) {
            Toast.makeText(getActivity(), "Please input course id", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(strCourseName)) {
            Toast.makeText(getActivity(), "Please input course name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void addCourse() {

        CourseModel model = new CourseModel();
        model.setCourseName(strCourseName);
        model.setFacultyID(strFacultyID);
        model.setDeptID(strDeptIDSelection);
        CourseMapper mapper = new CourseMapper(model);

        final Map<String, Object> dataCourse = new HashMap<String, Object>();
        dataCourse.put(strCourseID, mapper.detailsToMap());
        tblCourse.getTable().updateChildren(dataCourse);

        fragmentController.popBackStack("AddCourse");
    }
}
