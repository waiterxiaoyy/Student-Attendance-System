package com.system.dao;

import com.system.modle.StudentInfo;
import com.system.util.JDBCTools;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentGPADao {

    public List<StudentInfo> findStudentGPAInCollegeGrade(String college, String grade, String searchclassname, String searchstudentid, String searchstudentname) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<StudentInfo> list = new ArrayList<>();

        String searchclassnamelike = "%" +searchclassname + "%";
        String searchstudetnidlike = "%" + searchstudentid + "%";
        String searchstudentnamelike = "%" + searchstudentname + "%";
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * FROM studentgpa where college=? AND grade = ? and collegeclassname LIKE ? AND studentid LIKE ? AND studentname LIKE ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, college);
            preparedStatement.setString(2, grade);
            preparedStatement.setString(3, searchclassnamelike);
            preparedStatement.setString(4, searchstudetnidlike);
            preparedStatement.setString(5, searchstudentnamelike);
            resultSet = preparedStatement.executeQuery();
            StudentInfo studentInfo = null;
            while(resultSet.next()) {
                String studentid = resultSet.getString(2);
                String studentname = resultSet.getString(3);
                String collegeclassname = resultSet.getString(7);
                BigDecimal gpa = resultSet.getBigDecimal(9);
                studentInfo = new StudentInfo(studentid, studentname, collegeclassname, gpa);
                list.add(studentInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return  list;
    }
    public List<StudentInfo> findHighWarningByCollegeClassid(String collegeclassid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<StudentInfo> list = new ArrayList<>();

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * FROM studentgpa where collegeclassid = ? AND gpa < 2.0";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, collegeclassid);
            resultSet = preparedStatement.executeQuery();
            StudentInfo studentInfo = null;
            while(resultSet.next()) {
                String studentid = resultSet.getString(2);
                String studentname = resultSet.getString(3);
                String collegeclassname = resultSet.getString(7);
                BigDecimal gpa = resultSet.getBigDecimal(9);
                studentInfo = new StudentInfo(studentid, studentname, collegeclassname, gpa);
                list.add(studentInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return  list;
    }
    public List<StudentInfo> findMiddleWarningByCollegeClassid(String collegeclassid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<StudentInfo> list = new ArrayList<>();

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * FROM studentgpa where collegeclassid = ? AND gpa < 2.5 and gpa >= 2.0";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, collegeclassid);
            resultSet = preparedStatement.executeQuery();
            StudentInfo studentInfo = null;
            while(resultSet.next()) {
                String studentid = resultSet.getString(2);
                String studentname = resultSet.getString(3);
                String collegeclassname = resultSet.getString(7);
                BigDecimal gpa = resultSet.getBigDecimal(9);
                studentInfo = new StudentInfo(studentid, studentname, collegeclassname, gpa);
                list.add(studentInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return  list;
    }

    public List<StudentInfo> findLowWarningByCollegeClassid(String collegeclassid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<StudentInfo> list = new ArrayList<>();

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * FROM studentgpa where collegeclassid = ? AND gpa >= 2.5";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, collegeclassid);
            resultSet = preparedStatement.executeQuery();
            StudentInfo studentInfo = null;
            while(resultSet.next()) {
                String studentid = resultSet.getString(2);
                String studentname = resultSet.getString(3);
                String collegeclassname = resultSet.getString(7);
                BigDecimal gpa = resultSet.getBigDecimal(9);
                studentInfo = new StudentInfo(studentid, studentname, collegeclassname, gpa);
                list.add(studentInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return  list;
    }
}