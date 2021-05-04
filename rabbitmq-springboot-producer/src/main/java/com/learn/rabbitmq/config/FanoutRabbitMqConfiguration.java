package com.learn.rabbitmq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitMqConfiguration {

    //1.声明队列
    @Bean
    public FanoutExchange getFanoutExchange(){
        return  new FanoutExchange("fanout_exchange_boot",true,false);
    }

    //2.声明队列
    @Bean
    public Queue getQueue(){
        return new Queue("email_fanout_queue_boot",true);
    }

    //3.完成绑定
    @Bean
    public Binding binding(){
        return  BindingBuilder.bind(getQueue()).to(getFanoutExchange());
    }
}
