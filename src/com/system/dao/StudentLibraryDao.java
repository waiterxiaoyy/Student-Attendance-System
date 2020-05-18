package com.system.dao;

import com.system.modle.CollegeClassLibrary;
import com.system.modle.CollegeClassStudent;
import com.system.util.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;

public class StudentLibraryDao {
    public List<CollegeClassLibrary> findCollegeClassStudentLibraryByCollegeClassid(String collegeclassid, int page) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CollegeClassLibrary> list = new ArrayList<>();

        int limit = 8;
        int index = (page - 1) * limit;

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT studentid, studentname, collegeclassid, collegeclassname, countlibrary FROM studentlibrarycount where collegeclassid = ? LIMIT ?, ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, collegeclassid);
            preparedStatement.setInt(2, index);
            preparedStatement.setInt(3, limit);
            resultSet = preparedStatement.executeQuery();
            CollegeClassLibrary collegeClassLibrary = null;
            while (resultSet.next()) {
                String studentid = resultSet.getString(1);
                String studentname = resultSet.getString(2);
                String collegeclassname = resultSet.getString(4);
                int countlibrary = resultSet.getInt(5);
                collegeClassLibrary = new CollegeClassLibrary(studentid, studentname, collegeclassid, collegeclassname,countlibrary);
                list.add(collegeClassLibrary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public int countCollegeClassStudentLibraryDatails(String collegeclassid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT count(*) FROM studentlibrarycount where collegeclassid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,collegeclassid);
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

    public List<CollegeClassLibrary> findStudentLibraryInCollegeGrade(String college, String grade, String searchclassname, String searchstudentid, String searchstudentname) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CollegeClassLibrary> list = new ArrayList<>();
        String searchclassnamelike = "%" +searchclassname +"%";
        String searchstudentidlike = "%" +searchstudentid +"%";
        String searchstudentnamelike = "%" +searchstudentname +"%";
        int limit = 15;
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * FROM studentlibrarycount where grade =? and college = ? and collegeclassname LIKE ? and studentid LIKE ? and studentname LIKE ? LIMIT ?, ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, grade);
            preparedStatement.setString(2, college);
            preparedStatement.setString(3, searchclassnamelike);
            preparedStatement.setString(4, searchstudentidlike);
            preparedStatement.setString(5, searchstudentnamelike);
            preparedStatement.setInt(6, 0);
            preparedStatement.setInt(7, limit);
            resultSet = preparedStatement.executeQuery();
            CollegeClassLibrary collegeClassLibrary = null;
            while(resultSet.next()) {
                String studentid = resultSet.getString(2);
                String studentname = resultSet.getString(3);
                String collegeclassid = resultSet.getString(6);
                String collegeclassname = resultSet.getString(7);
                int countlibrary = resultSet.getInt(9);
                collegeClassLibrary = new CollegeClassLibrary(studentid, studentname, collegeclassid, collegeclassname, countlibrary);
                list.add(collegeClassLibrary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public int countCollegeClassLibraryStudentDatailsBySearch(String college, String grade, String searchclassname, String searchstudentid, String searchstudentname) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CollegeClassLibrary> list = new ArrayList<>();
        String searchclassnamelike = "%" +searchclassname +"%";
        String searchstudentidlike = "%" +searchstudentid +"%";
        String searchstudentnamelike = "%" +searchstudentname +"%";
        int count = 0;
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT count(*) FROM studentlibrarycount where grade =? and college = ? and collegeclassname LIKE ? and studentid LIKE ? and studentname LIKE ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, grade);
            preparedStatement.setString(2, college);
            preparedStatement.setString(3, searchclassnamelike);
            preparedStatement.setString(4, searchstudentidlike);
            preparedStatement.setString(5, searchstudentnamelike);
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