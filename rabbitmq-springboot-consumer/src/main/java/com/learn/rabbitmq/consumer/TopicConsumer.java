package com.learn.rabbitmq.consumer;


import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
//通过注解声明队列，交换机，并设置Key
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "topic_queue_boot_annotation", declare = "true",autoDelete = "false"),
        exchange = @Exchange(value = "topic_exchange_boot_annotation",type = ExchangeTypes.TOPIC),
        key = "#.topic"
))
public class TopicConsumer {

        @RabbitHandler
        public void reviceMessage(String message){
            System.out.println("topic 接收到的订单Id为->"+message);
        }

}
