package com.system.dao;

import com.system.modle.ClassShow;
import com.system.modle.CourseAndClass;
import com.system.modle.WeekCount;
import com.system.modle.WeekCountAndTime;
import com.system.util.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentClassDao {
    public List<CourseAndClass> findAll(String studentid) {
        List<CourseAndClass> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CourseAndClass courseAndClass = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "select * FROM course, studentcourse\n" +
                    "WHERE studentid = ? and course.classid = studentcourse.classid";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String teacher = resultSet.getString(2);
                String grade = resultSet.getString(3);
                String courseid = resultSet.getString(4);
                String coursename = resultSet.getString(5);
                String classid = resultSet.getString(6);
                String classname = resultSet.getString(7);
                int studentnumber = resultSet.getInt(8);
                courseAndClass = new CourseAndClass(teacher, grade, courseid, coursename, classid,  classname, studentnumber);
                list.add(courseAndClass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public List<CourseAndClass> findByGrade(String Grade, String studentid) {
        List<CourseAndClass> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CourseAndClass courseAndClass = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "select * from course, studentcourse where studentid = ? and grade=?  and course.classid = studentcourse.classid";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,studentid);
            preparedStatement.setString(2, Grade+"¼¶");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String teacher = resultSet.getString(2);
                String grade = resultSet.getString(3);
                String courseid = resultSet.getString(4);
                String coursename = resultSet.getString(5);
                String classid = resultSet.getString(6);
                String classname = resultSet.getString(7);
                int studentnumber = resultSet.getInt(8);
                courseAndClass = new CourseAndClass(teacher, grade, courseid, coursename, classid,classname, studentnumber);
                list.add(courseAndClass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public List<CourseAndClass> findByClassName(String searchclassname, String studentid) {
        List<CourseAndClass> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CourseAndClass courseAndClass = null;

        try {
            connection = JDBCTools.getConnection();
            String likeSearchClassName = "%" + searchclassname + "%";
            String sql = "SELECT * FROM course, studentcourse where course.classid = studentcourse.classid and studentid = ? and studentcourse.classname like ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,studentid);
            preparedStatement.setString(2, likeSearchClassName);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String teacher = resultSet.getString(2);
                String grade = resultSet.getString(3);
                String courseid = resultSet.getString(4);
                String coursename = resultSet.getString(5);
                String classid = resultSet.getString(6);
                String classname = resultSet.getString(7);
                int studentnumber = resultSet.getInt(8);
                courseAndClass = new CourseAndClass(teacher, grade, courseid, coursename, classid, classname, studentnumber);
                list.add(courseAndClass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public List<WeekCountAndTime> findWeekCountByClassid(String classid, String studentid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<WeekCountAndTime> list = new ArrayList<>();

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT weekcount.classid,weekcount.classname,weekcount.weekcount,weekcount.weekcountid,weekcount.studentid,weekcount.studentname,weekcount.signinsituation,weekcount.signoutsituation,classdetails.starttime,classdetails.endtime FROM classdetails, weekcount where weekcount.classid = ? and studentid = ? and classdetails.weekcountid = weekcount.weekcountid";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, classid);
            preparedStatement.setString(2, studentid);
            resultSet = preparedStatement.executeQuery();
            WeekCountAndTime weekCountAndTime = null;
            while (resultSet.next()) {
                String classname = resultSet.getString(2);
                String weekcount = resultSet.getString(3);
                String weekcountid = resultSet.getString(4);
                String studentname = resultSet.getString(6);
                String signinsituation = resultSet.getString(7);
                String signoutsituation = resultSet.getString(8);
                String starttimeStr = resultSet.getString(9);
                String endtimeStr = resultSet.getString(10);
                weekCountAndTime = new WeekCountAndTime(classid, classname, weekcount, weekcountid, studentid, studentname, signinsituation, signoutsituation, starttimeStr, endtimeStr);
                list.add(weekCountAndTime);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }
}