package com.umarzaii.uni4admin.Mapper;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.umarzaii.uni4admin.Database.DBConstants;
import com.umarzaii.uni4admin.Model.AdminModel;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class AdminMapper {

    AdminModel model;

    public AdminMapper(AdminModel model) {
        this.model = model;
    }

    @Exclude
    public Map<String, Object> detailsToMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(DBConstants.adminEmail, model.getAdminEmail());
        result.put(DBConstants.adminName, model.getAdminName());
        return result;
    }
}
