package org.example.controller;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义发送控制器
 *
 * @Description
 * @Author Zheng
 * @CreateTime 2023/8/2 19:43
 * @date 2023/08/02
 */
@RestController
@RequestMapping("/custom")
@Slf4j
public class CustomSendController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/test")
    public String testProducer(String message){
        Map<String, Object> map = new HashMap<>();
        map.put("taskId",message);
        String jsonStr = JSONUtil.toJsonStr(map);
        // 发送前端传过来的消息
        rabbitTemplate.convertAndSend("exchange_01","task_routing2",jsonStr);
        return jsonStr + "消息已发送！";
    }

}
