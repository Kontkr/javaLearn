package com.learn.work.fair;

import com.learn.tools.RabbitMQUtil;
import com.learn.tools.ThreadUtil;
import com.rabbitmq.client.*;

import java.io.IOException;


/**
 * 设置为公平分发，根据每一个消费者的处理能力来给消费者分发任务
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
                    String queueName = "default_work_queue";
                    channel.basicQos(2);//每次从队列中取出的数据量
                    Channel newChannel = channel;
                    channel.basicConsume(queueName, false, new DeliverCallback() {
                        @Override
                        public void handle(String consumerTag, Delivery delivery) throws IOException {
                            String message = new String(delivery.getBody(), "UTF-8");
                            System.out.println("消費者1消費了" + message);
                            //手动 Ack
                            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
//                            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                        }
                    }, new CancelCallback() {
                        @Override
                        public void handle(String consumerTag) throws IOException {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    System.in.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) {
        new Consumer1().run();
    }
}
