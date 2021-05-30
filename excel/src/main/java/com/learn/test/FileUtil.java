package com.learn.test;

import org.apache.poi.ss.SpreadsheetVersion;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * @author zhujh9
 * @describe
 * @createdate 2021-01-12 下午2:33:24
 **/
public class FileUtil {

    public static File createFile(String dirPath, String fileType) throws IOException {
        String fileName = UUID.randomUUID().toString();
        return createFile(dirPath, fileName, fileType);
    }

    public static File createFile(String dirPath, String fileName, String fileType) throws IOException {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        fileName += fileType;
        File file = new File(dirPath + fileName);
        boolean exists = file.exists();
        // 如果此文件存在再次生成
        while (exists) {
            fileName = UUID.randomUUID().toString() + fileType;
            file = new File(dirPath + fileName);
            exists = file.exists();
        }
        file.createNewFile();
        return file;
    }

    public static boolean deleteFile(File file) {
        boolean delete = false;
        if (file != null) {
            delete = file.delete();
            if (!delete) {
                String absolutePath = file.getAbsolutePath();
                String message = "文件：" + absolutePath + "删除失败！";
            }
        }
        return delete;
    }

    public static void main(String[] args) {
        System.getProperty("java.io.tmpdir");
        System.out.println( SpreadsheetVersion.EXCEL2007.getLastRowIndex());
        System.out.println( SpreadsheetVersion.EXCEL2007.getLastColumnIndex());
    }
}
