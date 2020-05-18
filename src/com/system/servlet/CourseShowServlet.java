package com.system.servlet;

import com.system.modle.CourseAndClass;
import com.system.dao.CourseShowDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/course")
public class CourseShowServlet extends HttpServlet {
    private CourseShowDao courseShowDao = new CourseShowDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("curUsername");
        List<CourseAndClass> list = null;
        if(method == null)
            method ="findAll";
        switch(method) {
            case "findAll" :
                list = courseShowDao.findAll(name);
                req.setAttribute("list", list);
                req.setAttribute("curGrade", "所有课程");
                req.getRequestDispatcher("showClass.jsp").forward(req, resp);
                break;
            case "findByGrade" :
                String grade = req.getParameter("Grade");
                list = courseShowDao.findByGrade(grade, name);
                req.setAttribute("list", list);
                req.setAttribute("curGrade", grade + "级");
                req.getRequestDispatcher("showClass.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String searchclassname = req.getParameter("searchClassName");
        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("curUsername");
        List<CourseAndClass> list = null;
        list = courseShowDao.findByClassName(searchclassname, name);
        req.setAttribute("list", list);
        req.getRequestDispatcher("showClass.jsp").forward(req, resp);
    }
}