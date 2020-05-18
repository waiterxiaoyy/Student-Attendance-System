package com.system.servlet;

import com.system.dao.StudentGPADao;
import com.system.modle.StudentInfo;
import sun.management.jdp.JdpGenericPacket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/studentWarning")
public class StudentWarningServlet extends HttpServlet {
    private StudentGPADao studentGPADao = new StudentGPADao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        List<StudentInfo> list = null;
        HttpSession session = req.getSession();
        String college = (String) session.getAttribute("curCollege");
        String grade = (String) session.getAttribute("curUserGrade");
        switch(method) {
            case "findHighWarningByCollegeClassid" :
                String collegeclassid = req.getParameter("CollegeClassID");
                list = studentGPADao.findHighWarningByCollegeClassid(collegeclassid);
                String curshow = "";
                if(list.size() > 0) {
                    curshow = list.get(0).getCollegeclassname();
                }
                curshow += "/高风险学生";
                req.setAttribute("list", list);
                req.setAttribute("curShow", curshow);
                req.getRequestDispatcher("showCollegeClassLearnStudent.jsp").forward(req, resp);
                break;
            case "findMiddleWarningByCollegeClassid" :
                collegeclassid = req.getParameter("CollegeClassID");
                list = studentGPADao.findMiddleWarningByCollegeClassid(collegeclassid);
                curshow = "";
                if(list.size() > 0) {
                    curshow = list.get(0).getCollegeclassname();
                }
                curshow += "/中风险学生";
                req.setAttribute("list", list);
                req.setAttribute("curShow", curshow);
                req.getRequestDispatcher("showCollegeClassLearnStudent.jsp").forward(req, resp);
                break;
            case "findLowWarningByCollegeClassid" :
                collegeclassid = req.getParameter("CollegeClassID");
                list = studentGPADao.findLowWarningByCollegeClassid(collegeclassid);
                curshow = "";
                if(list.size() > 0) {
                    curshow = list.get(0).getCollegeclassname();
                }
                curshow += "/无风险学生";
                req.setAttribute("list", list);
                req.setAttribute("curShow", curshow);
                req.getRequestDispatcher("showCollegeClassLearnStudent.jsp").forward(req, resp);
                break;
            case "findStudentGPAInCollegeGrade":
                String searchclassname = req.getParameter("searchClassName");
                String searchstudentid = req.getParameter("searchStudentid");
                String searchstudentname = req.getParameter("searchStudentName");
                list = null;
                list = studentGPADao.findStudentGPAInCollegeGrade(college, grade, searchclassname, searchstudentid, searchstudentname);
                req.setAttribute("list", list);
                req.setAttribute("curShow", "[关键词]" + searchclassname + "/" + searchstudentid+"/" + searchstudentname);
                req.getRequestDispatcher("showCollegeClassLearnStudent.jsp").forward(req, resp);
                break;
        }
    }
}