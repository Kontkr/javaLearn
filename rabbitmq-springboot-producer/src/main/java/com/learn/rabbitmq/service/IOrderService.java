package com.learn.rabbitmq.service;

public interface IOrderService {

    Object makeOrderFanout(String userId,String goodId,int num);

    Object makeOrderDirect(String userId,String goodId,int num);

    Object makeOrderTopic();
}
