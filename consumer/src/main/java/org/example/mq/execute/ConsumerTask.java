package org.example.mq.execute;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Description
 * @Author Zheng
 * @CreateTime 2023/8/2 19:51
 */
@Service
@Slf4j
public class ConsumerTask {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "task_queue2")
    public void consumerTask(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        // 模拟发生异常，导致消息堆积
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException("模拟的延迟 6秒");
        }
        try{
            // 简单的消费，前端传过来的消息
            log.error("消费者接收到的消息是：{}",message.toString());
            byte[] body = message.getBody();
            String s1 = new String(body);
            log.error("使用new String转换的数据是：{}",s1);
            log.error("timout 是：{}",channel);
            JSONObject object = JSONUtil.parseObj(s1);
            Object taskId = object.get("taskId");
            System.out.println(message.getBody()+"已被消费！"+"任务id是："+taskId);
            log.error("我一直在重试");
            int a = 1/0;
            // 手动确认收到的消息
            channel.basicAck(deliveryTag,false);
        }catch (Exception e){
            log.error("任务确认ack，错误：{}",e.toString());
            try{
                channel.basicNack(deliveryTag,false,true);
            }catch (Exception e2){
                log.error("任务执行发生了错误哦，{}",e2.toString());
            }
        }
    }
}
