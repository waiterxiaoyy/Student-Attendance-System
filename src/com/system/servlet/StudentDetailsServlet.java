package com.system.servlet;

import com.system.dao.CollegeClassDao;
import com.system.dao.StudentDetailsDao;
import com.system.dao.StudentLibraryDao;
import com.system.modle.CollegeClassLibrary;
import com.system.modle.CollegeClassStudent;
import com.system.modle.StudentInfo;
import com.system.modle.WeekCount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/studentDetails")
public class StudentDetailsServlet extends HttpServlet {
    private CollegeClassDao collegeClassDao = new CollegeClassDao();
    private StudentDetailsDao studentDetailsDao = new StudentDetailsDao();
    private StudentLibraryDao studentLibraryDao = new StudentLibraryDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        HttpSession session = req.getSession();
        String grade = (String) session.getAttribute("curUserGrade");
        String college = (String) session.getAttribute("curCollege");
        switch (method) {
            case "findCollegeClassStudentLibraryByCollegeClassid" :
                String collegeclassid = req.getParameter("CollegeClassID");
                String pageStr = req.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                List<CollegeClassLibrary> list1 = null;
                list1 = studentLibraryDao.findCollegeClassStudentLibraryByCollegeClassid(collegeclassid, page);
                int count = studentLibraryDao.countCollegeClassStudentLibraryDatails(collegeclassid);
                int pages = 1;
                if(count % 8 == 0)
                    pages = count / 8;
                else
                    pages = count / 8 + 1;
                req.setAttribute("list", list1);
                String curshow ="";
                if(list1.size() > 0) {
                    curshow = list1.get(0).getCollegeclassname();
                }
                req.setAttribute("curShow", curshow);
                req.setAttribute("collegeclassid", collegeclassid);
                req.setAttribute("currentPage", page);
                req.setAttribute("pages", pages);
                req.setAttribute("college", college);
                req.setAttribute("grade", grade);
                req.getRequestDispatcher("showCollegeClassLibraryStudent.jsp").forward(req, resp);
                break;
            case "findCollegeClassStudentByCollegeClassid":
                collegeclassid = req.getParameter("CollegeClassID");
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                List<CollegeClassStudent> list = null;
                list = collegeClassDao.findCollegeClassStudentByCollegeClassid(collegeclassid, page);
                count = collegeClassDao.countCollegeClassStudentDatails(collegeclassid);
                pages = 1;
                if(count % 8 == 0)
                    pages = count / 8;
                else
                    pages = count / 8 + 1;
                req.setAttribute("list", list);
                curshow ="";
                if(list.size() > 0) {
                    curshow = list.get(0).getCollegeclassname();
                }
                req.setAttribute("curShow", curshow);
                req.setAttribute("collegeclassid", collegeclassid);
                req.setAttribute("currentPage", page);
                req.setAttribute("pages", pages);
                req.setAttribute("college", college);
                req.setAttribute("grade", grade);
                req.getRequestDispatcher("showCollegeClassStudent.jsp").forward(req, resp);
                break;
            case "findStudentInCollegeGrade":
                String searchclassname = req.getParameter("searchClassName");
                String searchstudentid = req.getParameter("searchStudentid");
                String searchstudentname = req.getParameter("searchStudentName");
                list = null;
                list = collegeClassDao.findStudentInCollegeGrade(college, grade, searchclassname, searchstudentid, searchstudentname);
                int searchcount = collegeClassDao.countCollegeClassStudentDatailsBySearch(college, grade, searchclassname, searchstudentid, searchstudentname);
                pages = 1;
                if(searchcount % 8 == 0)
                    pages = searchcount / 8;
                else
                    pages = searchcount / 8 + 1;
                req.setAttribute("list", list);
                req.setAttribute("curShow", "[¹Ø¼ü´Ê]" + searchclassname + "/" + searchstudentid+"/" + searchstudentname);
                req.setAttribute("currentPage", 1);
                req.setAttribute("pages", pages);
                req.setAttribute("college", college);
                req.setAttribute("grade", grade);
                req.setAttribute("pages", pages);
                req.getRequestDispatcher("showCollegeClassStudentFromSearch.jsp").forward(req, resp);
                break;
            case "findStudentLibraryInCollegeGrade":
                searchclassname = req.getParameter("searchClassName");
                searchstudentid = req.getParameter("searchStudentid");
                searchstudentname = req.getParameter("searchStudentName");
                list = null;
                list1 = studentLibraryDao.findStudentLibraryInCollegeGrade(college, grade, searchclassname, searchstudentid, searchstudentname);
                searchcount = studentLibraryDao.countCollegeClassLibraryStudentDatailsBySearch(college, grade, searchclassname, searchstudentid, searchstudentname);
                pages = 1;
                if(searchcount % 8 == 0)
                    pages = searchcount / 8;
                else
                    pages = searchcount / 8 + 1;
                req.setAttribute("list", list1);
                req.setAttribute("curShow", "[¹Ø¼ü´Ê]" + searchclassname + "/" + searchstudentid+"/" + searchstudentname);
                req.setAttribute("currentPage", 1);
                req.setAttribute("pages", pages);
                req.setAttribute("college", college);
                req.setAttribute("grade", grade);
                req.setAttribute("pages", pages);
                req.getRequestDispatcher("showCollegeClassLibraryStudent.jsp").forward(req, resp);
                break;
            case "findStudentDetailsByStudentID":
                String studentid = req.getParameter("StudentID");
                List<WeekCount> list2 = null;
                StudentInfo studentInfo = studentDetailsDao.findStudentInfo(studentid);
                int attendenceCount = studentDetailsDao.countAttendenCount(studentid);
                int signinCount = studentDetailsDao.countSigninCount(studentid);
                int signoutCount = studentDetailsDao.countSignoutCount(studentid);
                BigDecimal gpa = studentDetailsDao.findStudentGPA(studentid);
                int countLibrary = studentDetailsDao.countLibarary(studentid);
                list2 = studentDetailsDao.findStudentWeekCountDetails(studentid);

                double signinRatio = ((double)signinCount / (double)attendenceCount) * 100;
                double signoutRatio = ((double)signoutCount / (double)attendenceCount) * 100;

                double gpaRation = (gpa.doubleValue() / 5 ) * 100;

                studentInfo.setAttendencecount(attendenceCount);
                studentInfo.setSignincount(signinCount);
                studentInfo.setSigninRatio(signinRatio);
                studentInfo.setCountLibrary(countLibrary);
                studentInfo.setGpa(gpa);
                studentInfo.setGpaRatio(gpaRation);
                studentInfo.setSignoutRatio(signoutRatio);

                req.setAttribute("studentInfo", studentInfo);
                req.setAttribute("list", list2);

                req.getRequestDispatcher("studentDetailsShow.jsp").forward(req, resp);
                break;

        }
    }
}