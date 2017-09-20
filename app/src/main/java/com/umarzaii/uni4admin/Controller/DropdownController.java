package com.umarzaii.uni4admin.Controller;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class DropdownController {

    private Activity activity;

    public DropdownController(Activity activity) {
        this.activity = activity;
    }

    public ArrayAdapter getAdapter(DataSnapshot dataSnapshot) {
        ArrayList<String> list = new ArrayList<String>();
        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
            list.add(postSnapshot.getKey());
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    public ArrayAdapter getAdapter(ArrayList list) {
        ArrayAdapter adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    public ArrayList<String> semester() {   //HARDCODE
        ArrayList<String> day = new ArrayList<String>();
        day.add("Semester 1");
        day.add("Semester 2");
        day.add("Semester 3");
        day.add("Semester 4");
        day.add("Semester 5");
        day.add("Semester 6");
        return day;
    }

    public ArrayList<String> timeDay() {   //HARDCODE
        ArrayList<String> day = new ArrayList<String>();
        day.add("1Sunday");
        day.add("2Monday");
        day.add("3Tuesday");
        day.add("4Wednesday");
        day.add("5Thursday");
        return day;
    }

    public ArrayList<String> timeHour() {   //HARDCODE
        ArrayList<String> hour = new ArrayList<String>();
        hour.add("0800-0900");
        hour.add("0900-1000");
        hour.add("1000-1100");
        hour.add("1100-1200");
        hour.add("1200-1300");
        hour.add("1300-1400");
        hour.add("1400-1500");
        hour.add("1500-1600");
        hour.add("1600-1700");
        hour.add("1700-1800");
        return hour;
    }

}
