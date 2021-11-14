package com.example.csc2005_team04.ui.leaves;


public class Leaves {
    private String userid;
    private String StartDate;
    private String EndDate;
    private String LeaveType;

    public Leaves(String userid, String startdate, String enddate, String leavetype) {
        this.userid = userid;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.LeaveType = LeaveType;
    }

    public Leaves() {
    }

    public String getUserid() {
        return userid;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public String getLeaveType() {
        return LeaveType;
    }
}



