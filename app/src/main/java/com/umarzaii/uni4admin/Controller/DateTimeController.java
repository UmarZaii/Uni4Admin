package com.umarzaii.uni4admin.Controller;

import android.app.Activity;
import android.support.annotation.NonNull;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;

import java.util.Date;

import static org.joda.time.DateTimeConstants.DECEMBER;

public class DateTimeController {

    private Date date;
    private DateTime dateTime;

    private final Integer JANUARY_DAYS = 31;

    public DateTimeController(Activity activity) {
        JodaTimeAndroid.init(activity);

        date = new Date();
        dateTime = new DateTime(date);
    }

    @NonNull
    private Integer getTimeTableDay(Integer day1to7) {
        return (getDayMonth() - getDayWeek()) + day1to7;
    }

    public Integer getDayWeek() {
        return dateTime.getDayOfWeek();
    }

    public Integer getDayMonth() {
        return dateTime.getDayOfMonth();
    }

    public Integer getYear() {
        return dateTime.getYear();
    }

    public Integer getMonthOfYear() {
        return dateTime.getMonthOfYear();
    }

    public String getCurrentDate() {
        return dateTime.toString().substring(0,10);
    }

    public String getTimeTableDate(Integer dayOfWeek) {

        String targetDay = "";
        String targetMonth = "";
        String targetYear = "";

        if (checkMinDay(dayOfWeek) && checkMaxDay(dayOfWeek)) {
            targetYear = String.format("%04d", getYear());
            targetMonth = String.format("%02d", getMonthOfYear());
            targetDay = String.format("%02d", getTimeTableDay(dayOfWeek));

        } else if (!checkMinDay(dayOfWeek) && checkMinMonth() && checkMaxDay(dayOfWeek) && checkMaxMonth()) {
            targetYear = String.format("%04d", getYear());
            targetMonth = String.format("%02d", getMonthOfYear() - 1);
            targetDay = String.format("%02d", getTimeTableDay(dayOfWeek) + totalDayMonthBefore());

        } else if (!checkMinDay(dayOfWeek) && !checkMinMonth() && checkMaxDay(dayOfWeek) && checkMaxMonth()) {
            targetYear = String.format("%04d", getYear() - 1);
            targetMonth = String.format("%02d", DECEMBER);
            targetDay = String.format("%02d", getTimeTableDay(dayOfWeek) + totalDayMonthBefore());

        } else if (checkMinDay(dayOfWeek) && checkMinMonth() && !checkMaxDay(dayOfWeek) && checkMaxMonth()){
            targetYear = String.format("%04d", getYear());
            targetMonth = String.format("%02d", getMonthOfYear() + 1);
            targetDay = String.format("%02d", getTimeTableDay(dayOfWeek) - totalDayMonth());

        } else if (checkMinDay(dayOfWeek) && checkMinMonth() && !checkMaxDay(dayOfWeek) && !checkMaxMonth()) {
            targetYear = String.format("%04d", getYear() + 1);
            targetMonth = String.format("%02d", getMonthOfYear() - 11);
            targetDay = String.format("%02d", getTimeTableDay(dayOfWeek) - totalDayMonth());
        }
        return targetYear + "-" + targetMonth + "-" + targetDay;
    }

    public Integer totalDayMonth() {
        return dateTime.dayOfMonth().getMaximumValue();
    }

    public Integer totalDayMonthBefore() {
        DateTime tempDate;
        if (checkMinMonth()) {
            Integer monthBefore = getMonthOfYear() - 1;
            tempDate = new DateTime(getYear(),monthBefore,getDayMonth(),00,00,00);
            return tempDate.dayOfMonth().getMaximumValue();
        } else { //JANUARY = 1
            return JANUARY_DAYS;
        }
    }

    public boolean checkMinDay(Integer dayOfWeek) {
        if (getTimeTableDay(dayOfWeek) >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkMaxDay(Integer dayOfWeek) {
        if (getTimeTableDay(dayOfWeek) <= totalDayMonth()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkMinMonth() {
        if (getMonthOfYear() > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkMaxMonth() {
        if (getMonthOfYear() < 12) {
            return true;
        } else {
            return false;
        }
    }

}
