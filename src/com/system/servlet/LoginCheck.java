package com.system.servlet;

import com.system.modle.User;
import com.system.util.JDBCTools;
import com.system.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/loginCheck")
public class LoginCheck extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username0 = req.getParameter("username");
        String password0 = req.getParameter("password");
        req.setAttribute("username", username0);
        req.setAttribute("password", password0);
        User user = new User(username0, password0);
        Connection connection = null;
        try {
            connection = JDBCTools.getConnection();
            User currentUser = UserDao.login(connection, user);
            if(currentUser == null) {
                resp.sendRedirect("/login/errorLogin.jsp");
            }
            else {
                HttpSession session = req.getSession(true);
                session.setAttribute("curUsername", currentUser.getName());
                session.setAttribute("curCollege", currentUser.getCollege());
                session.setAttribute("curUserID", currentUser.getUserName());
                session.setAttribute("curIdentity", currentUser.getIdentity());
                session.setAttribute("curUserGrade", currentUser.getGrade());
                if (currentUser.getIdentity() == 0)
                    resp.sendRedirect("/course?method=findAll");
                else if (currentUser.getIdentity() == 1)
                    resp.sendRedirect("/studentClass?method=findAll");
                else if(currentUser.getIdentity() == 2)
                    req.getRequestDispatcher("collegeIndex.jsp").forward(req, resp);
            }
//            String sql = "select * from loginuser";
//            preparedStatement = connection.prepareStatement(sql);
//            resultSet = preparedStatement.executeQuery();
//            int count = 0;
//            String college = "";
//            while(resultSet.next()) {
//                String username1 = resultSet.getString(1);
//                String password1 = resultSet.getString(2);
//                college = resultSet.getString(3);
//                if(username0.equals(username1) && password0.equals(password1))
//                    count++;
//            }
//            if(count > 0) {
//                HttpSession session = req.getSession(true);
//                session.setAttribute("username", username0);
//                session.setAttribute("password",password0);
//                session.setAttribute("college", college);
//                resp.sendRedirect("/login/loginStatusCheck.jsp");
//            }
//            else {
//                //req.setAttribute("error", "用户名或者密码错误");
//                //resp.sendRedirect("/login/errorLogin.jsp");
//                req.getRequestDispatcher("/login/errorLogin.jsp").forward(req, resp);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,null, null);
        }
    }
}