package com.system.dao;

import com.system.modle.ClassShow;
import com.system.modle.CourseAndClass;
import com.system.util.JDBCTools;
import jdk.nashorn.internal.scripts.JD;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseShowDao {
    public List<CourseAndClass> findAll(String name) {
        List<CourseAndClass> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CourseAndClass courseAndClass = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "select * from course where teacher=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
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

    public List<CourseAndClass> findByGrade(String Grade, String name) {
        List<CourseAndClass> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CourseAndClass courseAndClass = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "select * from course where teacher=? and grade=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
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

    public List<CourseAndClass> findByClassName(String searchclassname, String name) {
        List<CourseAndClass> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CourseAndClass courseAndClass = null;

        try {
            connection = JDBCTools.getConnection();
            String likeSearchClassName = "%" + searchclassname + "%";
            String sql = "select * from course where teacher=? and classname like ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
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

    public List<ClassShow> findByClassId(String classid) {
        List<ClassShow> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ClassShow classShow = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT grade , course.coursename, classdetails.classid, classdetails.classname,weekcountid, weekcount, studentnumber from classdetails, course\n" +
                    "where course.classid=? and classdetails.classid=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, classid);
            preparedStatement.setString(2, classid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String grade = resultSet.getString(1);
                String coursename = resultSet.getString(2);
                String classname = resultSet.getString(4);
                String weekcountid = resultSet.getString(5);
                String weekcount = resultSet.getString(6);
                int studentnumber = resultSet.getInt(7);
                int attdencednumber = countWeekCountDeatils(weekcountid);
                classShow = new ClassShow(grade, coursename, classid, classname, weekcountid, weekcount, studentnumber, attdencednumber);
                list.add(classShow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public int countWeekCountDeatils(String weekcountid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT count(*) FROM weekcount WHERE weekcountid = ? and signinsituation ='ÒÑÇ©µ½'";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, weekcountid);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return count;
    }
    public List<ClassShow> findByWeekCount(String classid, String weekcount) {
        List<ClassShow> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String weekcountlike = "%" + weekcount + "%";
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT grade , course.coursename, classdetails.classid, classdetails.classname,weekcountid, weekcount, studentnumber from classdetails, course where course.classid=? and classdetails.classid=? and classdetails.weekcount like ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, classid);
            preparedStatement.setString(2, classid);
            preparedStatement.setString(3, weekcountlike);
            resultSet = preparedStatement.executeQuery();
            ClassShow classShow = null;
            while(resultSet.next()) {
                String grade = resultSet.getString(1);
                String coursename = resultSet.getString(2);
                String classname = resultSet.getString(4);
                String weekcountid = resultSet.getString(5);
                weekcount = resultSet.getString(6);
                int studentnumber = resultSet.getInt(7);
                int attdencednumber = 10;
                classShow = new ClassShow(grade, coursename, classid, classname, weekcountid, weekcount, studentnumber, attdencednumber);
                list.add(classShow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }
}