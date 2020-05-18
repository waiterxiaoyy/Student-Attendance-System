package com.system.dao;

import com.system.modle.StudentInfo;
import com.system.modle.WeekCount;
import com.system.util.JDBCTools;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDetailsDao {
    public List<WeekCount> findStudentWeekCountDetails(String studentid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<WeekCount> list = new ArrayList<>();

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * from weekcount where studentid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentid);
            resultSet = preparedStatement.executeQuery();

            WeekCount weekCount = null;
            while (resultSet.next()) {
                String classid = resultSet.getString(2);
                String classname = resultSet.getString(3);
                String weekcount = resultSet.getString(4);
                String weekcountid = resultSet.getString(5);
                String studentname = resultSet.getString(7);
                String signinsituation = resultSet.getString(8);
                String signoutsituation = resultSet.getString(9);
                weekCount = new WeekCount(classid, classname, weekcount, weekcountid, studentid, studentname, signinsituation, signoutsituation);
                list.add(weekCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;

    }
    public StudentInfo findStudentInfo(String studentid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        StudentInfo studentInfo = null;
        try {
            connection = JDBCTools.getConnection();
            String sql ="SELECT student.studentid, student.studentname,student.college, student.grade,student.collegeclassname,COUNT(studentcourse.studentid)\n" +
                    " FROM student LEFT JOIN studentcourse ON student.studentid = studentcourse.studentid where student.studentid=?\n" +
                    "GROUP BY student.studentid";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentid);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                String studentname = resultSet.getString(2);
                String college = resultSet.getString(3);
                String grade = resultSet.getString(4);
                String collegeclassname = resultSet.getString(5);
                int coursenumber = resultSet.getInt(6);
                studentInfo = new StudentInfo(studentid, studentname, college, grade, collegeclassname, coursenumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return studentInfo;
    }

    public int countAttendenCount(String studentid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = JDBCTools.getConnection();
            String sql = "select count(*) from weekcount where studentid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentid);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return count;
    }

    public  int countSigninCount(String studentid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = JDBCTools.getConnection();
            String sql = "select count(*) from weekcount where studentid = ? and signinsituation = 'ÒÑÇ©µ½'";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return count;
    }

    public  int countSignoutCount(String studentid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = JDBCTools.getConnection();
            String sql = "select count(*) from weekcount where studentid = ? and signinsituation = 'Î´Ç©µ½'";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return count;
    }

    public BigDecimal findStudentGPA(String studentid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        BigDecimal gpa = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "select gpa from studentgpa where studentid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                gpa = resultSet.getBigDecimal(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return gpa;
    }

    public int countLibarary(String studentid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT countlibrary FROM studentlibrarycount where studentid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentid);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return count;
    }

}