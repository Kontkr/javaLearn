package com.learn.tools;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtil {




    public static ConnectionFactory buildConnFactory(){
      return buildConnFactory("127.0.0.1",5672,"admin","admin","/");
    }

    public static ConnectionFactory buildConnFactory(String host,int port,String userName,String passWord,String virtualHost){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(userName);
        factory.setPassword(passWord);
        factory.setVirtualHost(virtualHost);
        return factory;
    }


}
