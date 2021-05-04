package com.learn.topic;

import com.learn.tools.RabbitMQUtil;
import com.learn.tools.ThreadUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class TopicProducer {

    private void runExist(int num) {
        for (int i = 0; i < num; i++) {
            ThreadUtil.getPool().submit(new Runnable() {
                @Override
                public void run() {
                    final ConnectionFactory factory = RabbitMQUtil.buildConnFactory();
                    try (final Connection connection = factory.newConnection(); final Channel channel = connection.createChannel();
                    ) {
                        String exchangeName = "topic_exchange";
                        String routeKey1 = "aa.topic";
                        String routeKey2 = "aa.bb.topic";
                        channel.basicPublish(exchangeName, routeKey1, null, ("topic message 1 " + Thread.currentThread().getName()).getBytes());
                        channel.basicPublish(exchangeName, routeKey2, null, ("topic message 2 " + Thread.currentThread().getName()).getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public static void main(String[] args) {
        final TopicProducer producer = new TopicProducer();
        producer.runExist(5);
    }

}
