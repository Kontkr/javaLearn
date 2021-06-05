package com.learn.dubbo.producer.api;

public class ProducerService2Impl implements  IProducerService2 {


    @Override
    public Object process(Object message) {
        System.out.println(" process appapi "+message.toString());
        return null;
    }
}
