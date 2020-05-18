package com.system.modle;

public class CollegeClassLearn {
    private String grade;
    private String collegeclassid;
    private String collegeclassname;
    private int highwarning;
    private int middlewarning;
    private int lowwarning;

    public CollegeClassLearn(String grade, String collegeclassid, String collegeclassname, int highwarning, int middlewarning, int lowwarning) {
        this.grade = grade;
        this.collegeclassid = collegeclassid;
        this.collegeclassname = collegeclassname;
        this.highwarning = highwarning;
        this.middlewarning = middlewarning;
        this.lowwarning = lowwarning;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCollegeclassid() {
        return collegeclassid;
    }

    public void setCollegeclassid(String collegeclassid) {
        this.collegeclassid = collegeclassid;
    }

    public String getCollegeclassname() {
        return collegeclassname;
    }

    public void setCollegeclassname(String collegeclassname) {
        this.collegeclassname = collegeclassname;
    }

    public int getHighwarning() {
        return highwarning;
    }

    public void setHighwarning(int highwarning) {
        this.highwarning = highwarning;
    }

    public int getMiddlewarning() {
        return middlewarning;
    }

    public void setMiddlewarning(int middlewarning) {
        this.middlewarning = middlewarning;
    }

    public int getLowwarning() {
        return lowwarning;
    }

    public void setLowwarning(int lowwarning) {
        this.lowwarning = lowwarning;
    }
}