package com.system.modle;

import java.math.BigDecimal;

public class StudentInfo {
    private String studentid;
    private String studentname;
    private String college;
    private String grade;
    private String collegeclassname;
    private int coursenumber;
    private int attendencecount;
    private int signincount;
    private int signoutcount;
    private int countLibrary;
    private double signinRatio;
    private double signoutRatio;
    private BigDecimal gpa;
    private double gpaRatio;

    public StudentInfo(String studentid, String studentname, String college, String grade, String collegeclassname, int coursenumber, int attendencecount, int signincount, int signoutcount, int countLibrary, double signinRatio, double signoutRatio, BigDecimal gpa, double gpaRatio) {
        this.studentid = studentid;
        this.studentname = studentname;
        this.college = college;
        this.grade = grade;
        this.collegeclassname = collegeclassname;
        this.coursenumber = coursenumber;
        this.attendencecount = attendencecount;
        this.signincount = signincount;
        this.signoutcount = signoutcount;
        this.countLibrary = countLibrary;
        this.signinRatio = signinRatio;
        this.signoutRatio = signoutRatio;
        this.gpa = gpa;
        this.gpaRatio = gpaRatio;
    }

    public StudentInfo(String studentid, String studentname, String college, String grade, String collegeclassname, int coursenumber) {
        this.studentid = studentid;
        this.studentname = studentname;
        this.college = college;
        this.grade = grade;
        this.collegeclassname = collegeclassname;
        this.coursenumber = coursenumber;
    }

    public StudentInfo(String studentid, String studentname, String collegeclassname, BigDecimal gpa) {
        this.studentid = studentid;
        this.studentname = studentname;
        this.collegeclassname = collegeclassname;
        this.gpa = gpa;
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

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCollegeclassname() {
        return collegeclassname;
    }

    public void setCollegeclassname(String collegeclassname) {
        this.collegeclassname = collegeclassname;
    }

    public int getCoursenumber() {
        return coursenumber;
    }

    public void setCoursenumber(int coursenumber) {
        this.coursenumber = coursenumber;
    }

    public int getAttendencecount() {
        return attendencecount;
    }

    public void setAttendencecount(int attendencecount) {
        this.attendencecount = attendencecount;
    }

    public int getSignincount() {
        return signincount;
    }

    public void setSignincount(int signincount) {
        this.signincount = signincount;
    }

    public int getSignoutcount() {
        return signoutcount;
    }

    public void setSignoutcount(int signoutcount) {
        this.signoutcount = signoutcount;
    }

    public int getCountLibrary() {
        return countLibrary;
    }

    public void setCountLibrary(int countLibrary) {
        this.countLibrary = countLibrary;
    }

    public double getSigninRatio() {
        return signinRatio;
    }

    public void setSigninRatio(double signinRatio) {
        this.signinRatio = signinRatio;
    }

    public double getSignoutRatio() {
        return signoutRatio;
    }

    public void setSignoutRatio(double signoutRatio) {
        this.signoutRatio = signoutRatio;
    }

    public BigDecimal getGpa() {
        return gpa;
    }

    public void setGpa(BigDecimal gpa) {
        this.gpa = gpa;
    }

    public double getGpaRatio() {
        return gpaRatio;
    }

    public void setGpaRatio(double gpaRatio) {
        this.gpaRatio = gpaRatio;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "studentid='" + studentid + '\'' +
                ", studentname='" + studentname + '\'' +
                ", college='" + college + '\'' +
                ", grade='" + grade + '\'' +
                ", collegeclassname='" + collegeclassname + '\'' +
                ", coursenumber=" + coursenumber +
                ", attendencecount=" + attendencecount +
                ", signincount=" + signincount +
                ", signoutcount=" + signoutcount +
                ", countLibrary=" + countLibrary +
                ", signinRatio=" + signinRatio +
                ", signoutRatio=" + signoutRatio +
                ", gpa=" + gpa +
                ", gpaRatio=" + gpaRatio +
                '}';
    }
}