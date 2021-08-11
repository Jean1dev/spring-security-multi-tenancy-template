package com.security.template.provider;

import com.security.template.provider.dto.SendMailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * @doc https://stackoverflow.com/questions/35347269/javax-mail-authenticationfailedexception-535-5-7-8-username-and-password-not-ac
 * @doc https://mkyong.com/spring-boot/spring-boot-how-to-send-email-via-smtp/
 */
@Component
@EnableAsync
public class MailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmail(SendMailDto dto) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(dto.getTo());

        msg.setSubject(dto.getSubject());
        msg.setText(dto.getText());

        javaMailSender.send(msg);
    }
}
