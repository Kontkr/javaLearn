package com.learn.simple;

import com.rabbitmq.client.*;

import java.io.IOException;

public class SimpleConsumer {

    public static void main(String[] args) {
        //1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        Connection connection = null;
        Channel channel = null;
        try {
            //2.创建连接ReentrantReadWriteLock
            connection = factory.newConnection("simple_Connection");
            //3.创建通道
            channel = connection.createChannel();
            //4.通过通道创建交换机，队列，绑定Key，路由Key
            String queueName = "queue_simple";
            //5.接收消息，消费消息
            channel.basicConsume(queueName, true, new DeliverCallback() {
                @Override
                public void handle(String s, Delivery delivery) throws IOException {
                    System.out.println("收到消息为：" + new String(delivery.getBody(), "UTF-8"));
                }
            }, new CancelCallback() {
                @Override
                public void handle(String s) throws IOException {
                    System.out.println("接收消息失败:" + s);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Exception e = null;
            //7.关闭通道
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    e = ex;
                }
            }
            //8.关闭连接
            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    e = ex;
                }
            }
            if (e != null)
                e.printStackTrace();

        }
    }
}
