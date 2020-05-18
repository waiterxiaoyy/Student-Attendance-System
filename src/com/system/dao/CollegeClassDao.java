package com.system.dao;

import com.system.modle.CollegeClass;
import com.system.modle.CollegeClassLearn;
import com.system.modle.CollegeClassStudent;
import com.system.util.JDBCTools;
import sun.dc.pr.PRError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CollegeClassDao {

    public int findWeekCountSinginCountByStudentID(String studentid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = JDBCTools.getConnection();
            String sq1 = "SELECT count(signinsituation) as signin FROM weekcount where studentid=? and signinsituation ='ÒÑÇ©µ½'";
            preparedStatement = connection.prepareStatement(sq1);
            preparedStatement.setString(1, studentid);
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

    public int findWeekCountUnSinginCountByStudentID(String studentid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = JDBCTools.getConnection();
            String sq1 = "SELECT count(signinsituation) as signin FROM weekcount where studentid=? and signinsituation ='Î´Ç©µ½'";
            preparedStatement = connection.prepareStatement(sq1);
            preparedStatement.setString(1, studentid);
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

    public List<CollegeClassStudent> findCollegeClassStudentByCollegeClassid(String collegeclassid, int page) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CollegeClassStudent> list = new ArrayList<>();

        int limit = 8;
        int index = (page - 1) * limit;
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT \n" +
                    "student.studentid,\n" +
                    "student.studentname,\n" +
                    "student.collegeid,\n" +
                    "student.college,\n" +
                    "student.collegeclassid,\n" +
                    "student.collegeclassname,\n" +
                    "COUNT(studentcourse.studentid) \n" +
                    "FROM student\n" +
                    "LEFT JOIN\n" +
                    "studentcourse \n" +
                    "ON \n" +
                    "student.studentid = studentcourse.studentid \n" +
                    "where student.collegeclassid=?\n" +
                    "GROUP BY student.studentid\n" +
                    "LIMIT ?, ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, collegeclassid);
            preparedStatement.setInt(2, index);
            preparedStatement.setInt(3, limit);
            resultSet = preparedStatement.executeQuery();
            CollegeClassStudent collegeClassStudent = null;
            while (resultSet.next()) {
                String studentid = resultSet.getString(1);
                String studentname = resultSet.getString(2);
                String collegeid = resultSet.getString(3);
                String college = resultSet.getString(4);
                String collegeclassname = resultSet.getString(6);
                int coursenumber = resultSet.getInt(7);
                int attendencetimes = findWeekCountSinginCountByStudentID(studentid);
                int unattendencetimes = findWeekCountUnSinginCountByStudentID(studentid);
                collegeClassStudent = new CollegeClassStudent(studentid, studentname, collegeid, college, collegeclassid, collegeclassname, coursenumber, attendencetimes, unattendencetimes);
                list.add(collegeClassStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public int countCollegeClassStudentDatails(String collegeclassid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT count(*) FROM student where collegeclassid = ?";
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

    public List<CollegeClassLearn> findCollegeClassByGradeForLearn(String college, String grade) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CollegeClassLearn> list = new ArrayList<>();
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * FROM collegeclass where college = ? AND grade =?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, college);
            preparedStatement.setString(2, grade);
            resultSet = preparedStatement.executeQuery();
            CollegeClassLearn collegeClassLearn = null;
            CollegeClassDao collegeClassDao = new CollegeClassDao();
            while (resultSet.next()) {
                String collegeclassid = resultSet.getString(5);
                String collegeclassname = resultSet.getString(6);
                int hignwarning = collegeClassDao.findCollegeClassHighWarning(collegeclassid);
                int middlewarning = collegeClassDao.findCollegeClassMiddleWarning(collegeclassid);
                int lowwarning = collegeClassDao.findCollegeClassLowWarning(collegeclassid);
                collegeClassLearn = new CollegeClassLearn(grade,collegeclassid,collegeclassname, hignwarning, middlewarning, lowwarning);
                list.add(collegeClassLearn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }
    public List<CollegeClass> findCollegeClassByGradeForLibrary(String college, String grade) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CollegeClass> list = new ArrayList<>();
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * FROM collegeclass where college = ? AND grade =?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, college);
            preparedStatement.setString(2, grade);
            resultSet = preparedStatement.executeQuery();
            CollegeClass collegeClass = null;
            while (resultSet.next()) {
                String collegeid = resultSet.getString(2);
                String collegeclassid = resultSet.getString(5);
                String collegeclassname = resultSet.getString(6);
                int studentnumber = resultSet.getInt(7);
                collegeClass = new CollegeClass(collegeid, college, grade, collegeclassid, collegeclassname, studentnumber);
                list.add(collegeClass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public int findCollegeClassHighWarning(String collegeClassid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT count(gpa) FROM studentgpa where collegeclassid = ? AND gpa < 2.0";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, collegeClassid);
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
    public int findCollegeClassMiddleWarning(String collegeClassid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT count(gpa) FROM studentgpa where collegeclassid = ? AND gpa >= 2.0 and gpa < 2.5";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, collegeClassid);
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

    public int findCollegeClassLowWarning(String collegeClassid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT count(gpa) FROM studentgpa where collegeclassid = ? AND gpa >= 2.5";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, collegeClassid);
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

    public List<CollegeClass> findCollegeClassByGrade(String college, String grade) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CollegeClass> list = new ArrayList<>();
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * FROM collegeclass where college = ? AND grade =?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, college);
            preparedStatement.setString(2, grade);
            resultSet = preparedStatement.executeQuery();
            CollegeClass collegeClass = null;
            while (resultSet.next()) {
                String collegeid = resultSet.getString(2);
                String collegeclassid = resultSet.getString(5);
                String collegeclassname = resultSet.getString(6);
                int studentnumber = resultSet.getInt(7);
                collegeClass = new CollegeClass(collegeid, college, grade, collegeclassid, collegeclassname, studentnumber);
                list.add(collegeClass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public List<CollegeClassStudent> findStudentInCollegeGrade(String college, String grade, String searchclassname, String searchstudentid, String searchstudentname) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<CollegeClassStudent> list = new ArrayList<>();
        String searchclassnamelike = "%" +searchclassname +"%";
        String searchstudentidlike = "%" +searchstudentid +"%";
        String searchstudentnamelike = "%" +searchstudentname +"%";
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT \n" +
                    "student.studentid,\n" +
                    "student.studentname,\n" +
                    "student.collegeid,\n" +
                    "student.college,\n" +
                    "student.collegeclassid,\n" +
                    "student.collegeclassname,\n" +
                    "COUNT(studentcourse.studentid) \n" +
                    "FROM student\n" +
                    "LEFT JOIN\n" +
                    "studentcourse \n" +
                    "ON \n" +
                    "student.studentid = studentcourse.studentid \n" +
                    "where student.grade = ? and student.college=? and (student.collegeclassname like ? and student.studentid like ? and student.studentname like ?)\n" +
                    "GROUP BY student.studentid";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, grade);
            preparedStatement.setString(2, college);
            preparedStatement.setString(3, searchclassnamelike);
            preparedStatement.setString(4, searchstudentidlike);
            preparedStatement.setString(5, searchstudentnamelike);
            resultSet = preparedStatement.executeQuery();
            CollegeClassStudent collegeClassStudent = null;
            while(resultSet.next()) {
                String studentid = resultSet.getString(1);
                String studentname = resultSet.getString(2);
                String collegeid = resultSet.getString(3);
                String collegeclassid = resultSet.getString(5);
                String collegeclassname = resultSet.getString(6);
                int coursenumber = resultSet.getInt(7);
                int attendencetimes = findWeekCountSinginCountByStudentID(studentid);
                int unattendencetimes = findWeekCountUnSinginCountByStudentID(studentid);
                collegeClassStudent = new CollegeClassStudent(studentid, studentname, collegeid, college, collegeclassid, collegeclassname, coursenumber, attendencetimes, unattendencetimes);
                list.add(collegeClassStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

    public int countCollegeClassStudentDatailsBySearch(String college, String grade, String searchclassname, String searchstudentid, String searchstudentname) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String searchclassnamelike = "%" +searchclassname +"%";
        String searchstudentidlike = "%" +searchstudentid +"%";
        String searchstudentnamelike = "%" +searchstudentname +"%";
        int count = 0;
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT COUNT(*) from (\n" +
                    "SELECT \n" +
                    "student.studentid,\n" +
                    "student.studentname,\n" +
                    "student.collegeid,\n" +
                    "student.college,\n" +
                    "student.collegeclassid,\n" +
                    "student.collegeclassname,\n" +
                    "COUNT(studentcourse.studentid) \n" +
                    "FROM student\n" +
                    "LEFT JOIN\n" +
                    "studentcourse \n" +
                    "ON \n" +
                    "student.studentid = studentcourse.studentid \n" +
                    "where student.grade = ? and student.college=? and (student.collegeclassname like ? and student.studentid like ? and student.studentname like ?)\n" +
                    "GROUP BY student.studentid) as count";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, grade);
            preparedStatement.setString(2, college);
            preparedStatement.setString(3, searchclassnamelike);
            preparedStatement.setString(4, searchstudentidlike);
            preparedStatement.setString(5, searchstudentnamelike);
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
}