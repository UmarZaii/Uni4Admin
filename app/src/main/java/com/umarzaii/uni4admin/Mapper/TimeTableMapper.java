package com.umarzaii.uni4admin.Mapper;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.umarzaii.uni4admin.Database.DBConstants;
import com.umarzaii.uni4admin.Controller.DropdownController;
import com.umarzaii.uni4admin.Model.TimeTableModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class TimeTableMapper {

    TimeTableModel model;
    DropdownController controller;

    public TimeTableMapper(TimeTableModel model, DropdownController controller) {
        this.model = model;
        this.controller = controller;
    }

    @Exclude
    public Map<String, Object> classLocationToMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(DBConstants.userClassID, model.getUserClassID());
        result.put(DBConstants.subjectID, model.getSubjectID());
        result.put(DBConstants.lecturerID, model.getLecturerID());
        return result;
    }

    @Exclude
    public Map<String, Object> userClassToMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(DBConstants.classLocationID, model.getClassLocationID());
        result.put(DBConstants.subjectID, model.getSubjectID());
        result.put(DBConstants.lecturerID, model.getLecturerID());
        return result;
    }

    @Exclude
    public Map<String, Object> lecturerToMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(DBConstants.userClassID, model.getUserClassID());
        result.put(DBConstants.classLocationID, model.getClassLocationID());
        result.put(DBConstants.subjectID, model.getSubjectID());
        return result;
    }

    @Exclude
    private Map<String, Object> classLocationInit() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(DBConstants.userClassID, "");
        result.put(DBConstants.subjectID, "");
        result.put(DBConstants.lecturerID, "");
        return result;
    }

    @Exclude
    private Map<String, Object> userClassInit() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(DBConstants.classLocationID, "");
        result.put(DBConstants.subjectID, "");
        result.put(DBConstants.lecturerID, "");
        return result;
    }

    @Exclude
    private Map<String, Object> lecturerInit() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(DBConstants.userClassID, "");
        result.put(DBConstants.classLocationID, "");
        result.put(DBConstants.subjectID, "");
        return result;
    }

    @Exclude
    private Map<String, Object> timeFrameInit(String initType) {
        ArrayList<String> time = controller.timeHour();
        HashMap<String, Object> timeFrame = new HashMap<>();
        for (String timeID: time) {
            Map<String, Object> details = null;
            if (initType.equals(DBConstants.tblUserClass)) {
                details = userClassInit();
            } else if (initType.equals(DBConstants.tblClassLocation)) {
                details = classLocationInit();
            } else if (initType.equals(DBConstants.tblLecturer)) {
                details = lecturerInit();
            }
            timeFrame.put(timeID, details);
        }
        return timeFrame;
    }

    @Exclude
    public Map<String, Object> timeTableInit(String initType) {
        ArrayList<String> day = controller.timeDay();
        HashMap<String, Object> result = new HashMap<>();
        for (String dayID: day) {
            Map<String, Object> details = timeFrameInit(initType);
            result.put(dayID, details);
        }
        return result;
    }
}
