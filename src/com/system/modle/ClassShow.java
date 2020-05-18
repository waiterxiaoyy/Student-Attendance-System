package com.system.modle;

public class ClassShow {
    private String grade;
    private String coursename;
    private String classid;
    private String classname;
    private String weekcountid;
    private String weekcount;
    private int studentnumber;
    private int attdencednumber;

    public ClassShow(String grade, String coursename, String classid, String classname, String weekcountid, String weekcount, int studentnumber, int attdencednumber) {
        this.grade = grade;
        this.coursename = coursename;
        this.classid = classid;
        this.classname = classname;
        this.weekcountid = weekcountid;
        this.weekcount = weekcount;
        this.studentnumber = studentnumber;
        this.attdencednumber = attdencednumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
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

    public String getWeekcountid() {
        return weekcountid;
    }

    public void setWeekcountid(String weekcountid) {
        this.weekcountid = weekcountid;
    }

    public String getWeekcount() {
        return weekcount;
    }

    public void setWeekcount(String weekcount) {
        this.weekcount = weekcount;
    }

    public int getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(int studentnumber) {
        this.studentnumber = studentnumber;
    }

    public int getAttdencednumber() {
        return attdencednumber;
    }

    public void setAttdencednumber(int attdencednumber) {
        this.attdencednumber = attdencednumber;
    }

    @Override
    public String toString() {
        return "ClassShow{" +
                "grade='" + grade + '\'' +
                ", coursename='" + coursename + '\'' +
                ", classid='" + classid + '\'' +
                ", classname='" + classname + '\'' +
                ", weekcountid='" + weekcountid + '\'' +
                ", weekcount='" + weekcount + '\'' +
                ", studentnumber=" + studentnumber +
                ", attdencednumber=" + attdencednumber +
                '}';
    }
}