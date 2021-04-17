package com.learn.fanout;

import com.learn.tools.RabbitMQUtil;
import com.learn.tools.ThreadUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {

    private void run(String queueName) {
        ThreadUtil.getPool().submit(new Runnable() {
            @Override
            public void run() {
                ConnectionFactory factory = RabbitMQUtil.buildConnFactory();
                try (Connection connection = factory.newConnection("fanout Connection");
                     Channel channel = connection.createChannel()
                ) {
                    channel.basicConsume(queueName, true, new DeliverCallback() {
                        @Override
                        public void handle(String consumerTag, Delivery delivery) throws IOException {
                            String message = new String(delivery.getBody(), "UTF-8");
                            System.out.println("消费了队列" + queueName + " 的消息:" + message);
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
        Consumer consumer = new Consumer();
        consumer.run("fanout_queue1");
        consumer.run("fanout_queue2");
    }
}
