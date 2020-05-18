package com.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.system.modle.User;
public class UserDao {

    /**
     * µÇÂ¼ÑéÖ¤
     * @param connection
     * @param user
     * @return
     * @throws Exception
     */
    public static User login(Connection connection,  User user) throws Exception{
        User resultUser=null;
        try {
            String sql="select * from loginuser where username=? and password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                resultUser=new User();
                resultUser.setUserName(resultSet.getString("username"));
                resultUser.setPassword(resultSet.getString("password"));
                resultUser.setCollege(resultSet.getString("college"));
                resultUser.setName(resultSet.getString("name"));
                resultUser.setIdentity(resultSet.getInt("identity"));
                resultUser.setGrade(resultSet.getString("grade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultUser;
    }
}