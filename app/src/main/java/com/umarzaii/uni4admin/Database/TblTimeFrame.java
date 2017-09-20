package com.umarzaii.uni4admin.Database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TblTimeFrame {

    private DatabaseReference database;
    private DatabaseReference tblTimeFrame;

    public TblTimeFrame() {
        database = FirebaseDatabase.getInstance().getReference();
        tblTimeFrame = database.child(DBConstants.tblTimeFrame);
    }

    //ROOT
    public DatabaseReference getTable() {
        return tblTimeFrame;
    }
    public DatabaseReference getTblClassLocation() {
        return getTable().child(DBConstants.tblClassLocation);
    }
    public DatabaseReference getTblUserClass() {
        return getTable().child(DBConstants.tblUserClass);
    }
    public DatabaseReference getTblLecturer() {
        return getTable().child(DBConstants.tblLecturer);
    }

    //CLASS LOCATION
    public DatabaseReference getTblClassLocation(String classLocationID) {
        return getTblClassLocation().child(classLocationID);
    }
    public DatabaseReference getCLDay(String classLocationID, String dayID) {
        return getTblClassLocation(classLocationID).child(dayID);
    }
    public DatabaseReference getCLTime(String classLocationID, String dayID, String timeID) {
        return getCLDay(classLocationID,dayID).child(timeID);
    }
    public DatabaseReference getCLSubjectID(String classLocationID, String dayID, String timeID) {
        return getCLTime(classLocationID,dayID,timeID).child(DBConstants.subjectID);
    }
    public DatabaseReference getCLUserClassID(String classLocationID, String dayID, String timeID) {
        return getCLTime(classLocationID,dayID,timeID).child(DBConstants.userClassID);
    }
    public DatabaseReference getCLLecturerID(String classLocationID, String dayID, String timeID) {
        return getCLTime(classLocationID,dayID,timeID).child(DBConstants.lecturerID);
    }


    //USER CLASS
    public DatabaseReference getTblUserClass(String userClassID) {
        return getTblUserClass().child(userClassID);
    }
    public DatabaseReference getUCDay(String userClassID, String dayID) {
        return getTblUserClass(userClassID).child(dayID);
    }
    public DatabaseReference getUCTime(String userClassID, String dayID, String timeID) {
        return getUCDay(userClassID,dayID).child(timeID);
    }
    public DatabaseReference getUCSubjectID(String userClassID, String dayID, String timeID) {
        return getUCTime(userClassID,dayID,timeID).child(DBConstants.subjectID);
    }
    public DatabaseReference getUCUserClassID(String userClassID, String dayID, String timeID) {
        return getUCTime(userClassID,dayID,timeID).child(DBConstants.classLocationID);
    }
    public DatabaseReference getUCLecturerID(String userClassID, String dayID, String timeID) {
        return getUCTime(userClassID,dayID,timeID).child(DBConstants.lecturerID);
    }

    //LECTURER
    public DatabaseReference getTblLecturer(String lectID) {
        return getTblLecturer().child(lectID);
    }
    public DatabaseReference getLCDay(String lectID, String dayID) {
        return getTblLecturer(lectID).child(dayID);
    }
    public DatabaseReference getLCTime(String lectID, String dayID, String timeID) {
        return getLCDay(lectID,dayID).child(timeID);
    }
    public DatabaseReference getLCSubjectID(String lectID, String dayID, String timeID) {
        return getLCTime(lectID,dayID,timeID).child(DBConstants.subjectID);
    }
    public DatabaseReference getLCClassLocationID(String lectID, String dayID, String timeID) {
        return getLCTime(lectID,dayID,timeID).child(DBConstants.classLocationID);
    }
    public DatabaseReference getLCUserClassID(String lectID, String dayID, String timeID) {
        return getLCTime(lectID,dayID,timeID).child(DBConstants.userClassID);
    }

}
