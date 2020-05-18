package com.system.util;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class CommonExcel {

    // ��ʾ�ĵ���Excel��ı��⣨ͨ����һ�У�
    private String title;
    // ����Excel���������ͨ���ڶ��У�
    private String[] rowName;
    // ����Excel����ļ���
    private String fileName;

    // ����Excel����ļ��������ݣ�ͨ���ӵ��������µ����ݣ�
    // ��Ҫע����ǣ���Ҫ���ݵĸ�ʽΪ��һ��Ϊ���֣���ţ����ӵڶ��п�ʼ�����ַ���
    // ÿһ��ΪObject[]���ͣ��ܶ�����List��װ����
    private List<Object[]> dataList = new ArrayList<Object[]>();

    // �������ص�response
    private HttpServletResponse response;

    // ���췽��������Ҫ����������
    public CommonExcel(String title, String[] rowName, List<Object[]> dataList, HttpServletResponse response, String fileName) {
        this.dataList = dataList;
        this.rowName = rowName;
        this.title = title;
        this.response = response;
        this.fileName = fileName;
    }

    // ����Excel�ķ���
    public void downloadExcel() throws Exception {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();                     // ��������������
            HSSFSheet sheet = workbook.createSheet(title);                  // ����������

            // ������������
            HSSFRow rowm = sheet.createRow(0);
            HSSFCell cellTiltle = rowm.createCell(0);

            // sheet��ʽ���壨����������Ϊ�Զ��壬���������޸ģ�
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//��ȡ��ͷ��ʽ����
            HSSFCellStyle style = this.getStyle(workbook);                  //��Ԫ����ʽ����

            //sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
            //cellTiltle.setCellStyle(columnTopStyle);
            //cellTiltle.setCellValue(title);

            // ������������
            int columnNum = rowName.length;
            HSSFRow rowRowName = sheet.createRow(0);                // ������2��λ�ô�����(��˵��п�ʼ�ĵڶ���)

            // ����ͷ���õ�sheet�ĵ�Ԫ����
            for (int n = 0; n < columnNum; n++) {
                HSSFCell cellRowName = rowRowName.createCell(n);               //������ͷ��Ӧ�����ĵ�Ԫ��
                cellRowName.setCellType(CellType.STRING);             //������ͷ��Ԫ�����������
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
                cellRowName.setCellValue(text);                                 //������ͷ��Ԫ���ֵ
                cellRowName.setCellStyle(columnTopStyle);                       //������ͷ��Ԫ����ʽ
            }

            // ����ѯ�����������õ�sheet��Ӧ�ĵ�Ԫ����
            for (int i = 0; i < dataList.size(); i++) {

                Object[] obj = dataList.get(i);					//����ÿ������
                HSSFRow row = sheet.createRow(i + 1);			//�������������

                for (int j = 0; j < obj.length; j++) {
                    HSSFCell cell = null;   					//���õ�Ԫ�����������
                    //if (j == 0) {
                    //cell = row.createCell(j, CellType.NUMERIC);		//��һ��Ϊ����
                    //cell.setCellValue(i + 1);
                    //} else {
                    cell = row.createCell(j, CellType.STRING);		//�ڶ�����Ϊ�ַ���
                    if (!"".equals(obj[j]) && obj[j] != null) {
                        cell.setCellValue(obj[j].toString());                       //���õ�Ԫ���ֵ
                    }
                    //}
                    cell.setCellStyle(style);                                   //���õ�Ԫ����ʽ
                }
            }
            //���п����ŵ������г��Զ���Ӧ
            for (int colNum = 0; colNum < columnNum; colNum++) {
                int columnWidth = sheet.getColumnWidth(colNum) / 256;
                for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                    HSSFRow currentRow;
                    //��ǰ��δ��ʹ�ù�
                    if (sheet.getRow(rowNum) == null) {
                        currentRow = sheet.createRow(rowNum);
                    } else {
                        currentRow = sheet.getRow(rowNum);
                    }
                    if (currentRow.getCell(colNum) != null) {
                        HSSFCell currentCell = currentRow.getCell(colNum);
                        if (currentCell.getCellType() == CellType.STRING) {
                            int length = currentCell.getStringCellValue().getBytes().length;
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
                if (colNum == 0) {
                    sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
                } else {
                    sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
                }
            }

            if (workbook != null) {
                try {
                    if (response != null) {
                        response.setContentType("application/vnd.ms-excel;charset=utf-8");
                        response.setHeader("Content-Disposition", "attachment;filename=\""+new String(fileName.getBytes("gb2312"),"ISO8859-1"));
                        OutputStream out = response.getOutputStream();
                        workbook.write(out);
                        out.close();
                    } else {
                        FileOutputStream outputStream = new FileOutputStream("C:/"+fileName);
                        workbook.write(outputStream);
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ��ͷ��Ԫ����ʽ
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

        // ��������
        HSSFFont font = workbook.createFont();
        //���������С
        font.setFontHeightInPoints((short) 12);
        //����Ӵ�
        font.setBold(true);
        //������������
        font.setFontName("΢���ź�");
        //������ʽ;
        HSSFCellStyle style = workbook.createCellStyle();
        //���õױ߿�;
        //style.setBorderBottom(CellStyle.BORDER_THIN);
        //���õױ߿���ɫ;
        //style.setBottomBorderColor(HSSFColor.BLACK.index);
        //������߿�;
        //style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //������߿���ɫ;
        //style.setLeftBorderColor(HSSFColor.BLACK.index);
        //�����ұ߿�;
        //style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //�����ұ߿���ɫ;
        //style.setRightBorderColor(HSSFColor.BLACK.index);
        //���ö��߿�;
        //style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //���ö��߿���ɫ;
        //style.setTopBorderColor(HSSFColor.BLACK.index);
        //����ʽ��Ӧ�����õ�����;
        style.setFont(font);
        //�����Զ�����;
        style.setWrapText(false);
        //����ˮƽ�������ʽΪ���ж���;
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //���ô�ֱ�������ʽΪ���ж���;
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        return style;

    }

    // ��������Ϣ��Ԫ����ʽ
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // ��������
        HSSFFont font = workbook.createFont();
        //������������
        font.setFontName("΢���ź�");
        //������ʽ;
        HSSFCellStyle style = workbook.createCellStyle();
        //���õױ߿�;
        //style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //���õױ߿���ɫ;
        //style.setBottomBorderColor(HSSFColor.BLACK.index);
        //������߿�;
        //style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //������߿���ɫ;
        //style.setLeftBorderColor(HSSFColor.BLACK.index);
        //�����ұ߿�;
        //style.setBorderRight(CellStyle.BORDER_THIN);
        //�����ұ߿���ɫ;
        //style.setRightBorderColor(HSSFColor.BLACK.index);
        //���ö��߿�;
        //style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //���ö��߿���ɫ;
        //style.setTopBorderColor(HSSFColor.BLACK.index);
        //����ʽ��Ӧ�����õ�����;
        style.setFont(font);
        //�����Զ�����;
        style.setWrapText(false);
        //����ˮƽ�������ʽΪ���ж���;
        //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //���ô�ֱ�������ʽΪ���ж���;
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }
}