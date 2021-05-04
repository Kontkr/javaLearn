package com.learn.simple;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 简单模式
 */
public class SimpleProducer {

    public static void main(String[] args) {
        //1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/");

        Connection connection = null;
        Channel channel = null;
        try {
            //2.创建连接
            connection = factory.newConnection("simple_Connection");
            //3.通过连接获取通道 Channel
            channel = connection.createChannel();
            //4.通过通道创建交换机，队列,绑定的Key，路由Key
            /**
             * param1:队列的名称
             * param2:是否要持久化 durable = true
             * param3:排他性，是否独占独立
             * param4:是否自动删除，当队列中没有消息时是否自动删除队列
             * param5:携带附属参数
             */
            String queueName = "default_queue";
            channel.queueDeclare(queueName, true, false, false, null);
            //5.准备消息内容
            String message = "hello simple";
            //6.发送消息给队列
            //虽然没有指定交换机，但是一定会默认存在一个交换机
            channel.basicPublish("", "queueName", null, message.getBytes());
            System.out.println("消息发送成功！");
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
