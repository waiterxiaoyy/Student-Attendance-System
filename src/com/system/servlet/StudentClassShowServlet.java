package com.system.servlet;

import com.system.dao.StudentClassDao;
import com.system.modle.CourseAndClass;
import com.system.modle.WeekCount;
import com.system.modle.WeekCountAndTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/studentClass")
public class StudentClassShowServlet extends HttpServlet {
    private StudentClassDao studentClassDao = new StudentClassDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        HttpSession session = req.getSession();
        String studentid = (String) session.getAttribute("curUserID");
        List<CourseAndClass> list = null;
        List<WeekCountAndTime> listWeekCount = null;
        if(method == null)
            method ="findAll";
        switch(method) {
            case "findAll" :
                list = studentClassDao.findAll(studentid);
                req.setAttribute("list", list);
                req.setAttribute("curGrade", "所有课程");
                req.getRequestDispatcher("studentClassShow.jsp").forward(req, resp);
                break;
            case "findByGrade" :
                String grade = req.getParameter("Grade");
                list = studentClassDao.findByGrade(grade, studentid);
                req.setAttribute("list", list);
                req.setAttribute("curGrade", grade + "级");
                req.getRequestDispatcher("studentClassShow.jsp").forward(req, resp);
                break;
            case "findWeekCountByClassid":
                String classid = req.getParameter("classid");
                listWeekCount = studentClassDao.findWeekCountByClassid(classid, studentid);
                String starttimeStr = "";
                String endtimeStr = "";
                if(listWeekCount.size() > 0) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    for(int i = 0; i < listWeekCount.size(); i++) {
                        starttimeStr = listWeekCount.get(i).getStarttime();
                        endtimeStr = listWeekCount.get(i).getEndtime();
                        try {
                            Date starttime = simpleDateFormat.parse(starttimeStr);
                            Date endtime = simpleDateFormat.parse(endtimeStr);
                            Date curTime = new Date();
                            if(curTime.before(starttime)) {
                                listWeekCount.get(i).setSituation("nostart");
                            }
                            else if(curTime.before(endtime) && curTime.after(starttime)) {
                                listWeekCount.get(i).setSituation("ing");
                            }
                            else if(curTime.after(endtime)) {
                                listWeekCount.get(i).setSituation("end");
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
                req.setAttribute("list", listWeekCount);
                req.setAttribute("findClassID", classid);
                req.getRequestDispatcher("studentWeekCountShow.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String searchclassname = req.getParameter("searchClassName");
        HttpSession session = req.getSession();
        String studentid = (String) session.getAttribute("curUserID");
        List<CourseAndClass> list = null;
        list = studentClassDao.findByClassName(searchclassname, studentid);
        req.setAttribute("list", list);
        req.getRequestDispatcher("studentClassShow.jsp").forward(req, resp);
    }
}