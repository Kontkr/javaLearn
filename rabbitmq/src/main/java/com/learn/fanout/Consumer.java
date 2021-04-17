package com.learn.fanout;

import com.learn.tools.RabbitMQUtil;
import com.learn.tools.ThreadUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Consumer {

    private void run(int num) {
        for (int i = 0; i < num; i++) {
            ThreadUtil.getPool().submit(new Runnable() {
                @Override
                public void run() {
                    ConnectionFactory factory = RabbitMQUtil.buildConnFactory();
                    try (Connection connection = factory.newConnection("fanout Connection");
                         Channel channel = connection.createChannel()
                    ) {
                     channel.
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        new Consumer().run(2);
    }
}
