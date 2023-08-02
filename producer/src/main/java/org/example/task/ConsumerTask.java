package org.example.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author Zheng
 * @CreateTime 2023/8/2 19:58
 */
@Slf4j
@Service
public class ConsumerTask {

/*    @RabbitListener(queues = "task_queue1")
    public void consumerTask(String message){
        log.error("接收到的消息是：{}",message);
    }*/

}
