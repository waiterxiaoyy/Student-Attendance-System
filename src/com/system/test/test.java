package com.system.test;

import com.system.util.JDBCTools;

import java.sql.*;

public class test {
    public static void main(String[] args) {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String url ="jdbc:sqlserver://localhost:1433;DatebaseName = student";
//            String user = "sa";
//            String password = "abc123";
//            Connection connection = DriverManager.getConnection(url, user, password);
//            System.out.println(connection);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCTools.getConnection();
            String sql = "select * from loginuser";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                String username0 = resultSet.getString(1);
                String password0 = resultSet.getString(2);
                System.out.println(username0);
                System.out.println(password0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
    }
}