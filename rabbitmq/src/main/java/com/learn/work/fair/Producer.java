package com.learn.work.fair;

import com.learn.tools.RabbitMQUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {


    private void run() {
        final ConnectionFactory factory = RabbitMQUtil.buildConnFactory();
        try (final Connection connection = factory.newConnection();
             final Channel channel = connection.createChannel();) {
//            String exchangeName = "work_exchange";
            String queueName = "default_work_queue";
            String exchangeType = "topic";
            //声明交换机
//            channel.exchangeDeclare(exchangeName, exchangeType, true);
            //声明队列
            channel.queueDeclare(queueName, true, false, false, null);
            //绑定队列
//            channel.queueBind(queueName, exchangeName, "*.work");
            //发送消息
            for (int i = 0; i < 20; i++)
                channel.basicPublish("", queueName, null, String.valueOf(i).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Producer().
                run();
    }
}
