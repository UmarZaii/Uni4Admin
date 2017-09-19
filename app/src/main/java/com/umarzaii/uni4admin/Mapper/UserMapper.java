package com.umarzaii.uni4admin.Mapper;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.umarzaii.uni4admin.Database.DBConstants;
import com.umarzaii.uni4admin.Model.UserModel;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class UserMapper {

    UserModel model;

    public UserMapper(UserModel model) {
        this.model = model;
    }

    @Exclude
    public Map<String, Object> detailsToMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(DBConstants.userEmail, model.getUserEmail());
        return result;
    }

    @Exclude
    public Map<String, Object> credentialsToMap() {
        HashMap<String, Object> result = new HashMap<>();
        if (model.getUserRole() == DBConstants.student) {      //Students Only
            result.put(DBConstants.studentID, model.getStudentID());
        } else {                                                        //Employees Only
            result.put(DBConstants.lecturerID, model.getLecturerID());
        }
        result.put(DBConstants.userRole, model.getUserRole());
        return result;
    }
}
