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
 * ttl过期时间的配置 整个队列中的消息
 */
@Configuration
public class TtlRabbitMqConfiguration {

    @Bean
    public DirectExchange getTtlExchange(){
        return new DirectExchange("ttl_direct_exchange_boot",true,false);
    }

    @Bean
    public Queue getTtlQueue(){
        Map<String,Object> argsMap = new HashMap<>();
        //设置队列中消息的过期时间，如果超过这个时间没有消费，则丢弃
        argsMap.put("x-message-ttl",5000);
        //配置死信交换机与Key
        argsMap.put("x-dead-letter-exchange","dead_direct_exchange_boot");
        argsMap.put("x-dead-letter-routing-key","dead");
        return new Queue("ttl_direct_queue_boot",true,false,false,argsMap);
    }

    @Bean
    public Binding ttlBinding(){
        return BindingBuilder.bind(getTtlQueue()).to(getTtlExchange()).with("ttl");
    }

}
