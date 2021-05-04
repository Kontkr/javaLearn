package com.learn.work.polling;

import com.learn.tools.RabbitMQUtil;
import com.learn.tools.ThreadUtil;
import com.rabbitmq.client.*;

import java.io.IOException;


/**
 * 默认为轮询分发
 */
public class Consumer1 {

    private void run() {
        ThreadUtil.getPool().submit(new Runnable() {
            @Override
            public void run() {
                final ConnectionFactory factory =
                        RabbitMQUtil.buildConnFactory();
                try (final Connection connection = factory.newConnection();
                     final Channel channel = connection.createChannel();) {
                    String queueName = "work_queue";
                    channel.basicConsume(queueName, true, new DeliverCallback() {
                        @Override
                        public void handle(String consumerTag, Delivery delivery) throws IOException {
                            String message = new String(delivery.getBody(), "UTF-8");
                            System.out.println("消費者1消費了" + message);
                        }
                    }, new CancelCallback() {
                        @Override
                        public void handle(String consumerTag) throws IOException {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) {
        new Consumer1().run();
    }
}
