package com.system.modle;

public class CollegeClass {
    private String collegeid;
    private String college;
    private String grade;
    private String collegeclassid;
    private String collegeclassname;
    private int studentnumber;

    public CollegeClass(String collegeid, String college, String grade, String collegeclassid, String collegeclassname, int studentnumber) {
        this.collegeid = collegeid;
        this.college = college;
        this.grade = grade;
        this.collegeclassid = collegeclassid;
        this.collegeclassname = collegeclassname;
        this.studentnumber = studentnumber;
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

    public int getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(int studentnumber) {
        this.studentnumber = studentnumber;
    }

    @Override
    public String toString() {
        return "CollegeClass{" +
                "collegeid='" + collegeid + '\'' +
                ", college='" + college + '\'' +
                ", grade='" + grade + '\'' +
                ", collegeclassid='" + collegeclassid + '\'' +
                ", collegeclassname='" + collegeclassname + '\'' +
                ", studentnumber=" + studentnumber +
                '}';
    }
}