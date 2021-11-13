package com.example.csc2005_team04.ui.leaves;


public class Leaves {
    private String userid;
    private String startdate;
    private String enddate;
    private String leavetype;

    public Leaves(String userid, String startdate, String enddate, String leavetype)
    {
        this.userid = userid;
        this.startdate = startdate;
        this.enddate = enddate;
        this.leavetype = leavetype;
    }
    public Leaves(){}

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getLeavetype() {
        return leavetype;
    }

    public void setLeavetype(String leavetype) {
        this.leavetype = leavetype;
    }
}

