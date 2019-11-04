package com.palchevskyi.testtask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {
    @Autowired
    private JavaMailSender javaMailSender;

    public void send(String emailTo, String subject, String text){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setText(text);
        msg.setSubject(subject);
        msg.setTo(emailTo);
        javaMailSender.send(msg);
    }
}
