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
import com.umarzaii.uni4admin.Database.TblClassLocation;
import com.umarzaii.uni4admin.Database.TblCourse;
import com.umarzaii.uni4admin.Database.TblFaculty;
import com.umarzaii.uni4admin.Mapper.ClassLocationMapper;
import com.umarzaii.uni4admin.Mapper.CourseMapper;
import com.umarzaii.uni4admin.Model.ClassLocationModel;
import com.umarzaii.uni4admin.Model.CourseModel;
import com.umarzaii.uni4admin.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddClassLocationFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private FragmentController controller;

    private TblClassLocation tblClassLocation;
    private TblFaculty tblFaculty;

    private Spinner spnFacultyID;
    private EditText edtClassLocationID;
    private EditText edtClassLocationName;
    private Button btnAddClassLocation;

    private String strClassLocationID;
    private String strClassLocationName;
    private String strFacultyIDSelection;

    private Boolean boolFacultyID = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragm_addclasslocation,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Add Class Location");
        View v = getView();

        controller = new FragmentController(getActivity().getSupportFragmentManager());

        tblClassLocation = new TblClassLocation();
        tblFaculty = new TblFaculty();

        spnFacultyID = (Spinner)v.findViewById(R.id.spnFacultyID);
        edtClassLocationID = (EditText)v.findViewById(R.id.edtClassLocationID);
        edtClassLocationName = (EditText)v.findViewById(R.id.edtClassLocationName);
        btnAddClassLocation = (Button) v.findViewById(R.id.btnAddClassLocation);

        getFacultyList();

        btnAddClassLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strClassLocationID = edtClassLocationID.getText().toString().trim();
                strClassLocationName = edtClassLocationName.getText().toString().trim();

                if (inputCheck()) {
                    addClassLocation();
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
        spnFacultyID.setOnItemSelectedListener(this);
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
            Toast.makeText(getActivity(), "Please select a faculty", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(strClassLocationID)) {
            Toast.makeText(getActivity(), "Please input class location id", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(strClassLocationName)) {
            Toast.makeText(getActivity(), "Please input class location name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void addClassLocation() {

        ClassLocationModel model = new ClassLocationModel();
        model.setFacultyID(strFacultyIDSelection);
        model.setClassLocationName(strClassLocationName);
        ClassLocationMapper mapper = new ClassLocationMapper(model);

        final Map<String, Object> dataClassLocation = new HashMap<String, Object>();
        dataClassLocation.put(strClassLocationID, mapper.detailsToMap());
        tblClassLocation.getTable().updateChildren(dataClassLocation);

        controller.popBackStack("AddClassLocation");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()){
            case R.id.spnFacultyID:
                strFacultyIDSelection = parent.getItemAtPosition(position).toString();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
