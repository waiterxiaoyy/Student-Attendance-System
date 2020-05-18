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
                // Dao���ȡ���ݿ������
                String weekcountid = req.getParameter("WeekCountID");
                list = exportDao.findWeekCountDetails(weekcountid);

                // ������⣨��һ�У�
                String title = "��������";

                // �����������ڶ��У�
                String[] rowsName = new String[]{"ѧ��","����","�༶����","��/�ڴ�","ǩ�����","ǩ�����"};

                // �����������ݣ���������
                List<Object[]> dataList = new ArrayList<Object[]>();

                // ����ÿһ�е���ʱ����������������
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

                // ����Excel�ļ���
                String fileName="��������"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // ����CommonExcel���󣬵���downloadExcel()���󵼳�Excel
                CommonExcel ex = new CommonExcel(title, rowsName, dataList, resp, fileName);
                try {
                    ex.downloadExcel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "exportAllGrade":
                list = exportDao.findAllGradeWeekCountDetails(college, grade);

                // ������⣨��һ�У�
                title = "��������";

                // �����������ڶ��У�
                rowsName = new String[]{"ѧ��","����","�༶����","��/�ڴ�","ǩ�����"};

                // �����������ݣ���������
                dataList = new ArrayList<Object[]>();

                // ����ÿһ�е���ʱ����������������
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

                // ����Excel�ļ���
                fileName= college+ grade + "����������"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // ����CommonExcel���󣬵���downloadExcel()���󵼳�Excel
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

                // ������⣨��һ�У�
                title = "��������";

                // �����������ڶ��У�
                rowsName = new String[]{"ѧ��","����","�༶����","��/�ڴ�","ǩ�����"};

                // �����������ݣ���������
                dataList = new ArrayList<Object[]>();

                // ����ÿһ�е���ʱ����������������
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

                // ����Excel�ļ���
                fileName= studentid + "��������"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // ����CommonExcel���󣬵���downloadExcel()���󵼳�Excel
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

                // ������⣨��һ�У�
                title = "��������";

                // �����������ڶ��У�
                rowsName = new String[]{"ѧ��","����","�༶����","��/�ڴ�","ǩ�����"};

                // �����������ݣ���������
                dataList = new ArrayList<Object[]>();

                // ����ÿһ�е���ʱ����������������
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

                // ����Excel�ļ���
                fileName = college+grade+"��"+collegeclassid+ "��������"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // ����CommonExcel���󣬵���downloadExcel()���󵼳�Excel
                ex = new CommonExcel(title, rowsName, dataList, resp, fileName);
                try {
                    ex.downloadExcel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "exportHighWarningStudentGPA":
                list1 = exportDao.exportHighWarningStudentGPA(college, grade);

                // ������⣨��һ�У�
                title = "������Ϣ";

                // �����������ڶ��У�
                rowsName = new String[]{"ѧ��","����","�༶����","����"};

                // �����������ݣ���������
                dataList = new ArrayList<Object[]>();

                // ����ÿһ�е���ʱ����������������
                objs = null;
                for (int i = 0; i < list1.size(); i++) {
                    objs = new Object[rowsName.length];
                    objs[0] = list1.get(i).getStudentid();
                    objs[1] = list1.get(i).getStudentname();
                    objs[2] = list1.get(i).getCollegeclassname();
                    objs[3] = list1.get(i).getGpa();
                    dataList.add(objs);
                }

                // ����Excel�ļ���
                fileName = college+grade+"���߷���"+ "��������"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // ����CommonExcel���󣬵���downloadExcel()���󵼳�Excel
                ex = new CommonExcel(title, rowsName, dataList, resp, fileName);
                try {
                    ex.downloadExcel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "exportMiddleWarningStudentGPA":
                list1 = exportDao.exportMiddleWarningStudentGPA(college, grade);

                // ������⣨��һ�У�
                title = "������Ϣ";

                // �����������ڶ��У�
                rowsName = new String[]{"ѧ��","����","�༶����","����"};

                // �����������ݣ���������
                dataList = new ArrayList<Object[]>();

                // ����ÿһ�е���ʱ����������������
                objs = null;
                for (int i = 0; i < list1.size(); i++) {
                    objs = new Object[rowsName.length];
                    objs[0] = list1.get(i).getStudentid();
                    objs[1] = list1.get(i).getStudentname();
                    objs[2] = list1.get(i).getCollegeclassname();
                    objs[3] = list1.get(i).getGpa();
                    dataList.add(objs);
                }

                // ����Excel�ļ���
                fileName = college+grade+"���з���"+ "��������"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // ����CommonExcel���󣬵���downloadExcel()���󵼳�Excel
                ex = new CommonExcel(title, rowsName, dataList, resp, fileName);
                try {
                    ex.downloadExcel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "exportLowWarningStudentGPA":
                list1 = exportDao.exportLowWarningStudentGPA(college, grade);

                // ������⣨��һ�У�
                title = "������Ϣ";

                // �����������ڶ��У�
                rowsName = new String[]{"ѧ��","����","�༶����","����"};

                // �����������ݣ���������
                dataList = new ArrayList<Object[]>();

                // ����ÿһ�е���ʱ����������������
                objs = null;
                for (int i = 0; i < list1.size(); i++) {
                    objs = new Object[rowsName.length];
                    objs[0] = list1.get(i).getStudentid();
                    objs[1] = list1.get(i).getStudentname();
                    objs[2] = list1.get(i).getCollegeclassname();
                    objs[3] = list1.get(i).getGpa();
                    dataList.add(objs);
                }

                // ����Excel�ļ���
                fileName = college+grade+"���޷���"+ "��������"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // ����CommonExcel���󣬵���downloadExcel()���󵼳�Excel
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

                // ������⣨��һ�У�
                title = "��������";

                // �����������ڶ��У�
                rowsName = new String[]{"ѧ��","����","�༶����","�����Ŀ"};

                // �����������ݣ���������
                dataList = new ArrayList<Object[]>();

                // ����ÿһ�е���ʱ����������������
                objs = null;
                for (int i = 0; i < listLibrary.size(); i++) {
                    objs = new Object[rowsName.length];
                    objs[0] = listLibrary.get(i).getStudentid();
                    objs[1] = listLibrary.get(i).getStudentname();
                    objs[2] = listLibrary.get(i).getCollegeclassname();
                    objs[3] = listLibrary.get(i).getCountlibrary();
                    dataList.add(objs);
                }

                // ����Excel�ļ���
                fileName= college + grade + "��ݷ�������"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

                // ����CommonExcel���󣬵���downloadExcel()���󵼳�Excel
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