package com.umarzaii.uni4admin.Mapper;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.umarzaii.uni4admin.Database.DBConstants;
import com.umarzaii.uni4admin.Model.UserClassModel;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class UserClassMapper {

    UserClassModel model;

    public UserClassMapper(UserClassModel model) {
        this.model = model;
    }

    @Exclude
    public Map<String, Object> detailsToMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(DBConstants.userClassID, model.getUserClassID());
        result.put(DBConstants.facultyID, model.getFacultyID());
        result.put(DBConstants.deptID, model.getDeptID());
        result.put(DBConstants.courseID, model.getCourseID());
        result.put(DBConstants.semesterID, model.getSemesterID());
        return result;
    }

}
