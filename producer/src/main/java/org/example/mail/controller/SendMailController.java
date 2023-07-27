package org.example.mail.controller;

import org.example.mail.MailRequest;
import org.example.mail.service.impl.SendMailServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author Zheng
 * @CreateTime 2023/7/27 19:48
 */
@RestController
@RequestMapping("/mail")
public class SendMailController {
    @Resource
    SendMailServiceImpl sendMailService;

    @PostMapping("/test")
    public String testConn(){
        return "success!";
    }

    @PostMapping("/simple")
    public void SendMessage(@RequestBody MailRequest mailRequest){
        sendMailService.sendSimpleMail(mailRequest);
    }
}
