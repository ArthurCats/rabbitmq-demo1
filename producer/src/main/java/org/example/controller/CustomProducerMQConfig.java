package org.example.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author Zheng
 * @CreateTime 2023/8/2 20:57
 */
@Configuration
public class CustomProducerMQConfig {
    /*
    声明交换机
     */
    private static String EXCHANGE_NAME = "exchange_01";
    /*
    声明交换机
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE_NAME,true,false);
    }
}
