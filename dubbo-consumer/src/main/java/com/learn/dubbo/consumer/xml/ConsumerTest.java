package com.learn.dubbo.consumer.xml;

import com.learn.dubbo.producer.xml.IProviderService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerTest {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        context.start();
        System.out.println(" dubbo consumer 启动成功！");
        IProviderService providerService = (IProviderService) context.getBean("providerService");
        providerService.process(" dubbo " + System.currentTimeMillis());
    }
}
