package com.system.modle;

import java.util.Date;

public class WeekCountAndTime {
    private String classid;
    private String classname;
    private String weekcount;
    private String weekcountid;
    private String studentid;
    private String studentname;
    private String signinsituation;
    private String signoutsituation;
    private String starttime;
    private String endtime;
    private String situation;

    public WeekCountAndTime(String classid, String classname, String weekcount, String weekcountid, String studentid, String studentname, String signinsituation, String signoutsituation, String starttime, String endtime) {
        this.classid = classid;
        this.classname = classname;
        this.weekcount = weekcount;
        this.weekcountid = weekcountid;
        this.studentid = studentid;
        this.studentname = studentname;
        this.signinsituation = signinsituation;
        this.signoutsituation = signoutsituation;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getWeekcount() {
        return weekcount;
    }

    public void setWeekcount(String weekcount) {
        this.weekcount = weekcount;
    }

    public String getWeekcountid() {
        return weekcountid;
    }

    public void setWeekcountid(String weekcountid) {
        this.weekcountid = weekcountid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getSigninsituation() {
        return signinsituation;
    }

    public void setSigninsituation(String signinsituation) {
        this.signinsituation = signinsituation;
    }

    public String getSignoutsituation() {
        return signoutsituation;
    }

    public void setSignoutsituation(String signoutsituation) {
        this.signoutsituation = signoutsituation;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    @Override
    public String toString() {
        return "WeekCountAndTime{" +
                "classid='" + classid + '\'' +
                ", classname='" + classname + '\'' +
                ", weekcount='" + weekcount + '\'' +
                ", weekcountid='" + weekcountid + '\'' +
                ", studentid='" + studentid + '\'' +
                ", studentname='" + studentname + '\'' +
                ", signinsituation='" + signinsituation + '\'' +
                ", signoutsituation='" + signoutsituation + '\'' +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                '}';
    }
}