package com.system.servlet;


import com.system.dao.CourseShowDao;
import com.system.dao.WeekCountDao;
import com.system.modle.ClassShow;
import com.system.modle.CourseAndClass;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/classShow")
public class ClassShowServlet extends HttpServlet {
    private CourseShowDao courseShowDao = new CourseShowDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method) {
            case "findClassByClassid":
                String classid = req.getParameter("classid");
                List<ClassShow> list = null;
                list = courseShowDao.findByClassId(classid);
                req.setAttribute("list", list);
                req.setAttribute("classid", classid);
                req.getRequestDispatcher("/classDetailsShow.jsp").forward(req, resp);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        switch (method) {
            case "findByWeekCount":
                String classid = req.getParameter("findclassid");
                String weekcount = req.getParameter("searchWeekCount");
                List<ClassShow> list = null;
                list = courseShowDao.findByWeekCount(classid,weekcount);
                req.setAttribute("list",list);
                req.setAttribute("classid", classid);
                req.getRequestDispatcher("/classDetailsShow.jsp").forward(req,resp);
                break;
        }

    }
}