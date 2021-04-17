package com.learn.fanout;

import com.learn.tools.RabbitMQUtil;
import com.learn.tools.ThreadUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;



public class Producer {

    private void run(int num) {
        for (int i = 0; i < num; i++) {
            ThreadUtil.getPool().submit(new Runnable() {
                @Override
                public void run() {
                    ConnectionFactory factory = RabbitMQUtil.buildConnFactory();
                    try (Connection connection = factory.newConnection("fanout Connection");
                         Channel channel = connection.createChannel()) {
                        String message = "hello fanout message" + Thread.currentThread().getName();
                        String exchangeName = "fanout_exchange";
                        channel.basicPublish(exchangeName, "", null, message.getBytes());
                        System.out.println("消息发送成功");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        new Producer().run(5);
    }
}
