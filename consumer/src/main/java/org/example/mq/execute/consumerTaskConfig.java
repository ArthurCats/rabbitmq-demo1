package org.example.mq.execute;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.policy.SimpleRetryPolicy;

/**
 * @Description
 * @Author Zheng
 * @CreateTime 2023/8/2 20:34
 */
@Configuration
public class consumerTaskConfig {

    /*
    声明队列、交换机、绑定关系（routing-key）
     */
    private static String QUEUE_NAME = "task_queue2";
    private static String EXCHANGE_NAME = "exchange_01";

    /*
    声明队列
     */
    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME,true,false,false);
    }
    /*
    声明交换机
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE_NAME,true,false);
    }
    /*
    声明绑定关系
     */
    @Bean
    public Binding queueBinding(Queue queue, TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("task_routing2");
    }

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    /*    rabbitTemplate.setReplyTimeout(80000);
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(5);*/
        return rabbitTemplate;
    }
}
