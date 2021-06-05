package com.learn.nio.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@RestController
public class DownLoadCOntroller {


    @RequestMapping("/download")
    public void download(HttpServletResponse response) throws Exception {
        File file = new File("F:\\Document\\ISO\\CentOS-8-x86_64-1905-dvd1.iso");
        System.out.println("file size:" + file.length());
        BufferedInputStream bufferIn = new BufferedInputStream(new FileInputStream(file));

        ServletOutputStream outputStream = response.getOutputStream();

        BufferedOutputStream bufferOut = new BufferedOutputStream(outputStream);

        setResponseHeader(response, "CentOS-8-x86_64-1905-dvd1.iso");

        System.out.println("response header setting end!");

        byte[] row = new byte[4096];
        int count = bufferIn.read(row);
        while (count > 0) {
            bufferOut.write(row, 0, count);
            count = bufferIn.read(row);
        }
        bufferOut.flush();
        System.out.println("写入 repsone 完成");
    }

    private void setResponseHeader(HttpServletResponse response, String fileName) {
        response.setContentType("text/html; charset=ISO-8859-1");
        try {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            fileName = fileName.replaceAll("\\+", "%20");
        } catch (Exception ex) {
        }
        response.setHeader("Content-disposition", "attachment;filename*=utf-8'zh_cn'" + fileName);
    }
}
