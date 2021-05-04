package com.learn.rabbitmq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 死信交换机与死信队列
 */
@Configuration
public class DeadRabbitMqConfiguration {
    @Bean
    public DirectExchange getDeadExchange(){
        return new DirectExchange("dead_direct_exchange_boot",true,false);
    }
    @Bean
    public Queue getDeadQueue(){
        return  new Queue("dead_direct_queue_boot",true);
    }
    @Bean
    public Binding deadBinding(){
        return BindingBuilder.bind(getDeadQueue()).to(getDeadExchange()).with("dead");
    }
}
