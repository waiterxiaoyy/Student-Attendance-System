package com.system.modle;

public class CollegeClassStudent {
    private String studentid;
    private String studentname;
    private String collegeid;
    private String college;
    private String collegeclassid;
    private String collegeclassname;
    private int coursenumber;
    private int attendencetimes;
    private int unattendencetimes;

    public CollegeClassStudent(String studentid, String studentname, String collegeid, String college, String collegeclassid, String collegeclassname, int coursenumber, int attendencetimes, int unattendencetimes) {
        this.studentid = studentid;
        this.studentname = studentname;
        this.collegeid = collegeid;
        this.college = college;
        this.collegeclassid = collegeclassid;
        this.collegeclassname = collegeclassname;
        this.coursenumber = coursenumber;
        this.attendencetimes = attendencetimes;
        this.unattendencetimes = unattendencetimes;
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

    public String getCollegeid() {
        return collegeid;
    }

    public void setCollegeid(String collegeid) {
        this.collegeid = collegeid;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
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

    public int getCoursenumber() {
        return coursenumber;
    }

    public void setCoursenumber(int coursenumber) {
        this.coursenumber = coursenumber;
    }

    public int getAttendencetimes() {
        return attendencetimes;
    }

    public void setAttendencetimes(int attendencetimes) {
        this.attendencetimes = attendencetimes;
    }

    public int getUnattendencetimes() {
        return unattendencetimes;
    }

    public void setUnattendencetimes(int unattendencetimes) {
        this.unattendencetimes = unattendencetimes;
    }

    @Override
    public String toString() {
        return "CollegeClassStudent{" +
                "studentid='" + studentid + '\'' +
                ", studentname='" + studentname + '\'' +
                ", collegeid='" + collegeid + '\'' +
                ", college='" + college + '\'' +
                ", collegeclassid='" + collegeclassid + '\'' +
                ", collegeclassname='" + collegeclassname + '\'' +
                ", coursenumber=" + coursenumber +
                ", attendencetimes=" + attendencetimes +
                ", unattendencetimse=" + unattendencetimes +
                '}';
    }
}