package com.learn.direct;

import com.learn.tools.RabbitMQUtil;
import com.learn.tools.ThreadUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class DirectProducer {


    /**
     * 手动声明交换机，队列，并进行绑定
     *
     * @param num
     */
    private void run(int num) {
        for (int i = 0; i < num; i++) {

        }
    }


    /**
     * 交换机与队列已经在图形化界面中创建好了，绑定关系也已经确定好，此方法可以直接发送
     *
     * @param num
     */
    private void runExist(int num) {
        for (int i = 0; i < num; i++) {
            ThreadUtil.getPool().submit(new Runnable() {
                @Override
                public void run() {
                    ConnectionFactory factory = RabbitMQUtil.buildConnFactory();
                    try (Connection connection = factory.newConnection();
                         final Channel channel = connection.createChannel()) {
                        String exchangeName = "direct_exchange";
                        String routeKey1 = "direct_queue1_key";
                        String routeKey2 = "direct_queue2_key";
                        channel.basicPublish(exchangeName, routeKey1, null, ("direct Message 1" + Thread.currentThread().getName()).getBytes());
                        channel.basicPublish(exchangeName, routeKey2, null, ("direct Message 2" + Thread.currentThread().getName()).getBytes());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) {
        DirectProducer producer = new DirectProducer();
        producer.runExist(5);
    }
}
