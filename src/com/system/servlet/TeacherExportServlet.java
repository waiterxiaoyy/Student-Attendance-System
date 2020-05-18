package com.system.servlet;

import com.system.dao.ExportDao;
import com.system.modle.CollegeClassLibrary;
import com.system.modle.ExportWeekCountDetails;
import com.system.modle.StudentInfo;
import com.system.util.CommonExcel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/teacherExport")
public class TeacherExportServlet extends HttpServlet {
    private ExportDao exportDao = new ExportDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        HttpSession session = req.getSession();
        String college = (String) session.getAttribute("curCollege");
        String grade = (String) session.getAttribute("curUserGrade");
        List<ExportWeekCountDetails> list = null;
        List<StudentInfo> list1 = null;
        switch (method) {
            case "exportWeekCountDetails":
                // Dao层获取数据库的数据
                String weekcountid = req.getParameter("WeekCountID");
                list = exportDao.findWeekCountDetails(weekcountid);

                // 定义标题（第一行）
                String title = "考勤详情";

                // 定义列名（第二行）
                String[] rowsName = new String[]{"学号","姓名","班级名称","周/节次","签到情况","签退情况"};

                // 定义主题内容（第三行起）
                List<Object[]> dataList = new ArrayList<Object[]>();

                // 定义每一行的临时变量，并放入数据
                Object[] objs = null;
                for (int i = 0; i < list.size(); i++) {
                    objs = new Object[rowsName.length];
                    objs[0] = list.get(i).getStudentid();
                    objs[1] = list.get(i).getStudentname();
                    objs[2] = list.get(i).getClassname();
                    objs[3] = list.get(i).getWeekcount();
                    objs[4] = list.get(i).getSigninsituation();
                    objs[5] = list.get(i).getSignoutsituation();
                    dataList.add(objs);
                }

                // 定义Excel文件名
                String fileName="考勤详情"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // 创建CommonExcel对象，调用downloadExcel()对象导出Excel
                CommonExcel ex = new CommonExcel(title, rowsName, dataList, resp, fileName);
                try {
                    ex.downloadExcel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "exportAllGrade":
                list = exportDao.findAllGradeWeekCountDetails(college, grade);

                // 定义标题（第一行）
                title = "考勤详情";

                // 定义列名（第二行）
                rowsName = new String[]{"学号","姓名","班级名称","周/节次","签到情况"};

                // 定义主题内容（第三行起）
                dataList = new ArrayList<Object[]>();

                // 定义每一行的临时变量，并放入数据
                objs = null;
                for (int i = 0; i < list.size(); i++) {
                    objs = new Object[rowsName.length];
                    objs[0] = list.get(i).getStudentid();
                    objs[1] = list.get(i).getStudentname();
                    objs[2] = list.get(i).getClassname();
                    objs[3] = list.get(i).getWeekcount();
                    objs[4] = list.get(i).getSigninsituation();
                    dataList.add(objs);
                }

                // 定义Excel文件名
                fileName= college+ grade + "级考勤详情"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // 创建CommonExcel对象，调用downloadExcel()对象导出Excel
                ex = new CommonExcel(title, rowsName, dataList, resp, fileName);
                try {
                    ex.downloadExcel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "exportStudentWeekCountDetails":
                String studentid = req.getParameter("StudentID");
                list = exportDao.findStudentWeekCountDetails(studentid);

                // 定义标题（第一行）
                title = "考勤详情";

                // 定义列名（第二行）
                rowsName = new String[]{"学号","姓名","班级名称","周/节次","签到情况"};

                // 定义主题内容（第三行起）
                dataList = new ArrayList<Object[]>();

                // 定义每一行的临时变量，并放入数据
                objs = null;
                for (int i = 0; i < list.size(); i++) {
                    objs = new Object[rowsName.length];
                    objs[0] = list.get(i).getStudentid();
                    objs[1] = list.get(i).getStudentname();
                    objs[2] = list.get(i).getClassname();
                    objs[3] = list.get(i).getWeekcount();
                    objs[4] = list.get(i).getSigninsituation();
                    dataList.add(objs);
                }

                // 定义Excel文件名
                fileName= studentid + "考勤详情"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // 创建CommonExcel对象，调用downloadExcel()对象导出Excel
                ex = new CommonExcel(title, rowsName, dataList, resp, fileName);
                try {
                    ex.downloadExcel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "exportCollegeClass":
                String collegeclassid = req.getParameter("collegeclassid");
                list = exportDao.findCollegeClassWeekCountDetails(collegeclassid);

                // 定义标题（第一行）
                title = "考勤详情";

                // 定义列名（第二行）
                rowsName = new String[]{"学号","姓名","班级名称","周/节次","签到情况"};

                // 定义主题内容（第三行起）
                dataList = new ArrayList<Object[]>();

                // 定义每一行的临时变量，并放入数据
                objs = null;
                for (int i = 0; i < list.size(); i++) {
                    objs = new Object[rowsName.length];
                    objs[0] = list.get(i).getStudentid();
                    objs[1] = list.get(i).getStudentname();
                    objs[2] = list.get(i).getClassname();
                    objs[3] = list.get(i).getWeekcount();
                    objs[4] = list.get(i).getSigninsituation();
                    dataList.add(objs);
                }

                // 定义Excel文件名
                fileName = college+grade+"级"+collegeclassid+ "考勤详情"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // 创建CommonExcel对象，调用downloadExcel()对象导出Excel
                ex = new CommonExcel(title, rowsName, dataList, resp, fileName);
                try {
                    ex.downloadExcel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "exportHighWarningStudentGPA":
                list1 = exportDao.exportHighWarningStudentGPA(college, grade);

                // 定义标题（第一行）
                title = "绩点信息";

                // 定义列名（第二行）
                rowsName = new String[]{"学号","姓名","班级名称","绩点"};

                // 定义主题内容（第三行起）
                dataList = new ArrayList<Object[]>();

                // 定义每一行的临时变量，并放入数据
                objs = null;
                for (int i = 0; i < list1.size(); i++) {
                    objs = new Object[rowsName.length];
                    objs[0] = list1.get(i).getStudentid();
                    objs[1] = list1.get(i).getStudentname();
                    objs[2] = list1.get(i).getCollegeclassname();
                    objs[3] = list1.get(i).getGpa();
                    dataList.add(objs);
                }

                // 定义Excel文件名
                fileName = college+grade+"级高风险"+ "绩点详情"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // 创建CommonExcel对象，调用downloadExcel()对象导出Excel
                ex = new CommonExcel(title, rowsName, dataList, resp, fileName);
                try {
                    ex.downloadExcel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "exportMiddleWarningStudentGPA":
                list1 = exportDao.exportMiddleWarningStudentGPA(college, grade);

                // 定义标题（第一行）
                title = "绩点信息";

                // 定义列名（第二行）
                rowsName = new String[]{"学号","姓名","班级名称","绩点"};

                // 定义主题内容（第三行起）
                dataList = new ArrayList<Object[]>();

                // 定义每一行的临时变量，并放入数据
                objs = null;
                for (int i = 0; i < list1.size(); i++) {
                    objs = new Object[rowsName.length];
                    objs[0] = list1.get(i).getStudentid();
                    objs[1] = list1.get(i).getStudentname();
                    objs[2] = list1.get(i).getCollegeclassname();
                    objs[3] = list1.get(i).getGpa();
                    dataList.add(objs);
                }

                // 定义Excel文件名
                fileName = college+grade+"级中风险"+ "绩点详情"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // 创建CommonExcel对象，调用downloadExcel()对象导出Excel
                ex = new CommonExcel(title, rowsName, dataList, resp, fileName);
                try {
                    ex.downloadExcel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "exportLowWarningStudentGPA":
                list1 = exportDao.exportLowWarningStudentGPA(college, grade);

                // 定义标题（第一行）
                title = "绩点信息";

                // 定义列名（第二行）
                rowsName = new String[]{"学号","姓名","班级名称","绩点"};

                // 定义主题内容（第三行起）
                dataList = new ArrayList<Object[]>();

                // 定义每一行的临时变量，并放入数据
                objs = null;
                for (int i = 0; i < list1.size(); i++) {
                    objs = new Object[rowsName.length];
                    objs[0] = list1.get(i).getStudentid();
                    objs[1] = list1.get(i).getStudentname();
                    objs[2] = list1.get(i).getCollegeclassname();
                    objs[3] = list1.get(i).getGpa();
                    dataList.add(objs);
                }

                // 定义Excel文件名
                fileName = college+grade+"级无风险"+ "绩点详情"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // 创建CommonExcel对象，调用downloadExcel()对象导出Excel
                ex = new CommonExcel(title, rowsName, dataList, resp, fileName);
                try {
                    ex.downloadExcel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "exportAllGradeForLibrary":
                List<CollegeClassLibrary> listLibrary = null;
                listLibrary = exportDao.exportAllGradeForLibrary(college, grade);

                // 定义标题（第一行）
                title = "考勤详情";

                // 定义列名（第二行）
                rowsName = new String[]{"学号","姓名","班级名称","入馆数目"};

                // 定义主题内容（第三行起）
                dataList = new ArrayList<Object[]>();

                // 定义每一行的临时变量，并放入数据
                objs = null;
                for (int i = 0; i < listLibrary.size(); i++) {
                    objs = new Object[rowsName.length];
                    objs[0] = listLibrary.get(i).getStudentid();
                    objs[1] = listLibrary.get(i).getStudentname();
                    objs[2] = listLibrary.get(i).getCollegeclassname();
                    objs[3] = listLibrary.get(i).getCountlibrary();
                    dataList.add(objs);
                }

                // 定义Excel文件名
                fileName= college + grade + "入馆反馈详情"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // 创建CommonExcel对象，调用downloadExcel()对象导出Excel
                ex = new CommonExcel(title, rowsName, dataList, resp, fileName);
                try {
                    ex.downloadExcel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}