package com.learn.dubbo.producer.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * xml 配置方式
 */

public class ProducerTest {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        context.start();
        System.out.println("dubbo producer 启动成功！");
        System.in.read();
    }
}
