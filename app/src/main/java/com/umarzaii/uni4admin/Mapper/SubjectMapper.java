package com.umarzaii.uni4admin.Mapper;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.umarzaii.uni4admin.Database.DBConstants;
import com.umarzaii.uni4admin.Model.SubjectModel;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class SubjectMapper {

    SubjectModel model;

    public SubjectMapper(SubjectModel model) {
        this.model = model;
    }

    @Exclude
    public Map<String, Object> detailsToMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(DBConstants.subjectName, model.getSubjectName());
        result.put(DBConstants.facultyID, model.getFacultyID());
        result.put(DBConstants.deptID, model.getDeptID());
        return result;
    }

    @Exclude
    public Map<String, Object> saveSubjectID() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(model.getSubjectID(), true);
        return result;
    }

}
