package com.learn.rabbitmq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * ttl过期时间的配置 队列中一条消息，在发送消息的时候设置
 */
@Configuration
public class TtlMessageRabbitMqConfiguration {

    @Bean
    public DirectExchange getTtlMessageExchange(){
        return new DirectExchange("ttlmessage_direct_exchange_boot",true,false);
    }


    @Bean
    public Queue getTtlQueueMessage(){//普通的消息队列，测试  设置单独的一条消息过期时间
        return new Queue("ttl_message_direct_queue_boot",true);
    }


    @Bean
    public Binding ttlMessageBinding(){
        return BindingBuilder.bind(getTtlQueueMessage()).to(getTtlMessageExchange()).with("ttl.message");
    }
}
