package com.umarzaii.uni4admin.Controller;

import java.util.ArrayList;

public class DropdownController {

    public ArrayList<String> semester() {
        ArrayList<String> day = new ArrayList<String>();
        day.add("Semester 1");
        day.add("Semester 2");
        day.add("Semester 3");
        day.add("Semester 4");
        day.add("Semester 5");
        day.add("Semester 6");
        return day;
    }

    public ArrayList<String> timeDay() {
        ArrayList<String> day = new ArrayList<String>();
        day.add("1Sunday");
        day.add("2Monday");
        day.add("3Tuesday");
        day.add("4Wednesday");
        day.add("5Thursday");
        return day;
    }

    public ArrayList<String> timeHour() {
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
