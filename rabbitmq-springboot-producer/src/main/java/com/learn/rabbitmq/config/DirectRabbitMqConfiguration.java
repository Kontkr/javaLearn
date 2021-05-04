package com.learn.rabbitmq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitMqConfiguration {


    @Bean
    public DirectExchange getDirectExchange(){
        return new DirectExchange("direct_exchange_boot",true,false);
    }

    @Bean
    public Queue getDirectQueue(){
        return new Queue("email_direct_queue_boot",true);
    }

    @Bean
    public Binding directBinding(){
        return BindingBuilder.bind(getDirectQueue()).to(getDirectExchange()).with("email.queue");
    }
}
