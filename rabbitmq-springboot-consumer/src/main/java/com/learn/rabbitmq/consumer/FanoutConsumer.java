package com.learn.rabbitmq.consumer;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {"email_fanout_queue_boot"})
public class FanoutConsumer {

    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("email fanout 接收到的订单消息为-->"+message);
    }

}
