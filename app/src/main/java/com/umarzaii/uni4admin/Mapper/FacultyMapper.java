package com.umarzaii.uni4admin.Mapper;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.umarzaii.uni4admin.Database.DBConstants;
import com.umarzaii.uni4admin.Model.FacultyModel;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class FacultyMapper {

    FacultyModel model;

    public FacultyMapper(FacultyModel model) {
        this.model = model;
    }

    @Exclude
    public Map<String, Object> detailsToMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(DBConstants.facultyName, model.getFacultyName());
        return result;
    }

}
