package com.learn.dubbo.producer.api;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

public class AppApi {

    public static void main(String[] args) {

        //当前应用配置
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("producer-api");

        //配置注册中心
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("dubbo://127.0.0.1:2181");

        //配置服务提供者协议
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20881);

        // 服务提供者暴露服务配置
        ServiceConfig<IProducerService2> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setInterface(IProducerService2.class);
        serviceConfig.setRef(new ProducerService2Impl());

        serviceConfig.export();

        System.out.println(" producer-api 启动成功！");
    }

}
