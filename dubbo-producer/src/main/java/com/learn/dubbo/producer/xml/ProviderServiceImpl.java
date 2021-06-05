package com.learn.dubbo.producer.xml;

public class ProviderServiceImpl implements IProviderService {

    @Override
    public Object process(Object obj) {
        System.out.println(" start process " + obj.toString());
        return null;
    }
}
