package com.system.modle;

public class ExportWeekCountDetails {
    private String studentid;
    private String studentname;
    private String classname;
    private String weekcount;
    private String signinsituation;
    private String signoutsituation;

    public ExportWeekCountDetails(String studentid, String studentname, String classname, String weekcount, String signinsituation, String signoutsituation) {
        this.studentid = studentid;
        this.studentname = studentname;
        this.classname = classname;
        this.weekcount = weekcount;
        this.signinsituation = signinsituation;
        this.signoutsituation = signoutsituation;
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

    @Override
    public String toString() {
        return "ExportWeekCountDetails{" +
                "studentid='" + studentid + '\'' +
                ", studentname='" + studentname + '\'' +
                ", classname='" + classname + '\'' +
                ", weekcount='" + weekcount + '\'' +
                ", signinsituation='" + signinsituation + '\'' +
                ", signoutsituation='" + signoutsituation + '\'' +
                '}';
    }
}