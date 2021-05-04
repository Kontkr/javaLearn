package com.learn.direct;

import com.learn.tools.RabbitMQUtil;
import com.learn.tools.ThreadUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class DirectComsumer {

    private void runExist(String queueName) {
        ThreadUtil.getPool().submit(new Runnable() {
            @Override
            public void run() {
                final ConnectionFactory factory = RabbitMQUtil.buildConnFactory();
                try (final Connection connection = factory.newConnection();
                     final Channel channel = connection.createChannel();
                ) {
                    channel.basicConsume(queueName, true, new DeliverCallback() {
                        @Override
                        public void handle(String consumerTag, Delivery delivery) throws IOException {
                            String message = new String(delivery.getBody(), "UTF-8");
                            System.out.println("消费了队列" + queueName + "的消息" + message);
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
        final DirectComsumer comsumer = new DirectComsumer();
        comsumer.runExist("direct_queue1");
        comsumer.runExist("direct_queue2");
    }
}
