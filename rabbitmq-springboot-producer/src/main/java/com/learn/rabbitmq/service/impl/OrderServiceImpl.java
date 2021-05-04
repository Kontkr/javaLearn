package com.learn.rabbitmq.service.impl;

import com.learn.rabbitmq.service.IOrderService;
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


     @Override
     public Object makeOrderTopic(){
          String orderId = UUID.randomUUID().toString();
          String exchangeName = "topic_exchange_boot_annotation";
          String routingKey = "email.topic";
          rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
          return null;
     }
}
