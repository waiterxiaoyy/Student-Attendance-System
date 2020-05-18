package com.system.dao;

import com.system.modle.WeekCount;
import com.system.modle.WeekCountAndTime;
import com.system.util.JDBCTools;
import jdk.nashorn.internal.scripts.JD;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeekCountDao {
    public void updateSignoutSituation(String weekcountid, String studentid) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "UPDATE weekcount SET signoutsituation = 'ÒÑÇ©ÍË' WHERE weekcountid = ? and studentid = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, weekcountid);
            preparedStatement.setString(2, studentid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }
    }

    public void updateSigninSituation(String weekcountid, String studentid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "UPDATE weekcount SET signinsituation = 'ÒÑÇ©µ½' WHERE weekcountid = ? and studentid = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, weekcountid);
            preparedStatement.setString(2, studentid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }
    }

    public List<WeekCount> findStudentInWeekCount(String studentinfo, String weekcountid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<WeekCount> list = new ArrayList<>();
        String studentinfolike = "%" + studentinfo + "%";
        WeekCount weekCount = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * FROM weekcount where weekcountid =? and (studentid like ? or studentname like ?) ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, weekcountid);
            preparedStatement.setString(2, studentinfolike);
            preparedStatement.setString(3, studentinfolike);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String classid = resultSet.getString(2);
                String classname = resultSet.getString(3);
                String weekcount = resultSet.getString(4);
                String studentid = resultSet.getString(6);
                String studentname = resultSet.getString(7);
                String signinsituation = resultSet.getString(8);
                String signoutsituation = resultSet.getString(9);
                weekCount = new WeekCount(classid, classname, weekcount, weekcountid, studentid, studentname, signinsituation, signoutsituation);
                list.add(weekCount);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
            return list;
    }

    public int countWeekCountDatailsBySearch(String weekcountid, String studentinfo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String studentinfolike = "%" + studentinfo + "%";
        int count = 0;
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT count(*) FROM weekcount where weekcountid =? and (studentid like ? or studentname like ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, weekcountid);
            preparedStatement.setString(2, studentinfolike);
            preparedStatement.setString(3, studentinfolike);
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

    public List<WeekCountAndTime> findByWeekCountID(String weekcountid, Integer page) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<WeekCountAndTime> list = new ArrayList<>();

        int limit = 8;
        int index = (page - 1) * limit;
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT weekcount.classid,weekcount.classname,weekcount.weekcount,weekcount.weekcountid,studentid,studentname,signinsituation,signoutsituation, classdetails.starttime, classdetails.endtime\n" +
                    "FROM weekcount, classdetails WHERE weekcount.weekcountid = ? and weekcount.weekcountid = classdetails.weekcountid limit ?, ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, weekcountid);
            preparedStatement.setInt(2, index);
            preparedStatement.setInt(3, limit);
            resultSet = preparedStatement.executeQuery();
            WeekCountAndTime weekCountAndTime = null;
            while (resultSet.next()) {
                String classid = resultSet.getString(1);
                String classname = resultSet.getString(2);
                String weekcount = resultSet.getString(3);
                String studentid = resultSet.getString(5);
                String studentname = resultSet.getString(6);
                String signinsituation = resultSet.getString(7);
                String signoutsituation = resultSet.getString(8);
                String starttimeStr = resultSet.getString(9);
                String endtimeStr = resultSet.getString(10);
//                System.out.println(starttimeStr + "  " + endtimeStr);
//
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date starttime = simpleDateFormat.parse(starttimeStr);
//                Date endtime = simpleDateFormat.parse(endtimeStr);
//                System.out.println(starttime + " " + endtime);
//                Timestamp starttimestamp = new Timestamp(starttime.getTime());
//                Timestamp endtimestamp = new Timestamp(endtime.getTime());
//                System.out.println(starttimestamp + " " + endtimestamp);
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

    public int countWeekCountDatails(String weekcountid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT count(*) FROM weekcount where weekcountid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,weekcountid);
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

    public boolean addWeekCount(String classid, String weekcount, String weekcountid, String starttime, String endtime) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "insert into classdetails (classid,classname,weekcount,weekcountid,starttime, endtime) select course.classid,course.classname,?,?,?,? from course where course.classid=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, weekcount);
            preparedStatement.setString(2, weekcountid);
            preparedStatement.setString(3, starttime);
            preparedStatement.setString(4, endtime);
            preparedStatement.setString(5, classid);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }
        return false;
    }

    public void addWeekCountDetails(String classid, String weekcountid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCTools.getConnection();
            String sq1 = "INSERT into weekcount (classid, classname,weekcount,weekcountid,studentid,studentname)\n" +
                    "SELECT classdetails.classid,classdetails.classname,classdetails.weekcount,classdetails.weekcountid,studentcourse.studentid,studentcourse.studentname from classdetails,studentcourse where classdetails.weekcountid = ? and studentcourse.classid = ?";
            preparedStatement = connection.prepareStatement(sq1);
            preparedStatement.setString(1, weekcountid);
            preparedStatement.setString(2, classid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }
    }

    public boolean deleteByWeekCountID(String weekcountid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "DELETE FROM classdetails where weekcountid=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, weekcountid);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }
        return false;
    }

    public void deleteWeekCountDetailsByWeekCountID(String weekcountid) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = JDBCTools.getConnection();
            String sql = "DELETE FROM weekcount where weekcountid=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, weekcountid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, null);
        }
    }


}