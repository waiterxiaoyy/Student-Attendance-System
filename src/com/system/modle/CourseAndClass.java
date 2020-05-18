package com.system.modle;

public class CourseAndClass {
    private String teacher;
    private String grade;
    private String courseid;
    private String coursename;
    private String classid;
    private String classname;
    private int studentnumber;

    public CourseAndClass(String teacher, String grade, String courseid, String coursename, String classid, String classname, int studentnumber) {
        this.teacher = teacher;
        this.grade = grade;
        this.courseid = courseid;
        this.coursename = coursename;
        this.classid = classid;
        this.classname = classname;
        this.studentnumber = studentnumber;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
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

    public int getStudentnumber() {
        return studentnumber;
    }

    public void setStudentnumber(int studentnumber) {
        this.studentnumber = studentnumber;
    }

    @Override
    public String toString() {
        return "CourseAndClass{" +
                "teacher='" + teacher + '\'' +
                ", grade='" + grade + '\'' +
                ", courseid='" + courseid + '\'' +
                ", coursename='" + coursename + '\'' +
                ", classname='" + classname + '\'' +
                ", studentnumber=" + studentnumber +
                '}';
    }
}