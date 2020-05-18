package com.system.servlet;

import com.system.dao.CollegeClassDao;
import com.system.modle.CollegeClass;
import com.system.modle.CollegeClassLearn;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/collegeClass")
public class CollegeClassShowServlet extends HttpServlet {
    private CollegeClassDao collegeClassDao = new CollegeClassDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        HttpSession session = req.getSession();
        String grade = (String) session.getAttribute("curUserGrade");
        String college = (String) session.getAttribute("curCollege");
        switch(method) {
            case "findCollegeClassByGrade":

                List<CollegeClass> list = null;
                list = collegeClassDao.findCollegeClassByGrade(college, grade);
                req.setAttribute("curShow", "所有班级");
                req.setAttribute("list", list);
                req.getRequestDispatcher("showCollegeClass.jsp").forward(req, resp);
                break;
            case "findCollegeClassByGradeForLearn":
                List<CollegeClassLearn> list1 = null;
                list1 = collegeClassDao.findCollegeClassByGradeForLearn(college, grade);
                req.setAttribute("curShow", "所有班级");
                req.setAttribute("list", list1);
                req.getRequestDispatcher("showCollegeClassLearn.jsp").forward(req, resp);
                break;
            case "findCollegeClassByGradeForLibrary":
                list = null;
                list = collegeClassDao.findCollegeClassByGradeForLibrary(college, grade);
                req.setAttribute("curShow", "所有班级");
                req.setAttribute("list", list);
                req.getRequestDispatcher("showCollegeClassLibrary.jsp").forward(req, resp);
                break;
        }
    }
}