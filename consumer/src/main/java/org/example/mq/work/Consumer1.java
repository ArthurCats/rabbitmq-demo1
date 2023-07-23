package org.example.mq.work;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @Description
 * @Author Zheng
 * @CreateTime 2023/7/23 18:41
 */
@Component
public class Consumer1 {
    int count = 0;

    @RabbitListener(queuesToDeclare = @Queue("boot_queue_work"))
    public void consumer1(String msg,
                          @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                          Channel channel) throws IOException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("IO异常");
        }
        count ++;
        System.out.println("消费者1：--- 消息内容是：" + msg + " 当前数量 ：" + count);
        channel.basicAck(deliveryTag, true);
    }
}
