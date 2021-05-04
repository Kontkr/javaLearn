package com.learn.rabbitmq;

import com.learn.rabbitmq.service.IOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqProducerApplicationTest {

    @Autowired
    IOrderService orderService;

    @Test
    void contextLoads() {
        orderService.makeOrderFanout("1","1",3);
    }

    @Test
    void startDirect(){
        orderService.makeOrderDirect("1","1",4);
    }

    @Test
    void startTopic(){
        orderService.makeOrderTopic();
    }

    @Test
    void startTtlQueue(){
        for(int i = 0;i<10;i++)
        orderService.makeOrderTtlQueue();}


    @Test
    void startTtlQueueMessage(){orderService.makeOrderTtlMessage();}
}
