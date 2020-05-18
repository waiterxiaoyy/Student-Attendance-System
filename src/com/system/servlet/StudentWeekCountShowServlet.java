package com.system.servlet;

import com.system.dao.StudentWeekCountDao;
import com.system.dao.WeekCountDao;
import com.system.modle.WeekCount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/studentWeekCount")
public class StudentWeekCountShowServlet  extends HttpServlet {
    private StudentWeekCountDao studentWeekCountDao = new StudentWeekCountDao();
    private WeekCountDao weekCountDao = new WeekCountDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        HttpSession session = req.getSession();
        String studentid = (String) session.getAttribute("curUserID");
        List<WeekCount> list = null;
        switch (method) {
            case "findWeekCount":
                String searchWeekCount = req.getParameter("searchWeekCount");
                String findClassID = req.getParameter("findClassID");
                list = studentWeekCountDao.findWeekCount(searchWeekCount, findClassID, studentid);
                req.setAttribute("list", list);
                req.getRequestDispatcher("studentWeekCountShow.jsp").forward(req, resp);
                break;
            case "updateSigninSituation":
                String weekcountid = req.getParameter("WeekCountID");
                studentid = req.getParameter("StudentID");
                String classid = req.getParameter("ClassID");
                weekCountDao.updateSigninSituation(weekcountid, studentid);
                resp.sendRedirect("/studentClass?method=findWeekCountByClassid&classid=" + classid + "&studentid=" + studentid);
                break;
            case "updateSignoutSituation":
                weekcountid = req.getParameter("WeekCountID");
                studentid = req.getParameter("StudentID");
                classid = req.getParameter("ClassID");
                weekCountDao.updateSignoutSituation(weekcountid, studentid);
                resp.sendRedirect("/studentClass?method=findWeekCountByClassid&classid=" + classid + "&studentid=" + studentid);
                break;
        }
    }
}