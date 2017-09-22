package com.umarzaii.uni4admin.AdminSystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.umarzaii.uni4admin.Controller.FirebaseController;
import com.umarzaii.uni4admin.Controller.FragmentController;
import com.umarzaii.uni4admin.R;

public class MainFragment extends Fragment {

    private FragmentController fragmentController;
    private FirebaseController firebaseController;

    private Button btnGoToAddFaculty;
    private Button btnGoToAddDept;
    private Button btnGoToAddCourse;
    private Button btnGoToAddSubject;
    private Button btnGoToAddDeptAdmin;
    private Button btnGoToAddClasLocation;
    private Button btnLogOut;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragm_main,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Main Fragment");
        View v = getView();

        fragmentController = new FragmentController(getActivity().getSupportFragmentManager());
        firebaseController = new FirebaseController();

        btnGoToAddClasLocation = (Button)v.findViewById(R.id.btnGoToAddClassLocation);
        btnGoToAddFaculty = (Button)v.findViewById(R.id.btnGoToAddFaculty);
        btnGoToAddDept = (Button)v.findViewById(R.id.btnGoToAddDept);
        btnGoToAddCourse = (Button)v.findViewById(R.id.btnGoToAddCourse);
        btnGoToAddSubject = (Button)v.findViewById(R.id.btnGoToAddSubject);
        btnGoToAddDeptAdmin = (Button)v.findViewById(R.id.btnGoToAddDeptAdmin);
        btnLogOut = (Button)v.findViewById(R.id.btnLogOut);

        btnGoToAddFaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentController.stackFragment(new AddFacultyFragment(), R.id.content_main, "AddFaculty");
            }
        });

        btnGoToAddDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentController.stackFragment(new AddDeptFragment(), R.id.content_main, "AddDepartment");
            }
        });

        btnGoToAddCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentController.stackFragment(new AddCourseFragment(), R.id.content_main, "AddCourse");
            }
        });

        btnGoToAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentController.stackFragment(new AddSubjectFragment(), R.id.content_main, "AddSubject");
            }
        });

        btnGoToAddDeptAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentController.stackFragment(new AddDeptAdminFragment(), R.id.content_main, "AddDeptAdmin");
            }
        });

        btnGoToAddClasLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentController.stackFragment(new AddClassLocationFragment(), R.id.content_main, "AddClassLocation");
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseController.getFirebaseAuth().signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }

}
