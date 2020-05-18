package com.system.dao;

import com.system.modle.CollegeClassLibrary;
import com.system.modle.ExportWeekCountDetails;
import com.system.modle.StudentInfo;
import com.system.util.JDBCTools;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExportDao {
    public List<CollegeClassLibrary> exportAllGradeForLibrary(String college, String grade) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CollegeClassLibrary> list = new ArrayList<>();

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * FROM studentlibrarycount where grade = ? and college = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, grade);
            preparedStatement.setString(2, college);
            resultSet = preparedStatement.executeQuery();
            CollegeClassLibrary collegeClassLibrary = null;
            while(resultSet.next()) {
                String studentid = resultSet.getString(2);
                String studentname = resultSet.getString(3);
                String collegeclassid = resultSet.getString(6);
                String collegeclassname = resultSet.getString(7);
                int countlibrary = resultSet.getInt(9);
                collegeClassLibrary = new CollegeClassLibrary(studentid, studentname, collegeclassid,collegeclassname, countlibrary);
                list.add(collegeClassLibrary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return  list;
    }

    public  List<ExportWeekCountDetails> findCollegeClassWeekCountDetails(String collegeclassid) {
        List<ExportWeekCountDetails> list = new ArrayList<>();
        Connection connection  = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT weekcount.studentid,weekcount.studentname, weekcount.classname, weekcount.weekcount, weekcount.signinsituation,weekcount.signoutsituation FROM weekcount, student where student.studentid = weekcount.studentid and student.collegeclassid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, collegeclassid);
            resultSet = preparedStatement.executeQuery();
            ExportWeekCountDetails exportWeekCountDetails = null;
            while(resultSet.next()) {
                String studentid = resultSet.getString(1);
                String studentname = resultSet.getString(2);
                String classname = resultSet.getString(3);
                String weekcount = resultSet.getString(4);
                String signinsituation = resultSet.getString(5);
                String signoutsituation = resultSet.getString(6);
                exportWeekCountDetails = new ExportWeekCountDetails(studentid, studentname, classname, weekcount, signinsituation, signoutsituation);
                list.add(exportWeekCountDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public  List<ExportWeekCountDetails> findAllGradeWeekCountDetails(String college, String grade) {
        List<ExportWeekCountDetails> list = new ArrayList<>();
        Connection connection  = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT weekcount.studentid,weekcount.studentname, weekcount.classname, weekcount.weekcount, weekcount.signinsituation,weekcount.signoutsituation FROM weekcount, student where student.grade = ? and student.studentid = weekcount.studentid and student.college = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, grade);
            preparedStatement.setString(2, college);
            resultSet = preparedStatement.executeQuery();
            ExportWeekCountDetails exportWeekCountDetails = null;
            while(resultSet.next()) {
                String studentid = resultSet.getString(1);
                String studentname = resultSet.getString(2);
                String classname = resultSet.getString(3);
                String weekcount = resultSet.getString(4);
                String signinsituation = resultSet.getString(5);
                String signoutsituation = resultSet.getString(6);
                exportWeekCountDetails = new ExportWeekCountDetails(studentid, studentname, classname, weekcount, signinsituation, signoutsituation);
                list.add(exportWeekCountDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public  List<ExportWeekCountDetails> findStudentWeekCountDetails(String studentid) {
        List<ExportWeekCountDetails> list = new ArrayList<>();
        Connection connection  = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT studentid,studentname, classname, weekcount, weekcount.signinsituation,weekcount.signoutsituation FROM weekcount where studentid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, studentid);
            resultSet = preparedStatement.executeQuery();
            ExportWeekCountDetails exportWeekCountDetails = null;
            while(resultSet.next()) {
                String studentname = resultSet.getString(2);
                String classname = resultSet.getString(3);
                String weekcount = resultSet.getString(4);
                String signinsituation = resultSet.getString(5);
                String signoutsituation = resultSet.getString(6);
                exportWeekCountDetails = new ExportWeekCountDetails(studentid, studentname, classname, weekcount, signinsituation, signoutsituation);
                list.add(exportWeekCountDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public List<ExportWeekCountDetails> findWeekCountDetails(String weekcountid) {
        List<ExportWeekCountDetails> list = new ArrayList<>();
        Connection connection  = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT studentid,studentname, classname, weekcount, weekcount.signinsituation,weekcount.signoutsituation FROM weekcount where weekcountid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, weekcountid);
            resultSet = preparedStatement.executeQuery();
            ExportWeekCountDetails exportWeekCountDetails = null;
            while(resultSet.next()) {
                String studentid = resultSet.getString(1);
                String studentname = resultSet.getString(2);
                String classname = resultSet.getString(3);
                String weekcount = resultSet.getString(4);
                String signinsituation = resultSet.getString(5);
                String signoutsituation = resultSet.getString(6);
                exportWeekCountDetails = new ExportWeekCountDetails(studentid, studentname, classname, weekcount, signinsituation, signoutsituation);
                list.add(exportWeekCountDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public List<StudentInfo> exportHighWarningStudentGPA(String college, String grade) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<StudentInfo> list = new ArrayList<>();

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * FROM studentgpa where grade = ? and college = ? AND gpa < 2.0";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, grade);
            preparedStatement.setString(2, college);
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

    public List<StudentInfo> exportMiddleWarningStudentGPA(String college, String grade) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<StudentInfo> list = new ArrayList<>();

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * FROM studentgpa where grade = ? and college = ? AND gpa < 2.5 and gpa >= 2.0";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, grade);
            preparedStatement.setString(2, college);
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

    public List<StudentInfo> exportLowWarningStudentGPA(String college, String grade) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<StudentInfo> list = new ArrayList<>();

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * FROM studentgpa where grade = ? and college = ? AND gpa >= 2.5";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, grade);
            preparedStatement.setString(2, college);
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