package org.example.mail.service;

import org.example.mail.MailRequest;

public interface SendMailService {
    /**
     * 发送简单邮件
     *
     * @param mailRequest 邮件请求
     */
    void sendSimpleMail(MailRequest mailRequest);
}
