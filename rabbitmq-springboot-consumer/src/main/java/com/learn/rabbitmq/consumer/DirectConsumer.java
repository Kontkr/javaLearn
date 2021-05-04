package com.learn.rabbitmq.consumer;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener( queues = {"email_direct_queue_boot"})
public class DirectConsumer {

    @RabbitHandler
    public  void reviceMessage(String message){
        System.out.println("direct 接收到的订单消息Id为->"+message);
    }

}
