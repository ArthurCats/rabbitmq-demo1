package org.example.mq.topic;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
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
public class Consumer8 {
    int count = 0;

    @RabbitListener(bindings = @QueueBinding(value = @Queue,
                                            exchange = @Exchange(value = "boot_exchange_topic", type = "topic"),
                                            key = {"boss.*"}))
    public void consumer1(String msg,
                          @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
                          Channel channel) throws IOException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("IO异常");
        }
        count ++;
        System.out.println("消费者8：匹配boss开头 --- 消息内容是：" + msg + " 当前数量 ：" + count);
        channel.basicAck(deliveryTag, true);
    }
}
