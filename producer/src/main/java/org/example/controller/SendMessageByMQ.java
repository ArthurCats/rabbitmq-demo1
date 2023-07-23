package org.example.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author Zheng
 * @CreateTime 2023/7/23 14:10
 */
@RestController
@RequestMapping("/mq")
public class SendMessageByMQ {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 1. rabbitmq -- simple mode 简单模式
     *
     * @param msg 消息
     * @return 执行结果
     */
    @GetMapping("/test")
    public String send(@RequestParam String msg){
        rabbitTemplate.convertAndSend("","boot_queue_1",msg);
        return msg + "发送成功";
    }


    /**
     * 2. rabbitmq -- work mode 工作模式
     *
     * @param msg 消息
     * @return 执行结果
     */
    @GetMapping("/test2")
    public String send2(@RequestParam String msg){
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend("","boot_queue_work",msg);
        }
        return msg + " ===> 发送成功";
    }

    /**
     * 3. rabbitmq -- pub / sub mode 发布/订阅者模式
     *
     * @param msg 消息
     * @return 执行结果
     */
    @GetMapping("/test3")
    public String send3(@RequestParam String msg){
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend("boot_exchange_pub","",msg);
        }
        return msg + " ===> 发送成功";
    }

    /**
     * 4. rabbitmq -- routing mode 路由模式
     *
     * @param msg 消息
     * @return 执行结果
     */
    @GetMapping("/test4")
    public String send4(@RequestParam String msg,
                        @RequestParam String key){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("boot_exchange_routing",key,msg);
        }
        return msg + " ===> 发送成功";
    }

    /**
     * 5. rabbitmq -- topic mode 主题模式
     *
     * @param msg 消息
     * @return 执行结果
     */
    @GetMapping("/test5")
    public String send5(@RequestParam String msg,
                        @RequestParam String key){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("boot_exchange_topic",key,msg);
        }
        return msg + " ===> 发送成功";
    }
}
