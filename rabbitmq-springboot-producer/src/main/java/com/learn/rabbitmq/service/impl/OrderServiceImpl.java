package com.learn.rabbitmq.service.impl;

import com.learn.rabbitmq.service.IOrderService;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements IOrderService {

     @Autowired
     private RabbitTemplate rabbitTemplate;

     /**
      * 用户下单(fanout模式)
      * @param userId 用户主键
      * @param goodId 商品主键
      * @param num 数量
      * @return
      */
     @Override
     public Object makeOrderFanout(String userId,String goodId,int num){
          //1.查询水平库存
          //2.保存订单
           String orderId = UUID.randomUUID().toString();
          //3.通过Rabbit分发消息
          String exchangeName = "fanout_exchange_boot";
          String routingKey = "";
          rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
          return null;
     }


     /**
      * 发送 Direct模式的消息
      * @param userId
      * @param goodId
      * @param num
      * @return
      */
     @Override
     public Object makeOrderDirect(String userId,String goodId,int num){
          String orderId = UUID.randomUUID().toString();
          String exchangeName = "direct_exchange_boot";
          String routingKey = "email.queue";
          rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
          return null;
     }


     /**
      * 主题模式
      * @return
      */
     @Override
     public Object makeOrderTopic(){
          String orderId = UUID.randomUUID().toString();
          String exchangeName = "topic_exchange_boot_annotation";
          String routingKey = "email.topic";
          rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
          return null;
     }


     /**
      * ttl 过期时间 队列
      * 设置了过期时间的队列，消息过期之后，可以写入到死信队列中
      * @return
      */
     @Override
     public Object makeOrderTtlQueue(){
          String orderId = UUID.randomUUID().toString();
          String exchangeName = "ttl_direct_exchange_boot";
          String routingKey = "ttl";
          rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
          return null;
     }


     /**
      * ttl 过期时间 单独的一条消息
      *  直接移除，不会转到死信队列中
      * @return
      */
     @Override
     public Object makeOrderTtlMessage(){
          String orderId = UUID.randomUUID().toString();
          String exchangeName = "ttlmessage_direct_exchange_boot";
          String routingKey = "ttl.message";

          MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
               @Override
               public Message postProcessMessage(Message message) throws AmqpException {
                    //设置这条消息的过期时间
                    message.getMessageProperties().setExpiration("5000");
                    //设置这条消息的编码
                    message.getMessageProperties().setContentEncoding("UTF-8");
                    return message;
               }
          };

          rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId,messagePostProcessor);
          return null;
     }
}
