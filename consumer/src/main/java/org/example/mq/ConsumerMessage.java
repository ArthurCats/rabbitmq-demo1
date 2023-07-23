package org.example.mq;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Zheng
 * @CreateTime 2023/7/23 14:19
 */
@Service
public class ConsumerMessage {

    @RabbitListener(queuesToDeclare = @Queue("boot_queue_1"))
    public void consumerMsg(String msg){
        System.out.println("消息内容为："+msg);
    }


}
