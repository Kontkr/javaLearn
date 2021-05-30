package com.learn.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.Data;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.util.DefaultTempFileCreationStrategy;
import org.apache.poi.util.TempFile;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelTest {

    @Test
    public void XSSFTest() throws IOException {
        XSSFWorkbook workBook = new XSSFWorkbook();
        //创建页签
        XSSFSheet sheet = workBook.createSheet("sheet");
        for (int i = 0; i < 7056; i++) {
            //创建行
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < 30; j++) {
                //创建单元格
                row.createCell(j).setCellValue(i + "" + j);
            }
        }
        File file = FileUtil.createFile("F:\\Temporary\\excel\\", ".xlsx");
        workBook.write(new FileOutputStream(file));
        System.out.println("导出完成,file为：" + file.getName());
        while (true) {
        }
    }


    @Test
    public void SXXSFTest() throws Exception {
        long start = System.currentTimeMillis();
        File poiTempDir = new File("F:\\Temporary\\excel\\poitemp");
        if (!poiTempDir.exists()) {
            poiTempDir.mkdir();
        }
        TempFile.setTempFileCreationStrategy(new DefaultTempFileCreationStrategy(poiTempDir));
        SXSSFWorkbook work = new SXSSFWorkbook(null, SXSSFWorkbook.DEFAULT_WINDOW_SIZE, true);
        SXSSFSheet sxxfSheet = work.createSheet("sxxfSheet");
        int maxRow = SpreadsheetVersion.EXCEL2007.getLastRowIndex();
        int maxCol = SpreadsheetVersion.EXCEL2007.getLastColumnIndex();
        for (int i = 0; i < maxRow; i++) {
            //创建行
            SXSSFRow row = sxxfSheet.createRow(i);
            for (int j = 0; j < 30; j++) {
                //创建单元格
                row.createCell(j).setCellValue(i + "" + j);
            }
        }
        System.out.println("写入完成,file为：");
        File file = FileUtil.createFile("F:\\Temporary\\excel\\", ".xlsx");
        work.write(new FileOutputStream(file));
        FileInputStream worksheetXMLInputStream = (FileInputStream) sxxfSheet.getWorksheetXMLInputStream();
        FileDescriptor fd = worksheetXMLInputStream.getFD();
        System.out.println("导出完成,file为：" + file.getName());
        System.out.println("导出耗时：" + (System.currentTimeMillis() - start));
        while (true) {
        }
//        while(true){}

    }


    @Test
    public void EasyExcelTest() throws Exception {
        String esayDir = "F:\\Temporary\\esayExcel\\";
        File file = FileUtil.createFile(esayDir, ".xlsx");

        //创建写实例
        ExcelWriter ew = EasyExcel.write(file).build();

        //创建页签
        WriteSheet sheet0 = EasyExcel.writerSheet(0, "sheet0").head(DemoData.class).build();

        //写入到 Sheet
//        ew.write(getVOBody(300000), sheet0);

        ew.write(getCustomBody(30000,10), sheet0);

        ew.finish();

    }

    public List<List<Object>> getCustomBody(int rowNum, int colNum) {
        List<List<Object>> rows = new ArrayList<>();
        for (int i = 0; i < rowNum; i++) {
            List<Object> row = new ArrayList<>();
            for (int j = 0; j < colNum; j++) {
                row.add(i + "" + j);
            }
            rows.add(row);
        }
        return rows;
    }


    public List<DemoData> getVOBody(int rowNum) {
        List<DemoData> rows = new ArrayList<>();
        for (int i = 0; i < rowNum; i++) {
            DemoData row = new DemoData();
            row.setDate(new Date());
            row.setString("String" + i + "2");
            row.setDoubleData(Double.valueOf(i + "3"));
            rows.add(row);
        }

        return rows;
    }

    @Data
    private static class DemoData {
        @ExcelProperty("字符串标题")
        private String string;
        @ExcelProperty("日期标题")
        private Date date;
        @ExcelProperty("数字标题")
        private Double doubleData;
        /**
         * 忽略这个字段
         */
        @ExcelIgnore
        private String ignore;
    }

    public static void main(String[] args) {
        System.getProperty("java.io.tmpdir");
        System.out.println(SpreadsheetVersion.EXCEL2007.getLastRowIndex());
        System.out.println(SpreadsheetVersion.EXCEL2007.getLastColumnIndex());
    }

}
