package com.umarzaii.uni4admin.AdminSystem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.umarzaii.uni4admin.Controller.FirebaseController;
import com.umarzaii.uni4admin.Controller.FragmentController;
import com.umarzaii.uni4admin.Database.TblFaculty;
import com.umarzaii.uni4admin.Mapper.FacultyMapper;
import com.umarzaii.uni4admin.Model.FacultyModel;
import com.umarzaii.uni4admin.R;

import java.util.HashMap;
import java.util.Map;

public class AddFacultyFragment extends Fragment {

    private FragmentController fragmentController;
    private FirebaseController firebaseController;

    private TblFaculty tblFaculty;

    private EditText edtFacultyIDReg, edtFacultyNameReg;
    private Button btnAddFaculty;

    private String strFacultyID, strFacultyName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragm_addfaculty,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Add Faculty");
        View v = getView();

        fragmentController = new FragmentController(getActivity().getSupportFragmentManager());
        firebaseController = new FirebaseController();

        tblFaculty = new TblFaculty();

        edtFacultyIDReg = (EditText)v.findViewById(R.id.edtFacultyID);
        edtFacultyNameReg = (EditText)v.findViewById(R.id.edtFacultyName);
        btnAddFaculty = (Button) v.findViewById(R.id.btnAddFaculty);

        btnAddFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strFacultyID = edtFacultyIDReg.getText().toString().trim();
                strFacultyName = edtFacultyNameReg.getText().toString().trim();

                if (inputCheck()) {
                    addDepartment();
                }
            }
        });

    }

    private boolean inputCheck() {
        if (TextUtils.isEmpty(strFacultyID)) {
            Toast.makeText(getActivity(), "Please input faculty id", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(strFacultyName)) {
            Toast.makeText(getActivity(), "Please input faculty name", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void addDepartment() {

        FacultyModel model = new FacultyModel();
        model.setFacultyName(strFacultyName);
        FacultyMapper mapper = new FacultyMapper(model);

        final Map<String, Object> dataFaculty = new HashMap<String, Object>();
        dataFaculty.put(strFacultyID, mapper.detailsToMap());
        tblFaculty.getTable().updateChildren(dataFaculty);

        fragmentController.popBackStack("AddFaculty");
    }
}
