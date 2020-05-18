package com.system.modle;

public class CollegeClassLibrary {
    private String studentid;
    private String studentname;
    private String collegeclassid;
    private String collegeclassname;
    private int countlibrary;

    public CollegeClassLibrary(String studentid, String studentname, String collegeclassid, String collegeclassname, int countlibrary) {
        this.studentid = studentid;
        this.studentname = studentname;
        this.collegeclassid = collegeclassid;
        this.collegeclassname = collegeclassname;
        this.countlibrary = countlibrary;
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

    public int getCountlibrary() {
        return countlibrary;
    }

    public void setCountlibrary(int countlibrary) {
        this.countlibrary = countlibrary;
    }

    @Override
    public String toString() {
        return "CollegeClassLibrary{" +
                "studentid='" + studentid + '\'' +
                ", studentname='" + studentname + '\'' +
                ", collegeclassid='" + collegeclassid + '\'' +
                ", collegclassname='" + collegeclassname + '\'' +
                ", countlibrary='" + countlibrary + '\'' +
                '}';
    }
}