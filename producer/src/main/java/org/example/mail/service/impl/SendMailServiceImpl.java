package org.example.mail.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.mail.MailRequest;
import org.example.mail.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description
 * @Author Zheng
 * @CreateTime 2023/7/27 19:42
 */
@Service
@Slf4j
public class SendMailServiceImpl implements SendMailService {
    /**
     * 注入邮件工具类
     */
    @Resource
    private JavaMailSender javaMailSender;

    /**
     * 发送邮件的用户
     */
    @Value("${spring.mail.username}")
    private String sendMailer;

    /**
     * 检查邮件
     *
     * @param mailRequest 邮件请求
     */
    public void checkMail(MailRequest mailRequest) {
        Assert.notNull(mailRequest,"邮件请求不能为空");
        Assert.notNull(mailRequest.getSendTo(), "邮件收件人不能为空");
        Assert.notNull(mailRequest.getSubject(), "邮件主题不能为空");
        Assert.notNull(mailRequest.getText(), "邮件收件人不能为空");
    }
    @Override
    public void sendSimpleMail(MailRequest mailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        checkMail(mailRequest);
        //邮件发件人
        message.setFrom(sendMailer);
        //邮件收件人 1或多个
        message.setTo(mailRequest.getSendTo().split(","));
        //邮件主题
        message.setSubject(mailRequest.getSubject());
        //邮件内容
        message.setText(mailRequest.getText());
        //邮件发送时间
        message.setSentDate(new Date());

        javaMailSender.send(message);
        log.error("发送邮件成功:{}->{}",sendMailer,mailRequest.getSendTo());
    }
}
