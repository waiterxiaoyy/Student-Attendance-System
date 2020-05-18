package com.system.servlet;

import com.system.dao.WeekCountDao;
import com.system.modle.WeekCount;
import com.system.modle.WeekCountAndTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/WeekCount")
public class WeekCountShowServlet extends HttpServlet {
    private WeekCountDao weekCountDao = new WeekCountDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");

        switch (method) {
            case "findByWeekCountID":
                String weekcountid = req.getParameter("WeekCountID");
                String pageStr = req.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                List<WeekCountAndTime> list = null;
                list = weekCountDao.findByWeekCountID(weekcountid, page);
                int count = weekCountDao.countWeekCountDatails(weekcountid);
                int pages = 1;
                if(count % 8 == 0)
                    pages = count / 8;
                else
                    pages = count / 8 + 1;
                String situation = "nostart";
                if(list.size() > 0) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String starttimeStr = list.get(0).getStarttime();
                    String endtimeStr = list.get(0).getEndtime();
                    try {
                        Date starttime = simpleDateFormat.parse(starttimeStr);
                        Date endtime = simpleDateFormat.parse(endtimeStr);
                        Date curTime = new Date();
                        if(curTime.before(starttime)) {
                            situation = "nostart";
                        }
                        else if(curTime.before(endtime) && curTime.after(starttime)) {
                            situation = "ing";
                        }
                        else if(curTime.after(endtime)) {
                            situation = "end";
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                req.setAttribute("list", list);
                req.setAttribute("findweekcountid",weekcountid);
                req.setAttribute("situation", situation);
                req.setAttribute("currentPage", page);
                req.setAttribute("pages", pages);
                req.getRequestDispatcher("weekCountDetailsShow.jsp").forward(req, resp);
                break;
            case "deleteByWeekCountID":
                weekcountid = req.getParameter("WeekCountID");
                String classid = weekcountid.substring(0, 10);
                boolean success = weekCountDao.deleteByWeekCountID(weekcountid);
                if(success == true)
                    weekCountDao.deleteWeekCountDetailsByWeekCountID(weekcountid);
                resp.sendRedirect("classShow?method=findClassByClassid&classid=" + classid);
                break;
            case "updateSigninSituation":
                weekcountid = req.getParameter("WeekCountID");
                String studentid = req.getParameter("StudentID");
                weekCountDao.updateSigninSituation(weekcountid, studentid);
                resp.sendRedirect("/WeekCount?method=findByWeekCountID&WeekCountID=" + weekcountid + "&page=1");
                break;
            case "updateSignoutSituation":
                weekcountid = req.getParameter("WeekCountID");
                studentid = req.getParameter("StudentID");
                weekCountDao.updateSignoutSituation(weekcountid, studentid);
                resp.sendRedirect("/WeekCount?method=findByWeekCountID&WeekCountID=" + weekcountid  + "&page=1");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");
        List<WeekCount> list = null;
        switch (method) {
            case "addWeekCount":
                String classid= req.getParameter("classid");
                String weekStr = req.getParameter("week");
                String countStr = req.getParameter("count");
                String starttime = req.getParameter("starttime");
                String endtime = req.getParameter("endtime");
                String week = StringToNumberString(weekStr);
                String count = StringToNumberString(countStr);
                String weekcount = weekStr + countStr;
                String weekcountid = classid + week + count;
                boolean success = weekCountDao.addWeekCount(classid, weekcount, weekcountid, starttime, endtime);
                if(success == true) {
                    weekCountDao.addWeekCountDetails(classid, weekcountid);
                }
                resp.sendRedirect("/classShow?method=findClassByClassid&classid=" + classid);
                break;
            case "findStudentInWeekCount":
                String studentinfo = req.getParameter("searchStudent");
                String situation = req.getParameter("Situation");
                weekcountid = req.getParameter("findweekcountid");
                list = weekCountDao.findStudentInWeekCount(studentinfo, weekcountid);
                int searchcount = weekCountDao.countWeekCountDatailsBySearch(weekcountid, studentinfo);
                int pages = 1;
                if(searchcount % 8 == 0)
                    pages = searchcount / 8;
                else
                    pages = searchcount / 8 + 1;
                req.setAttribute("list", list);
                req.setAttribute("situation", situation);
                req.setAttribute("findweekcountid", weekcountid);
                req.setAttribute("currentPage", 1);
                req.setAttribute("pages", pages);
                req.getRequestDispatcher("weekCountDetailsShow.jsp").forward(req, resp);
                break;
        }
    }

    protected String StringToNumberString(String stringandnumber) {
        String regEx = "[^0-9]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(stringandnumber);
        return matcher.replaceAll("").trim();
    }
}