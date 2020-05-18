package com.system.dao;

import com.system.modle.WeekCount;
import com.system.util.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentWeekCountDao {
    public List<WeekCount> findWeekCount(String searchWeekCount, String findClassID, String studentid) {
        List<WeekCount> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String likeSearchWeekCount = "%" + searchWeekCount + "%";
        try {
            connection = JDBCTools.getConnection();
            String sql = "SELECT * FROM weekcount WHERE classid = ? and weekcount LIKE ? and studentid = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, findClassID);
            preparedStatement.setString(2, likeSearchWeekCount);
            preparedStatement.setString(3 , studentid);
            resultSet = preparedStatement.executeQuery();
            WeekCount weekCount = null;
            while (resultSet.next()) {
                String classname = resultSet.getString(3);
                String weekcount = resultSet.getString(4);
                String weekcountid = resultSet.getString(5);
                String studentname = resultSet.getString(7);
                String signinsituation = resultSet.getString(8);
                String signoutsituation = resultSet.getString(9);
                weekCount = new WeekCount(findClassID, classname, weekcount, weekcountid, studentid, studentname, signinsituation, signoutsituation);
                list.add(weekCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection, preparedStatement, resultSet);
        }
        return list;
    }

}